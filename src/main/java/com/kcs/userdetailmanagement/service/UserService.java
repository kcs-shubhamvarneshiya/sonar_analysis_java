package com.kcs.userdetailmanagement.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.kcs.userdetailmanagement.dto.UserDto;
import com.kcs.userdetailmanagement.entity.UserEntity;

/**
 * @author Mansi.Patel
 * @author Hetasvi.Ahir
 * @author Kinjal.Vaja
 * @since 0.0.1
 *
 */
@Service
public interface UserService {

	/**
	 * @param name
	 * @return name {@summary get user by name}
	 */
	public UserEntity getUserByName(String name);

	/**
	 * @param user
	 * @return userDto {@summary save user}
	 */
	public UserEntity saveUser(UserDto user);

	/**
	 * @return list of users {@summary returns list of user}
	 */
	public List<UserEntity> getAllUsers();

	/**
	 * @param id
	 * @return id {@summary find user by id}
	 */
	public UserEntity findByID(Integer id);

	/**
	 * @param id {@summary delete user by Id}
	 */
	public void deleteUserById(Integer id);

	/**
	 * @param userDto
	 * @return userDto {@summary update user}
	 */
	public UserEntity updateUser(UserDto userDto);

}