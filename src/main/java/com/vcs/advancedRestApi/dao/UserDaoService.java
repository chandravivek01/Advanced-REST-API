package com.vcs.advancedRestApi.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.vcs.advancedRestApi.model.User;

@Component
public class UserDaoService {
	
	private static List<User> users = new ArrayList<User>();
	
	private static int id = 0;
	static {
		users.add(new User(++id, "vcs", LocalDate.now().minusYears(9)));
		users.add(new User(++id, "vivek", LocalDate.now().minusYears(28)));
	}
	
	public List<User> findAll() {
		return users;
	}
	
}
