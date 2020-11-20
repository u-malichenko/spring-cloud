package ru.malichenko.ms.products.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.malichenko.ms.products.entities.Product;

@Data
@NoArgsConstructor
public class ProductDto {

    private Long id;
    private String title;
    private int price;
    private String categoryTitle;

    public ProductDto(Product p) {
        this.id = p.getId();
        this.title = p.getTitle();
        this.price = p.getPrice();
        this.categoryTitle = p.getCategory().getTitle();
    }
}
