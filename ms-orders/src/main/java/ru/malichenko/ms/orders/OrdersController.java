package ru.malichenko.ms.orders;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrdersController {
    private final ProductsClient productsClient;

    @RequestMapping("/get-products")
    public String getAllProducts(Model model) {
        String answer = productsClient.getAllProducts();
//        answer = productsClient.param("1000");
//        model.addAttribute("product", answer);
//        return "product-view";
        return answer;
    }
}
