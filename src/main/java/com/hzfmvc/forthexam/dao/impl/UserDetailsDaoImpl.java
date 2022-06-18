package com.hzfmvc.forthexam.dao.impl;

import com.hzfmvc.forthexam.dao.UserDao;
import com.hzfmvc.forthexam.entity.User;
import com.hzfmvc.forthexam.utils.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsDaoImpl implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findUserByName(username);

        if (user == null) {
            throw new UsernameNotFoundException("未找到此用户信息！");
        }

        return new MyUserDetails(user);
    }

}
