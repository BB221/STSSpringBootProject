package controller;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import model.User;
import repository.UserRepository;

@RestController
@RequestMapping("/secure/rest")
public class AdminController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Resource.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	
	//@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping("/admin/add")
	public String addUserbyAdmin(@RequestBody User user) {
		
		LOGGER.info("inside adduser By Admin method()");
		String pwd = user.getPassword();
		String encrptedPwd = passwordEncoder.encode(pwd);
		user.setPassword(encrptedPwd);
		userRepository.save(user);
		return " user added successfully";
	}
}
