package sn.sonatel.ci_diourbel.fiber_failure_locator.services;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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

    private static final Path ROOT_PATH = Paths.get("src/main/resources/static/uploads");

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
        return userRepo.findById(id).orElse(null);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public void init() {
        try {
            Files.createDirectories(ROOT_PATH);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }

    @Override
    public void saveFile(MultipartFile file) {
        try (InputStream inputStream = file.getInputStream()) {
            SecurityContext securityContext = SecurityContextHolder.getContext();
            Authentication authentication = securityContext.getAuthentication();

            if (authentication != null && authentication.isAuthenticated()) {
                CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
                User user = userRepo.findByEmail(customUserDetails.getUsername());

                String originalFilename = file.getOriginalFilename();
                String filename = generateFilenameWithoutExtension(user);
                String chemin = "src/main/resources/static/uploads";
                User.deleteFileNameExists(chemin,"photoProfilAidaDienemat1");
                filename = addExtensionToFilename(filename, originalFilename);
                Path filePath = ROOT_PATH.resolve(filename);

                Files.copy(inputStream, filePath);

                String photoPath = "uploads/" + filename;
                user.setPhoto(photoPath);
                userRepo.save(user);

                //mettre a jour le chemin de la photo sur le getprincipal cad le userAuthentifier car lorsque la basse de donnes est modifie apres l'authentification il n'affiche pas ses mise a jour donc n'affiche pas la nouvelle photo de profil
                CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                // Créez une nouvelle instance de CustomUserDetails avec le nouveau chemin de la photo
                CustomUserDetails updatedUserDetails = new CustomUserDetails(
                        userDetails.getPrenom(),
                        userDetails.getNom(),
                        userDetails.getMatricule(),
                        userDetails.getTelephone(),
                        userDetails.getUsername(),
                        userDetails.getPassword(),
                        userDetails.getAuthorities(),
                        userDetails.isEnabled(),
                        photoPath
                );
                Authentication currentAuthentication = SecurityContextHolder.getContext().getAuthentication();
                // Créez un nouvel objet Authentication avec le nouvel objet principal
                Authentication newAuthentication = new UsernamePasswordAuthenticationToken(
                        updatedUserDetails,
                        currentAuthentication.getCredentials(),
                        currentAuthentication.getAuthorities()
                );
                // Remplacez l'objet Authentication dans le contexte de sécurité
                SecurityContextHolder.getContext().setAuthentication(newAuthentication);

            }
        } catch (Exception e) {
            throw new RuntimeException("Could not save the file: " + e.getMessage());
        }
    }

    private String generateFilenameWithoutExtension(User user) {
        String filenameWithoutExtension = "photoProfil" + user.getPrenom() + user.getNom() + user.getMatricule();
        return filenameWithoutExtension;
    }
    private String addExtensionToFilename(String fileName, String originalFilename) {

        int lastDotIndex = originalFilename.lastIndexOf(".");
        if (lastDotIndex != -1) {
            fileName += originalFilename.substring(lastDotIndex);
        }
        return fileName;
    }
}