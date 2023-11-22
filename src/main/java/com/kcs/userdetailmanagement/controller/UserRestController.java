package com.kcs.userdetailmanagement.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.kcs.userdetailmanagement.dto.UserDto;
import com.kcs.userdetailmanagement.entity.UserEntity;
import com.kcs.userdetailmanagement.service.UserService;

/**
 * @author Mansi.Patel
 * @author Hetasvi.Ahir
 * @author Kinjal.Vaja
 * @since 0.0.1
 */
@RestController
@RequestMapping("/api")
public class UserRestController {

	@Autowired
	private UserService userService;

	/**
	 * @param username
	 * @return username {@summary get User by name}
	 */
	@GetMapping("/user/{username}")
	public UserEntity getUserByName(@PathVariable String username) {
		return userService.getUserByName(username);
	}

	/**
	 * @param user
	 * @return userDto {@summary save user}
	 */
	@PostMapping("/user")
	public UserEntity saveUser(@RequestBody UserDto user) {
		return userService.saveUser(user);
	}

	/**
	 * @return List {@summary returns the list of users}
	 */
	@GetMapping("/user")
	public List<UserEntity> getAllUsers() {
		return userService.getAllUsers();
	}

	/**
	 * @param id
	 * @return {@summary returns the list of users}
	 */
	@GetMapping("/{id}")
	public UserEntity getUserById(@PathVariable Integer id) {
		return userService.findByID(id);
	}

	/**
	 * @param id
	 * @return string {@summary delete the user}
	 */
	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable Integer id) {
		userService.deleteUserById(id);
		return "Deleted..";
	}

	/**
	 * @param user
	 * @return userDto {@summary update the user}
	 */
	@PutMapping("/update")
	public UserEntity updateUser(@RequestBody UserDto user) {
		return userService.updateUser(user);
	}
}