package com.hzfmvc.forthexam.dao;

import com.hzfmvc.forthexam.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UserDao extends CrudRepository<User, Long> {
//    nativeQuery = true
    @Query(value = "SELECT u FROM User u WHERE u.userName = :username")
    User findUserByName(@Param("username") String username);

    // Executing an update/delete query] with root cause
    @Modifying
    @Transactional
    @Query("update User u set u.password = :newpassword where u.userName = :username")
    int updatePwdByName(@Param("newpassword") String newpassword, @Param("username") String username);

}
