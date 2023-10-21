package sn.sonatel.ci_diourbel.fiber_failure_locator;

import java.io.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootApplication
public class FiberFailureLocatorApplication {
    private static final Logger logger = LoggerFactory.getLogger(FiberFailureLocatorApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(FiberFailureLocatorApplication.class, args);
        String staticDirPath = "src/main/resources/static";
        Path uploadsDirPath = Paths.get(staticDirPath, "uploads");
        File uploadsDir = uploadsDirPath.toFile();
        if (!uploadsDir.exists()) {
            try {
                Files.createDirectories(uploadsDirPath);
                logger.info("Répertoire 'uploads' créé avec succès.");
            } catch (IOException e) {
                logger.error("Impossible de créer le répertoire 'uploads'.", e);
            }
        } else {
            logger.info("Le répertoire 'uploads' existe déjà.");
        }
    }
}
