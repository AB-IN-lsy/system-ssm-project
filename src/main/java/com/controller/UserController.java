package com.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.entity.Article;
import com.entity.Comment;
import com.entity.User;
import com.github.pagehelper.PageInfo;
import com.service.ArticleService;
import com.service.CommentService;
import com.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;

	@Autowired
	private ArticleService articleService;

	@Autowired
	private CommentService CommentService;

	/**
	 * ��ҳ��ѯ�û���Ϣ
	 * 
	 * @param pageIndex ���ڷ�ҳ,��ʾ��ǰ�ǵڼ�ҳ,Ĭ����1
	 * @param pageSize  ���ڷ�ҳ,��ʾÿҳ�ж���������,Ĭ����10
	 * @param m
	 * @return ���ص��� PageInfo���͵�����,�����溬�з�ҳ��Ϣ,�;���Ĳ����������
	 */
	@RequestMapping(value = "")
	public String index(@RequestParam(required = false, defaultValue = "1") Integer pageIndex,
			@RequestParam(required = false, defaultValue = "5") Integer pageSize, ModelMap m) {

		// ��ҳ��ѯ������ص�����,�ŵ���������
		PageInfo<User> pageInfo = userService.getPageUserList(pageIndex, pageSize);

		m.put("pageUrlPrefix", "user?pageIndex"); // ��ǰ׺������ҳ��ҳ��
		m.put("pageInfo", pageInfo);
		return "/User/user-list";
	}

	@RequestMapping("/login")
	public String login(HttpServletRequest request) {
		String userNameOrEmail = request.getParameter("userName");
		String userPass = request.getParameter("userPass");

		// String rememberMe=request.getParameter("rememberMe");

		User user = userService.loginByNameOrEmail(userNameOrEmail);
		if (user == null) {
			request.setAttribute("msg", "�û�������");
			return "login";
		} else if (!user.getUserPass().equals(userPass)) {
			request.setAttribute("msg", "�������");
			return "login";
		} else {
			// �û���¼�ɹ��ܺ�,���û���ص���Ϣ�ŵ� session��,�����Ժ�ʹ��
			request.getSession().setAttribute("session_user", user);

			// ����û���ѡ��rememberMe , ���cookie��ص���Ϣ
			// �����û�������¼ʱ��
			// �����û�������¼ip
			// user.setUserLastLoginTime(new Date());
			// user.setUserLastLoginIp(request.getRemoteAddr());
			// userService.updateUser(user);

			// �������б����ݲ����,����indexҳ
			List<Article> artileList = articleService.listRecentArticle(5);
			request.setAttribute("artileList", artileList);

			// �������б����ݲ����,����indexҳ
			List<Comment> commentList = CommentService.listRecentComment(5);
			request.setAttribute("commentList", commentList);
			return "index"; // src/main/webapp/view/index.jsp
		}
	}

	/**
	 * �ӵ�����ת���û����ҳ��
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String goToAdd() {
		return "/User/user-add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(User user, MultipartFile photo) throws IOException {
		user.setUserRegisterTime(new Date());
		user.setUserStatus(1);
		user.setUserPhoto(photo.getBytes()); // ���ļ�����װ���ֽ�����

		userService.addUser(user);
		return "forward:/user";
	}

	/**
	 * restful ���Ϊ�˽�user-list��ͼƬ��ʾ����
	 * 
	 * @param userId
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/photo/{userId}")
	public void showPhoto(@PathVariable("userId") Integer userId, HttpServletResponse response) throws IOException {
		User user = userService.getUserById(userId);

		response.setContentType("image/jpg"); // �趨��Ӧ�����ݵ����ͣ����������������ͼƬ
		ServletOutputStream out = response.getOutputStream(); // �õ�ͨ��ͻ��˵������
		out.write(user.getUserPhoto()); // д�������
		out.flush();
	}

}