package com.sl.dms.domain.service;

import com.sl.dms.domain.User;

public interface UserService {
	public User findUserByEmail(String email);
	public void saveUser(User user);
}