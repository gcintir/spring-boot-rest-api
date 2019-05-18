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
		User user = userDao.findOne(userId);
		if (null == user) {
			return Optional.ofNullable(null);
		}
		return Optional.of(user);
	}
}
