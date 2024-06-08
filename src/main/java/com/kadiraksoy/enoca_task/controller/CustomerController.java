package com.kadiraksoy.enoca_task.controller;

import com.kadiraksoy.enoca_task.dto.request.CreateCustomerRequest;
import com.kadiraksoy.enoca_task.dto.response.CustomerResponse;
import com.kadiraksoy.enoca_task.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/create")
    public ResponseEntity<CustomerResponse> createCustomer(@RequestBody CreateCustomerRequest createCustomerRequest) {
        CustomerResponse customerResponse = customerService.createCustomer(createCustomerRequest);
        return ResponseEntity.status(201).body(customerResponse);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<CustomerResponse> getCustomer(@PathVariable Long id) {
        CustomerResponse customerResponse = customerService.getCustomer(id);
        return ResponseEntity.ok(customerResponse);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<CustomerResponse>> getAllCustomers() {
        List<CustomerResponse> customerResponses = customerService.getAllCustomers();
        return ResponseEntity.ok(customerResponses);
    }


}
