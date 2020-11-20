package ru.malichenko.ms.products.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.malichenko.ms.products.dto.ProductDto;
import ru.malichenko.ms.products.entities.Product;

import java.util.List;
import java.util.Optional;

public interface ProductsController {
    @RequestMapping("/products")
    List<ProductDto> getAllProducts();

    @GetMapping("/param/{id}")
    ProductDto param(@PathVariable(value = "id") Long id);
}
