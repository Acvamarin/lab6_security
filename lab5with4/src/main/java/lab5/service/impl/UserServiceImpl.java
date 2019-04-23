package lab5.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lab5.entity.User;
import lab5.repository.UserRepository;
import lab5.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository repository;

	@Override
	public User read(Long id) {
		return repository.findOne(id);

	}

	@Override
	public List<User> read() {
		return repository.findAll();
	}

	@Override
	public void save(User entity) {
		repository.save(entity);
	}

	@Override
	public void delete(Long id) {
		repository.delete(id);
	}

	@Override
	public List<User> findByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

}