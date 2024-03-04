package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

//UserController.java

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.RedisService;
import com.example.demo.entitiy.User;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

	private static final String REQUEST_COUNTER_KEY = "requestCounter";

	@Autowired
	private RedisService redisService;

	private final List<User> users = new ArrayList<>();

	@GetMapping
	public String getAllUsers() {
		// Kullanıcının Redis'te saklanan sayaç değerini artır
		Long requestCount = redisService.incrementCounter(REQUEST_COUNTER_KEY);

		if (requestCount > 5) {
			// Beşten fazla istek yapıldığında işlemi reddet
			return "redirect:/error"; // veya başka bir uygun işlem
		}

		return "view/index";
	}

// @GetMapping
// public String getAllUsers() {
//     
//     return "view/index";
// }

	@GetMapping("/add2")
	public String getAllUsers2(Model model) {
		model.addAttribute("users", users);
		return "view/index";
	}

	@GetMapping("/add")
	public String showAddUserForm(User user) {
		return "view/add-user";
	}

	@PostMapping("/add")
	public String addUser(User user) {
		users.add(user);
		return "redirect:/users";
	}
}
