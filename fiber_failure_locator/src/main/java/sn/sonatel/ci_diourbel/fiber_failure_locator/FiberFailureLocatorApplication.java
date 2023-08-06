package sn.sonatel.ci_diourbel.fiber_failure_locator;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FiberFailureLocatorApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(FiberFailureLocatorApplication.class, args);
		
		 // Chemin vers le répertoire "static" de votre projet
        String staticDirPath = "src/main/resources/static";

        // Chemin vers le répertoire "uploads" à l'intérieur du répertoire "static"
        String uploadsDirPath = staticDirPath + "/uploads";

        File uploadsDir = new File(uploadsDirPath);
        if (!uploadsDir.exists()) {
            if (uploadsDir.mkdirs()) {
                System.out.println("Répertoire 'uploads' créé avec succès.");
            } else {
                System.err.println("Impossible de créer le répertoire 'uploads'.");
            }
        } else {
            System.out.println("Le répertoire 'uploads' existe déjà.");
        }
   
	}

}
