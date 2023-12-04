package com.vcs.advancedRestApi.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import com.vcs.advancedRestApi.model.Name;
import com.vcs.advancedRestApi.model.User;
import com.vcs.advancedRestApi.model.UserV2;

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
	
	public User findUserById(int id) {
		
		Predicate<? super User> predicate = user -> user.getId().equals(id);
		return users.stream().filter(predicate).findFirst().orElse(null);
	}
	
	public User save(User user) {
		
		user.setId(++id);
		users.add(user);
		return user;
	}
	
	public void deleteUserById(int id) {
		
		Predicate<? super User> predicate = user -> user.getId().equals(id);
		users.removeIf(predicate);
	}
	
	// Version-2	
	private static List<UserV2> usersV2 = new ArrayList<UserV2>();
	
	private static int id2 = 0;
	static {
		usersV2.add(new UserV2(++id2, new Name("vcs", "vcs"), LocalDate.now().minusYears(9)));
		usersV2.add(new UserV2(++id2, new Name("vivek", "vivek"), LocalDate.now().minusYears(28)));
	}
	
	public List<UserV2> findAll2() {
		return usersV2;
	}
	
	public UserV2 findUserById2(int id2) {
		
		Predicate<? super UserV2> predicate = userV2 -> userV2.getId().equals(id2);
		return usersV2.stream().filter(predicate).findFirst().orElse(null);
	}
	
}
