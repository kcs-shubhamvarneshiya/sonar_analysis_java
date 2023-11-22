package com.kcs.userdetailmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.kcs.userdetailmanagement.entity.UserEntity;

/**
 * @author Mansi.Patel
 * @author Hetasvi.Ahir
 * @author Kinjal.Vaja
 * @since 0.0.1
 *
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

	/**
	 * @param name
	 * @return {@link UserEntity}
	 */
	public UserEntity findByName(String name);

	/**
	 * @param id
	 * @return {@link UserEntity}
	 */
	public UserEntity findUserById(Integer id);

}
