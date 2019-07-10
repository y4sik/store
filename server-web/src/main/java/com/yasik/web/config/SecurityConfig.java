package com.yasik.web.config;

import com.yasik.service.handler.GlobalExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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

    @Autowired
    private GlobalExceptionHandler exceptionHandler;

    private static String REALM="MY_TEST_REALM";

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
                .antMatchers(HttpMethod.POST, "api/customers").permitAll()
                .antMatchers(HttpMethod.GET, "/api/admin/customers").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/admin/customers/*").hasRole( "ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/customers").hasRole("CUSTOMER")
                .antMatchers(HttpMethod.DELETE, "/api/admin/customers/*").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/moderator/products").hasRole("MODERATOR")
                .antMatchers(HttpMethod.GET, "/api/moderator/products/*").hasRole("MODERATOR")
                .antMatchers(HttpMethod.POST, "/api/moderator/products").hasRole("MODERATOR")
                .antMatchers(HttpMethod.PUT, "/api/moderator/products").hasRole("MODERATOR")
                .antMatchers(HttpMethod.DELETE, "/api/moderator/products/*").hasRole("MODERATOR")
                .antMatchers(HttpMethod.GET, "/api/addresses").hasRole("CUSTOMER")
                .antMatchers(HttpMethod.POST, "/api/addresses").hasRole("CUSTOMER")
                .antMatchers(HttpMethod.DELETE, "/api/addresses").hasRole("CUSTOMER")
                .antMatchers(HttpMethod.GET, "/api/addresses/*").hasRole("CUSTOMER")
                .antMatchers(HttpMethod.DELETE, "/api/admin/addresses/*").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/admin/addresses").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/admin/addresses/*").hasRole("ADMIN")
                .and()
                .httpBasic().realmName(REALM).authenticationEntryPoint(exceptionHandler)
                .and()
//                .exceptionHandling().authenticationEntryPoint(exceptionHandler)
//                .and()
                .csrf().disable();
//                .formLogin()
//        .successHandler()
//        .failureHandler();
//        http.addFilterAfter(new AuthFilterExceptionHandler(), BasicAuthenticationFilter.class);
    }
}
