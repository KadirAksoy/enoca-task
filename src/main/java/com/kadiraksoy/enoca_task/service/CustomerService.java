package com.kadiraksoy.enoca_task.service;


import com.kadiraksoy.enoca_task.dto.request.CreateCustomerRequest;
import com.kadiraksoy.enoca_task.dto.response.CustomerResponse;
import com.kadiraksoy.enoca_task.entity.Cart;
import com.kadiraksoy.enoca_task.entity.Customer;
import com.kadiraksoy.enoca_task.exception.CustomerNotFoundException;
import com.kadiraksoy.enoca_task.mapper.CustomerMapper;
import com.kadiraksoy.enoca_task.repository.CartRepository;
import com.kadiraksoy.enoca_task.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@Slf4j
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CartRepository cartRepository;

    public CustomerService(CustomerRepository customerRepository, CartRepository cartRepository) {
        this.customerRepository = customerRepository;
        this.cartRepository = cartRepository;
    }

    public CustomerResponse createCustomer(CreateCustomerRequest createCustomerRequest) {
        Customer customer = CustomerMapper.INSTANCE.toCustomer(createCustomerRequest);
        customerRepository.save(customer);

        Cart cart = new Cart();
        cart.setCustomer(customer);
        cart.setTotalPrice(0.0);

        cart = cartRepository.save(cart);
        log.info("Cart oluşturuldu: {}", cart);

        customer.setCart(cart);
        customerRepository.save(customer);

        log.info("Customer oluşturuldu: {}", customer);
        return CustomerMapper.INSTANCE.toCustomerResponse(customer);
    }

    public CustomerResponse getCustomer(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(()-> new CustomerNotFoundException(id));
        return CustomerMapper.INSTANCE.toCustomerResponse(customer);
    }


    public List<CustomerResponse> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream().map(CustomerMapper.INSTANCE::toCustomerResponse).toList();
    }




}
