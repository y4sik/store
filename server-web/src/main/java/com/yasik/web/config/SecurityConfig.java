package com.yasik.web.config;

import com.yasik.service.excetpion.handle.GlobalExceptionHandler;
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
    private GlobalExceptionHandler handler;

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
                .antMatchers(HttpMethod.GET, "/api/customers").hasAnyRole("CUSTOMER", "ADMIN", "MODERATOR")
//                .antMatchers(HttpMethod.GET, "/api/customers/*").hasAnyRole("CUSTOMER", "ADMIN", "MODERATOR")
//                .antMatchers(HttpMethod.POST, "/api/customers").hasRole("ADMIN")
//                .antMatchers(HttpMethod.PUT, "/api/customers").hasRole("ADMIN")
//                .antMatchers(HttpMethod.DELETE, "/api/customers").hasRole("ADMIN")
//                .antMatchers(HttpMethod.GET, "/api/products").hasAnyRole("CUSTOMER", "ADMIN", "MODERATOR")
//                .antMatchers(HttpMethod.GET, "/api/products/*").hasAnyRole("CUSTOMER", "ADMIN", "MODERATOR")
//                .antMatchers(HttpMethod.POST, "/api/products").hasRole("MODERATOR")
//                .antMatchers(HttpMethod.PUT, "/api/products").hasRole("MODERATOR")
//                .antMatchers(HttpMethod.DELETE, "/api/products").hasRole("MODERATOR")
                .and()
                .exceptionHandling().authenticationEntryPoint(handler)
                .and()
                .csrf().disable()
                .formLogin().disable();
    }

//    @Override
//    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
//        httpServletResponse.sendError( HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized: Authentication token was either missing or invalid." );
//    }
}
