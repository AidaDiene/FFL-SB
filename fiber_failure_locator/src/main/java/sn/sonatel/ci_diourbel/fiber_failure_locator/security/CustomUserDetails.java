package sn.sonatel.ci_diourbel.fiber_failure_locator.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class CustomUserDetails implements UserDetails {

	private final String prenom;
	private final String nom;
	private final String matricule;
	private final String telephone;
	private final String username;
	private final String password;
	private final Collection<? extends GrantedAuthority> authorities;
	private final boolean enabled;
	private final String photo;

	public CustomUserDetails(String prenom, String nom, String matricule, String telephone, String username, String password,
			Collection<? extends GrantedAuthority> authorities, boolean enabled, String photo) {
		this.prenom = prenom;
		this.nom = nom;
		this.matricule = matricule;
		this.telephone = telephone;
		this.username = username;
		this.password = password;
		this.authorities = authorities;
		this.enabled = enabled;
		this.photo = photo;
	}

	public String getNom() {
		return this.nom;
	}

	public String getPrenom() {
		return this.prenom;
	}

	public String getMatricule() {
		return this.matricule;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public String getPassword() {
		return this.password;
	}
	
	public String getPhoto() {
		return this.photo;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	public boolean isEnabled() {
		return this.enabled;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
}
