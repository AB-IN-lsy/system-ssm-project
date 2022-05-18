package com.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
	@RequestMapping("/test")
	public String test(ModelMap m) {
		System.out.println("请求进入了控制层....");
		m.put("msg", "哈哈,springmvc初始程序大功告成");
		return "index";    //==>  /view/index.jsp
	}
}