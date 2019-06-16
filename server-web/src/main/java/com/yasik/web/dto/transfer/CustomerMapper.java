package com.yasik.web.dto.transfer;

import com.yasik.model.entity.customer.Authorities;
import com.yasik.model.entity.customer.Customer;
import com.yasik.web.dto.CustomerDto;
import com.yasik.web.dto.GenericDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class CustomerMapper implements GenericDTO<Customer, CustomerDto> {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Customer convertToEntity(CustomerDto customerDto) {
        Customer customer = modelMapper.map(customerDto, Customer.class);
        customer.setDateOfBirthday(customerDto.getConvertedDateOfBirthday());
        Set<Authorities> authorities = customer.getAuthorities();
        authorities.forEach(auth -> auth.setCustomer(customer));
        customer.setAuthorities(authorities);
        return customer;
    }

    @Override
    public CustomerDto convertToDto(Customer customer) {
        CustomerDto customerDto = modelMapper.map(customer, CustomerDto.class);
        customerDto.setConvertedDateOfBirthday(customer.getDateOfBirthday());
        return customerDto;
    }
}
