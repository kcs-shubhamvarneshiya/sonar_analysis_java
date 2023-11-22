package com.kcs.userdetailmanagement.test.utility;

import com.kcs.userdetailmanagement.dto.UserDto;
import com.kcs.userdetailmanagement.entity.UserEntity;

/**
 * @author Mansi.Patel
 * @author Hetasvi.Ahir
 * @author Kinjal.Vaja
 * @since 0.0.1
 *
 */
public class TestUtility {

	/**
	 * Set user for test cases
	 * 
	 * @return user
	 */
	public UserEntity getUser() {

		UserEntity user = new UserEntity();
		user.setName("jadu");
		user.setId(4);
		return user;

	}

	/**
	 * Set UserDto for test cases
	 * 
	 * @return userDto
	 */
	public UserDto getUserDto() {

		UserDto userDto = new UserDto();
		userDto.setName("jadu");
		userDto.setId(4);
		return userDto;

	}

	/**
	 * 
	 * @return user
	 */
	public UserEntity getUserByConstructor() {
		UserEntity user = new UserEntity(4, "jadu");
		return user;
	}
}
