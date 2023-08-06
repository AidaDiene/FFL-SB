package sn.sonatel.ci_diourbel.fiber_failure_locator.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import sn.sonatel.ci_diourbel.fiber_failure_locator.entities.User;

public interface UserService {

	User saveUser(User u);

	User updateUser(User u);

	void deleteUser(User u);

	void deleteUserById(Long id);

	User getUser(Long id);

	List<User> getAllUsers();

	public void init();

	public void saveFile(MultipartFile file);

}
