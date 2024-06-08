package com.kadiraksoy.enoca_task.mapper;


import com.kadiraksoy.enoca_task.dto.request.CreateProductRequest;
import com.kadiraksoy.enoca_task.dto.request.UpdateProductRequest;
import com.kadiraksoy.enoca_task.dto.response.ProductResponse;
import com.kadiraksoy.enoca_task.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {


    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);


   Product createToProduct(CreateProductRequest createProductRequest);
   Product updateToProduct(UpdateProductRequest updateProductRequest);
   ProductResponse toProductResponse(Product product);

}
