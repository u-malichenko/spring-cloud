package ru.malichenko.ms.products.controllers;

import com.netflix.discovery.EurekaClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.malichenko.ms.products.controllers.ProductsController;
import ru.malichenko.ms.products.dto.ProductDto;
import ru.malichenko.ms.products.entities.Product;
import ru.malichenko.ms.products.exceptions.ResourceNotFoundException;
import ru.malichenko.ms.products.services.ProductService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ProductsControllerImpl implements ProductsController {
    private final EurekaClient eurekaClient;
    private final ProductService productService;

    @Value("${spring.application.name}")
    private String appName;

//    @Value("${userValue}")
//    private String username;

    @Override
    @GetMapping("/products")
    public List<ProductDto> getAllProducts() {
        return productService.findAllProducts().stream().map(ProductDto::new).collect(Collectors.toList());

    }

    @Override
    @GetMapping("/param/{id}")
    public ProductDto param(@PathVariable(value = "id") Long id) {
        Product product = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Unable to find product with id: " + id ));
        return new ProductDto(product);
    }

}
