package com.kcs.userdetailmanagement.test.serviceimpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;
import com.kcs.userdetailmanagement.dto.UserDto;
import com.kcs.userdetailmanagement.entity.UserEntity;
import com.kcs.userdetailmanagement.repository.UserRepository;
import com.kcs.userdetailmanagement.serviceimpl.UserServiceImpl;
import com.kcs.userdetailmanagement.test.utility.TestUtility;

/**
 * @author Mansi.Patel
 * @author Hetasvi.Ahir
 * @author Kinjal.Vaja
 * @since 0.0.1
 *
 */
@RunWith(SpringRunner.class)
public class UserServiceImplTest {

	@InjectMocks
	private UserServiceImpl userService;

	@Mock
	private UserRepository userRepository;

	 TestUtility testUtility = new TestUtility();
	 
   
	/**
	 * Initialization method for each test case.
	 */
	@Before
	public void setUp() {

		UserEntity user;
		UserEntity user1;
		List<UserEntity> users;

		user = testUtility.getUser();
		user1 = testUtility.getUser();
		users = new ArrayList<UserEntity>();

		users.add(user1);
		users.add(user);

		Mockito.when(userRepository.findByName(user.getName())).thenReturn(user);
		Mockito.when(userRepository.findAll()).thenReturn(users);
		Mockito.doNothing().when(userRepository).deleteById(1);
	}

	/**
	 * To validate search by user name functionality.
	 */
	@Test
	public void getUserByName() {

		String name = "jadu";
		UserEntity user = userService.getUserByName(name);
		Assert.assertEquals(user.getName(), name);
	}

	/**
	 * To validate save user functionality.
	 */
	@Test
	public void saveUser() {
		UserDto userDto;
		userDto = testUtility.getUserDto();
		
		userService.saveUser(userDto);
		verify(userRepository, times(1)).save(isA(UserEntity.class));
	}

	/**
	 * To validate list of users functionality.
	 */
	@Test
	public void getAllUsers() {
		List<UserEntity> users;

		users = userService.getAllUsers();
		Assert.assertEquals(2, users.size());
	}

	/**
	 * To validate update user's true condition by id functionality.
	 */
	@Test
	public void findById() {
		UserEntity user;
		UserEntity user2;
		
		user = testUtility.getUser();
	
		Mockito.when(userRepository.findUserById(4)).thenReturn(user);
		user2 = userService.findByID(4);
		assertEquals(user2.getId(), user.getId());
	}

	/**
	 * To validate update user's false condition by id functionality.
	 */
	@Test
	public void findByIdElse() {

		
		UserEntity user2;
		user2 = userService.findByID(1);

		Mockito.when(userRepository.findUserById(1)).thenReturn(null);
		assertEquals(null, user2);
	}

	/**
	 * To validate delete user by id functionality.
	 */
	@Test
	public void deleteUserById() {
		
		userService.deleteUserById(4);
		verify(userRepository, times(1)).deleteById(4);

	}

	/**
	 * To validate update user functionality
	 */
	@Test
	public void updateUser() {
		UserEntity user;
		UserDto userDto;
		
		user = testUtility.getUser();
		userDto = testUtility.getUserDto();
	
		Mockito.when(userRepository.findUserById(userDto.getId())).thenReturn(user);
		Mockito.when(userRepository.save(user)).thenReturn(user);
		
		userService.updateUser(userDto);
		assertEquals(userDto.getId(), user.getId());

	}
}
