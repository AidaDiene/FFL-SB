package sn.sonatel.ci_diourbel.fiber_failure_locator.security;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import sn.sonatel.ci_diourbel.fiber_failure_locator.entities.User;
import sn.sonatel.ci_diourbel.fiber_failure_locator.repos.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepository.findByEmail(username);

		if (user == null) {
			throw new UsernameNotFoundException("Utilisateur non trouvé !");
		}
		if (!user.isAdmin()) {
			throw new UsernameNotFoundException("Cet utilisateur n'a pas l'acces admin !");
		}
		if (!user.isEnabled()) {
			throw new UsernameNotFoundException("Ce compte est desactivé !");
		}

		return new CustomUserDetails(
				user.getPrenom(), 
				user.getNom(), 
				user.getMatricule(), 
				user.getTelephone(),
				user.getEmail(),
				user.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_ADMIN")),
				user.isAdmin(),
				user.getPhoto()
				);
	}

}
