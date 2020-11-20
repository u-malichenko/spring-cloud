package ru.malichenko.ms.products.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.malichenko.ms.products.dto.ProductDto;
import ru.malichenko.ms.products.entities.Product;
import ru.malichenko.ms.products.exceptions.ResourceCreationException;
import ru.malichenko.ms.products.exceptions.ResourceNotFoundException;
import ru.malichenko.ms.products.services.ProductService;
import ru.malichenko.ms.products.utils.ProductFilter;

import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping(produces = "application/json")
    public Page<ProductDto> getAllProduct(@RequestParam(defaultValue = "1", name = "p") Integer page,
                                       @RequestParam Map<String, String> params) {
        if (page < 1) {
            page = 1;
        }
        System.out.println("params = "+params.toString());
        ProductFilter productFilter = new ProductFilter(params);
        Page<Product> content = productService.findAll(productFilter.getSpec(), page - 1, 5);
        Page<ProductDto> out = new PageImpl<>(content.getContent().stream().map(ProductDto::new).collect(Collectors.toList()), content.getPageable(), content.getTotalElements());
        return out;
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public Product getProductById(@PathVariable Long id) {
        return productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Unable to find product with id: " + id ));
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public Product createProduct(@RequestBody @Validated Product p, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ResourceCreationException("Invalid new product");
        }
        if (p.getId() != null && productService.existsById(p.getId())) {
            throw new ResourceCreationException("Product with id: " + p.getId() + "already exist");
        }
        return productService.saveOrUpdate(p);
    }

    @PutMapping(consumes = "application/json", produces = "application/json")
    public Product updateProduct(@RequestBody Product p) {
        return productService.saveOrUpdate(p);
    }

    @DeleteMapping
    public void deleteProduct() {
        productService.deleteAll();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteProductById(id);
    }
}
