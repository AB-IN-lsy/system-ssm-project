package com.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.entity.Article;
import com.entity.Comment;
import com.entity.User;
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
	private CommentService commentService;

	/**
	 * ȫ���û�
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "")
	public String index(ModelMap map) {
		List<User> userList = userService.listUser();
		map.put("userList", userList);
		return "User/user-list";
	}

	/**
	 * ��̨��ҳ
	 *
	 * @return
	 */
	@RequestMapping("/index")
	public String index(HttpSession session, ModelMap m) {
		User user = (User) session.getAttribute("session_user");
		// �����б�
		List<Article> articleList = articleService.listRecentArticle(5);
		m.put("articleList", articleList);

		// �����б�
		List<Comment> commentList = commentService.listRecentComment(5);
		m.put("commentList", commentList);

		// �û�
		m.put("userId", user.getUserId());
		return "index";
	}

	/**
	 * �˳���¼
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		// һ��Ҫ����û���session
		session.invalidate();
		return "login";
	}

	/**
	 * ���з��ʷ���ˣ�ת����½ҳ��
	 * 
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String goToLogin() {
		return "login";
	}

	/**
	 * ���ʺ�̨��ҳ
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpServletRequest request, HttpServletResponse response) {
		String userNameOrEmail = request.getParameter("userName");
		String userPass = request.getParameter("userPass");
		String rememberMe = request.getParameter("rememberMe");

		User user = userService.loginByNameOrEmail(userNameOrEmail);
		if (user == null) {
			request.setAttribute("msg", "�û�������");
			return "login";
		} else if (!user.getUserPass().equals(userPass)) {
			request.setAttribute("msg", "�������");
			return "login";
		} else if (user.getUserStatus() == 0) {
			request.setAttribute("msg", "�˺��ѽ��ã�");
			return "login";
		} else {
			// �û���¼�ɹ��ܺ�,���û���ص���Ϣ�ŵ� session��,�����Ժ�ʹ��
			request.getSession().setAttribute("session_user", user);

			if (rememberMe != null) {
				// ��������Cookie����
				Cookie nameCookie = new Cookie("username", userNameOrEmail);
				// ����Cookie����Ч��Ϊ3��
				nameCookie.setMaxAge(60 * 60 * 24 * 3);
				Cookie pwdCookie = new Cookie("password", userPass);
				pwdCookie.setMaxAge(60 * 60 * 24 * 3);
				response.addCookie(nameCookie);
				response.addCookie(pwdCookie);

			}

			// �����û�������¼ʱ��
			// �����û�������¼ip
			user.setUserLastLoginTime(new Date());
			user.setUserLastLoginIp(request.getRemoteAddr());
			// userService.updateUser(user);

			// �������б����ݲ����,����indexҳ
			List<Article> articleList = articleService.listRecentArticle(5);
			request.setAttribute("articleList", articleList);

			// �������б����ݲ����,����indexҳ
			List<Comment> commentList = commentService.listRecentComment(5);
			request.setAttribute("commentList", commentList);
			return "index"; // src/main/webapp/view/index.jsp
		}
	}

	/**
	 * �ӵ�����ת���û����ҳ��
	 * 
	 * @return
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