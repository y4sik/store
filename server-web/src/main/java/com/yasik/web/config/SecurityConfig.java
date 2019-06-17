package com.yasik.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
//                .antMatchers("/home").hasRole("CUSTOMER")
//                .antMatchers("/home/leaders/**").hasRole("MODERATOR")
//                .antMatchers("/home/systems/**").hasRole("ADMIN")
//                .antMatchers(HttpMethod.GET, "/api/customers").hasRole("CUSTOMER")
//                .antMatchers(HttpMethod.GET, "/api/customers/*").hasRole("CUSTOMER")
//                .antMatchers(HttpMethod.POST, "/api/customers").hasRole("MODERATOR")
//                .antMatchers(HttpMethod.PUT, "/api/customers").hasRole("MODERATOR")
//                .antMatchers(HttpMethod.DELETE, "/api/customers").hasRole("ADMIN")
                .and()
                .logout()
                .permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/access-denied")
                .and()
                .csrf().disable()
                .formLogin().disable();
    }
}
