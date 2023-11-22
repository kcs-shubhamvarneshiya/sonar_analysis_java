package com.kcs.userdetailmanagement.serviceimpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kcs.userdetailmanagement.dto.UserDto;
import com.kcs.userdetailmanagement.entity.UserEntity;
import com.kcs.userdetailmanagement.repository.UserRepository;
import com.kcs.userdetailmanagement.service.UserService;

/**
 * @author Mansi.Patel
 * @author Hetasvi.Ahir
 * @author Kinjal.Vaja
 * @since 0.0.1
 *
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	UserEntity user;

	@Override
	public UserEntity getUserByName(String name) {

		return userRepository.findByName(name);
	}

	@Override
	public UserEntity saveUser(UserDto userDto) {

		user = new UserEntity();

		user.setId(userDto.getId());
		user.setName(userDto.getName());

		return userRepository.save(user);
	}

	@Override
	public List<UserEntity> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public UserEntity findByID(Integer id) {

		user = userRepository.findUserById(id);
		if (user != null) {

			return user;

		} else {
			return null;
		}

	}

	@Override
	public void deleteUserById(Integer id) {
		userRepository.deleteById(id);
	}

	@Override
	public UserEntity updateUser(UserDto userDto) {
		user = userRepository.findUserById(userDto.getId());

		user.setId(userDto.getId());
		user.setName(userDto.getName());

		return userRepository.save(user);
	}

}