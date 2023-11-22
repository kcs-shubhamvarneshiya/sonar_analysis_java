package com.kcs.userdetailmanagement.test.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kcs.userdetailmanagement.controller.UserRestController;
import com.kcs.userdetailmanagement.dto.UserDto;
import com.kcs.userdetailmanagement.entity.UserEntity;
import com.kcs.userdetailmanagement.service.UserService;
import com.kcs.userdetailmanagement.test.utility.TestUtility;

/**
 * @author Mansi.Patel
 * @author Hetasvi.Ahir
 * @author Kinjal.Vaja
 * @since 0.0.1
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest(UserRestController.class)
public class UserRestControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private UserService userService;

	@InjectMocks
	private UserRestController userController;

	@Autowired
	private WebApplicationContext webApplicationContext;
	
    private TestUtility testUtility;
    
	/**
	 *  Initialization method for each test case.
	 */
	@Before
	public void setUp() {
		testUtility = new TestUtility();
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	/**
	 * To validate search by user name controller functionality. 
	 * @throws Exception
	 * 
	 */
	@Test
	public void getUserByName() throws Exception {
		UserEntity user ;
		user = testUtility.getUserByConstructor();
		
		when(userService.getUserByName("jadu")).thenReturn(user);

		mvc.perform(get("/api/user/jadu").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value("jadu"));

	}

	/**
	 * To validate save user controller functionality. 
	 * @throws Exception
	 */
	@Test
	public void saveUser() throws Exception {
		UserEntity user;
		UserDto userDto;
		
		user = testUtility.getUser();
	    userDto = testUtility.getUserDto();
	    
		when(userService.saveUser(userDto)).thenReturn(user);

		mvc.perform(MockMvcRequestBuilders.post("/api/user").content(asJsonString(user))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	/**
	 * To validate list of users in controller functionality. 
	 * @throws Exception
	 */
	@Test
	public void getAllUsers() throws Exception {

		UserEntity user;
		UserEntity user1;
		List<UserEntity> users ;
		
		user = testUtility.getUser();
		user1 =testUtility.getUser();
		users = new ArrayList<UserEntity>();
		
		user1.setName("jadu1");
		user1.setId(5);
		
     	users.add(user1);
		users.add(user);

		when(userService.getAllUsers()).thenReturn(users);

		mvc.perform(get("/api/user").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2));

	}

	/**
	 * To validate update user in controller functionality. 
	 * @throws Exception
	 */
	@Test
	public void updateUser() throws Exception {

		UserDto userDto;
		UserEntity user;
		
		userDto = testUtility.getUserDto();
		user = testUtility.getUser();
	
		when(userService.findByID(1)).thenReturn(user);
		when(userService.saveUser(userDto)).thenReturn(user);

		mvc.perform(MockMvcRequestBuilders.put("/api/update").content(asJsonString(user))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	/**
	 * To validate user by id in controller functionality. 
	 * @throws Exception
	 */
	@Test
	public void getUserById() throws Exception {

		UserEntity user ;
		user = testUtility.getUser();
		
		when(userService.findByID(1)).thenReturn(user);

		mvc.perform(get("/api/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(4));

	}

	/**
	 * To validate delete user by id in controller functionality. 
	 * @throws Exception
	 */
	@Test
	public void testDeleteUserById() throws Exception {
		
		mvc.perform(MockMvcRequestBuilders.delete("/api/delete/4").accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(MockMvcResultMatchers.status().isOk());

	}

	/**
	 * To convert object to JSON String
	 * @param obj
	 * @return obj
	 * @throws JsonProcessingException
	 */
	public static String asJsonString(final Object obj) throws JsonProcessingException {

		return new ObjectMapper().writeValueAsString(obj);

	}

}