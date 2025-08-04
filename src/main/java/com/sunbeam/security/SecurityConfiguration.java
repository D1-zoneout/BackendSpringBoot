package com.sunbeam.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // to declare config class - equivalent to bean config xml file
@EnableWebSecurity // to enable spring security
@EnableMethodSecurity // later - to enable auth rules at method level
public class SecurityConfiguration {
	// configure spring security filter chain - as a spring bean
	@Bean
	SecurityFilterChain configureFilterChain(HttpSecurity http) throws Exception {
		// HttpSecurity - spring sec supplied class
		// to customize n build filter chain
		// 1. disable CSRF protection
		http.csrf(csrf -> csrf.disable());
		// 2. any request - has to be authenticated
		http.authorizeHttpRequests(

				request -> request
						.requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/users/signin", "/users/signup")
						.permitAll().requestMatchers(HttpMethod.GET, "/restaurants").permitAll()
						.requestMatchers(HttpMethod.POST, "/restaurants").hasRole("ADMIN")
						.anyRequest()
						.authenticated());
		// 3. disable HttpSession tracking - stateless
		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		// 4. To support REST APIs , disable form login
		http.formLogin(form -> form.disable());
		// 5. Enable Basic auth support - with default.
		http.httpBasic(Customizer.withDefaults());
		/*
		 * - any one (un protected => permit all ) should be able to access swagger ui ,
		 * user sign in , user sign up , list all available restaurants - only
		 * authenticated users should be able assign address - only user logged in under
		 * customer role - should be able to place order - only user logged in under
		 * admin role - should be able to add food item | update restaurant | delete
		 * restaurant
		 * 
		 */

		return http.build();
	}

	// 2.Configure another spring bean to provide user details loaded from in
	// memory.
//	@Bean
//	UserDetailsService userDetailsService() {
//		//TO BE COMPLETED later.....
//		//user name |email , password , Collection of GrantedAuthorities
//		UserDetails admin=new User("rama@gmail.com", null, null);
//		InMemoryUserDetailsManager mgr = 
//				new InMemoryUserDetailsManager();
//		return mgr;
//	}

}
