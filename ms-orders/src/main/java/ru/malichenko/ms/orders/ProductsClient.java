package ru.malichenko.ms.orders;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("ms-products")
public interface ProductsClient {
    @RequestMapping("/products")
    String getAllProducts();

    @GetMapping("/param/{id}")
    String param(@PathVariable(value = "id") String id);
}
