package com.kadiraksoy.enoca_task.mapper;

import com.kadiraksoy.enoca_task.dto.request.CreateCustomerRequest;
import com.kadiraksoy.enoca_task.dto.response.CustomerResponse;
import com.kadiraksoy.enoca_task.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

   Customer toCustomer(CreateCustomerRequest request);
   CustomerResponse toCustomerResponse(Customer customer);
}
