package springbootrestapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springbootrestapi.model.User;
import springbootrestapi.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userDao;

	public Optional<List<User>> getAllUsers() {
		return Optional.ofNullable(userDao.findAll());
	}

	public Optional<User> getUserById(Long userId) {
		return Optional.ofNullable(userDao.findOne(userId));
	}
	
	public User saveUser(String name) {
		User user = new User();
		user.setName(name);
		user = userDao.save(user);
		return user;
	}
	
	public void deleteUser(Long id) {
		userDao.delete(id);
	}
	
	public User updateUser(Long id, String name) {
		User user = userDao.findOne(id);
		user.setName(name);
		return userDao.save(user);
	}
	
}
