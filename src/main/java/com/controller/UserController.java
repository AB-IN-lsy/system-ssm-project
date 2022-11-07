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
	 * 全部用户
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
	 * 后台首页
	 *
	 * @return
	 */
	@RequestMapping("/index")
	public String index(HttpSession session, ModelMap m) {
		User user = (User) session.getAttribute("session_user");
		// 文章列表
		List<Article> articleList = articleService.listRecentArticle(5);
		m.put("articleList", articleList);

		// 评论列表
		List<Comment> commentList = commentService.listRecentComment(5);
		m.put("commentList", commentList);

		// 用户
		m.put("userId", user.getUserId());
		return "index";
	}

	/**
	 * 退出登录
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		// 一定要清除用户的session
		session.invalidate();
		return "login";
	}

	/**
	 * 绕行访问服务端，转到登陆页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String goToLogin() {
		return "login";
	}

	/**
	 * 访问后台主页
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
			request.setAttribute("msg", "用户名错误");
			return "login";
		} else if (!user.getUserPass().equals(userPass)) {
			request.setAttribute("msg", "密码错误");
			return "login";
		} else if (user.getUserStatus() == 0) {
			request.setAttribute("msg", "账号已禁用！");
			return "login";
		} else {
			// 用户登录成功能后,把用户相关的信息放到 session中,方便以后使用
			request.getSession().setAttribute("session_user", user);

			if (rememberMe != null) {
				// 创建两个Cookie对象
				Cookie nameCookie = new Cookie("username", userNameOrEmail);
				// 设置Cookie的有效期为3天
				nameCookie.setMaxAge(60 * 60 * 24 * 3);
				Cookie pwdCookie = new Cookie("password", userPass);
				pwdCookie.setMaxAge(60 * 60 * 24 * 3);
				response.addCookie(nameCookie);
				response.addCookie(pwdCookie);

			}

			// 更新用户的最后登录时间
			// 更新用户的最后登录ip
			user.setUserLastLoginTime(new Date());
			user.setUserLastLoginIp(request.getRemoteAddr());
			// userService.updateUser(user);

			// 把文章列表数据查出来,带到index页
			List<Article> articleList = articleService.listRecentArticle(5);
			request.setAttribute("articleList", articleList);

			// 把评论列表数据查出来,带到index页
			List<Comment> commentList = commentService.listRecentComment(5);
			request.setAttribute("commentList", commentList);
			return "index"; // src/main/webapp/view/index.jsp
		}
	}

	/**
	 * 从导航上转到用户添加页面
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