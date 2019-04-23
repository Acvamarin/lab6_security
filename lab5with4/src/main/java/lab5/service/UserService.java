package lab5.service;

import java.util.List;

import lab5.entity.User;

public interface UserService extends Service<User> {

	List<User> findByUsername(String username);

}
