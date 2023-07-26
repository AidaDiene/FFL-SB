package sn.sonatel.ci_diourbel.fiber_failure_locator.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
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
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepository.findByEmail(username);

		if (user == null) {
            throw new UsernameNotFoundException("Utilisateur non trouvé");
        }
		if (user.isAdmin() == false) {
            throw new UsernameNotFoundException("Cet utilisateur n'a pas l'acces admin");
        }
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword())
                .roles("ADMIN")
                .build();
	}

}
