//package com.artshop.jin.util;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

//@Configuration
//@EnableWebSecurity

//public class SecurityConfig {
////	@Bean
////	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////	    http
////	        .authorizeHttpRequests((requests) -> requests
////	            .requestMatchers("/css/**", "/js/**", "/images/**").permitAll() // 允许匿名访问静态资源
////	            .requestMatchers("/admin/**").hasRole("ADMIN")
////	            .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")
////	            .anyRequest().authenticated())
////	        .formLogin((form) -> form
////	            .loginPage("/login")
////	            .permitAll())
////	        .logout((logout) -> logout
////	            .permitAll());
////	    return http.build();
////	}
//
////	@Bean
////	PasswordEncoder passwordEncoder() {
////		return new BCryptPasswordEncoder();
////	}
//
//}
