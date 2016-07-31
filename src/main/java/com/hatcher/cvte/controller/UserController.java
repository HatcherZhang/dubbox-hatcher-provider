package com.hatcher.cvte.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hatcher.cvte.model.Dept;
import com.hatcher.cvte.model.Menu;
import com.hatcher.cvte.model.User;
import com.hatcher.cvte.results.BaseResult;
import com.hatcher.cvte.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "isLogin")
	@ResponseBody
	public BaseResult isLogin(User user, String captcha, HttpSession session) {
		String yzm = (String) session.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		/** 默认是false **/
		System.out.println(user.getLoginAccount());
		BaseResult result = new BaseResult();
		// if (yzm.equals(captcha)) {
		User value = userService.islogin(user);
		if (value.getUserCode() != null) {
			result.setResultMsg("success");
			result.setResult(true);
			session.setAttribute("USERCODE", value.getUserCode());
		}
		System.out.println("后台验证中。。。");
		// } else {
		// result.setResultMsg("验证码错误，请重新输入！");
		// }
		return result;
	}

	@RequestMapping("getUserDepts")
	@ResponseBody
	public List<Dept> getUserDepts(HttpSession session) {
		String userCode = (String) session.getAttribute("USERCODE");
		List<Dept> list = userService.getUserDepts(userCode);
		System.out.println("获取部门信息中。。。");
		return list;
	}

	@RequestMapping("getUserMenus")
	@ResponseBody
	public List<Menu> getUserMenus(HttpSession session) {
		String userCode = (String) session.getAttribute("USERCODE");
		List<Menu> list = userService.getUserMenus(userCode);
		System.out.println("获取菜单信息中。。。");
		return list;
	}

	@RequestMapping("exit")
	public ModelAndView exit(HttpSession session) {
		ModelAndView view = new ModelAndView("login");
		session.removeAttribute("USERCODE");
		return view;
	}
}
