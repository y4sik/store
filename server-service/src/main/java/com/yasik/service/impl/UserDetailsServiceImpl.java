package com.yasik.service.impl;

import com.yasik.dao.CustomerDAO;
import com.yasik.model.entity.customer.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private CustomerDAO customerDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<Customer> customers = customerDAO.findByUsername(username);
        if (customers.size() == 0) {
            throw new UsernameNotFoundException("User not found.");//add exception
        } else {
            return customers.get(0);

        }
//        org.springframework.security.core.userdetails.User.UserBuilder builder = null;
//        if (customer != null) {
//            builder = org.springframework.security.core.userdetails.User.withUsername(username);
//            builder.disabled(!customer.isAccountNonExpired());
//            builder.disabled(!customer.isAccountNonLocked());
//            builder.disabled(!customer.isCredentialsNonExpired());
//            builder.disabled(!customer.isEnabled());
//            builder.password((customer.getPassword()));
//            String[] authorities = (String[]) user.getAuthorities().toArray();
//            builder.authorities(authorities);
//        } else {
//            throw new UsernameNotFoundException("User not found.");
//        }
//        if (customer != null) {
//            return customer;
//        } else {
//            throw new UsernameNotFoundException("User: " + username + " not found!");
//        }
    }


}
