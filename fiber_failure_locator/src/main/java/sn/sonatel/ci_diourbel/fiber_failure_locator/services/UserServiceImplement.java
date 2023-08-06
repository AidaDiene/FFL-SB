package sn.sonatel.ci_diourbel.fiber_failure_locator.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import sn.sonatel.ci_diourbel.fiber_failure_locator.entities.User;
import sn.sonatel.ci_diourbel.fiber_failure_locator.repos.UserRepository;
import sn.sonatel.ci_diourbel.fiber_failure_locator.security.CustomUserDetails;

@Service
public class UserServiceImplement implements UserService {

	@Autowired
	UserRepository userRepo;

	private final Path root = Paths.get("src/main/resources/static/uploads");

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

	@Override
	public void init() {
		try {
			Files.createDirectories(root);
		} catch (IOException e) {
			throw new RuntimeException("Could not initialize folder for upload!");
		}
	}

	@Override
	public void saveFile(MultipartFile file) {
		// Obtenir le contexte de sécurité
		SecurityContext securityContext = SecurityContextHolder.getContext();
		// Obtenir l'objet d'authentification
		Authentication authentication = securityContext.getAuthentication();

		try {
			if (authentication != null && authentication.isAuthenticated()) {
				// Obtenir l'objet Principal
				CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
				User user = userRepo.findByEmail(customUserDetails.getUsername());
				
				String filenameWithoutExtension = "photoProfil" + user.getPrenom() + user.getNom() + user.getMatricule();
				String filename = filenameWithoutExtension;
				String originalFilename = file.getOriginalFilename();
				System.out.println("filename avant lastindex : " + filename);
				// Extraire l'extension du fichier d'origine (s'il existe)
				int lastDotIndex = originalFilename.lastIndexOf(".");
				if (lastDotIndex != -1) {
				    filename += originalFilename.substring(lastDotIndex);
				}

				System.out.println("filename : " + filename);

				String filePath = "src/main/resources/static/uploads";
				User.deleteFileNameExists(filePath, filenameWithoutExtension);

				Files.copy(file.getInputStream(), this.root.resolve(filename));
				String photoPath = "uploads/" + filename;
				user.setPhoto(photoPath);
				userRepo.save(user);
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

}
