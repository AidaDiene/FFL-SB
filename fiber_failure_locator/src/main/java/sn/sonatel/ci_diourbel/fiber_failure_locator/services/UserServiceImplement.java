package sn.sonatel.ci_diourbel.fiber_failure_locator.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sn.sonatel.ci_diourbel.fiber_failure_locator.entities.User;
import sn.sonatel.ci_diourbel.fiber_failure_locator.repos.UserRepository;

@Service
public class UserServiceImplement implements UserService {

	@Autowired
	UserRepository userRepo ;
	
	@Override
	public User saveUser(User u) {
		return userRepo.save(u);
	}

	@Override
	public User updateUser(User u) {
		return userRepo.save(u);
	}

	@Override
	public void deleteUser(User u) {
		userRepo.delete(u);
	}

	@Override
	public void deleteUserById(Long id) {
		userRepo.deleteById(id);
	}

	@Override
	public User getUser(Long id) {
		return userRepo.findById(id).get();
	}

	@Override
	public List<User> getAllUsers() {
		return userRepo.findAll();
	}

}
