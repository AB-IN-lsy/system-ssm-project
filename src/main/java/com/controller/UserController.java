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
	 * 分页查询用户信息
	 * 
	 * @param pageIndex 用于分页,表示当前是第几页,默认是1
	 * @param pageSize  用于分页,表示每页有多少条数据,默认是10
	 * @param m
	 * @return 返回的是 PageInfo类型的数据,它里面含有分页信息,和具体的查出来的数据
	 */
	@RequestMapping(value = "")
	public String index(@RequestParam(required = false, defaultValue = "1") Integer pageIndex,
			@RequestParam(required = false, defaultValue = "5") Integer pageSize, ModelMap m) {

		// 分页查询文章相关的数据,放到作用域中
		PageInfo<User> pageInfo = userService.getPageUserList(pageIndex, pageSize);

		m.put("pageUrlPrefix", "user?pageIndex"); // 把前缀传给分页的页面
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
			request.setAttribute("msg", "用户名错误");
			return "login";
		} else if (!user.getUserPass().equals(userPass)) {
			request.setAttribute("msg", "密码错误");
			return "login";
		} else {
			// 用户登录成功能后,把用户相关的信息放到 session中,方便以后使用
			request.getSession().setAttribute("session_user", user);

			// 如果用户勾选了rememberMe , 添加cookie相关的信息
			// 更新用户的最后登录时间
			// 更新用户的最后登录ip
			// user.setUserLastLoginTime(new Date());
			// user.setUserLastLoginIp(request.getRemoteAddr());
			// userService.updateUser(user);

			// 把文章列表数据查出来,带到index页
			List<Article> artileList = articleService.listRecentArticle(5);
			request.setAttribute("artileList", artileList);

			// 把评论列表数据查出来,带到index页
			List<Comment> commentList = CommentService.listRecentComment(5);
			request.setAttribute("commentList", commentList);
			return "index"; // src/main/webapp/view/index.jsp
		}
	}

	/**
	 * 从导航上转到用户添加页面
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String goToAdd() {
		return "/User/user-add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(User user, MultipartFile photo) throws IOException {
		user.setUserRegisterTime(new Date());
		user.setUserStatus(1);
		user.setUserPhoto(photo.getBytes()); // 把文件数据装成字节数组

		userService.addUser(user);
		return "forward:/user";
	}

	/**
	 * restful 风格，为了将user-list的图片显示出来
	 * 
	 * @param userId
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/photo/{userId}")
	public void showPhoto(@PathVariable("userId") Integer userId, HttpServletResponse response) throws IOException {
		User user = userService.getUserById(userId);

		response.setContentType("image/jpg"); // 设定响应的数据的类型，告诉浏览器传的是图片
		ServletOutputStream out = response.getOutputStream(); // 得到通向客户端的输出流
		out.write(user.getUserPhoto()); // 写入输出流
		out.flush();
	}

}