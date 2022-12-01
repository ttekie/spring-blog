package com.codeup.springblog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
            .antMatchers("/posts/create", "/post/{id}/edit").authenticated()
            .antMatchers("/", "/post/create/all-post", "/post/show/{id}").permitAll()
            .and().formLogin().loginPage("/login").defaultSuccessUrl("/posts")
            .and().logout()
            .and().httpBasic();

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
