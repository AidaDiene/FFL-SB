package sn.sonatel.ci_diourbel.fiber_failure_locator.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeRequests(
						(requests) -> requests
								.requestMatchers("/css/**", "/img/**", "/js/**", "/scss/**", "/vendor/**", "/uploads/**", "/api/**").permitAll()
								.anyRequest().authenticated())
				.formLogin((form) -> form.loginPage("/login").permitAll())
				.logout((logout) -> {
					logout.logoutUrl("/logout")
							.permitAll()
							.logoutSuccessUrl("/login")
							.deleteCookies("JSESSIONID")
							.invalidateHttpSession(true);
				})
				.rememberMe(me -> me.key("rememberMe"))
				.csrf().disable();
		return http.build();
	}

	@Autowired
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}
}
