package ru.malichenko.ms.products.repositories.specifications;


import org.springframework.data.jpa.domain.Specification;
import ru.malichenko.ms.products.entities.Product;

public class ProductSpecifications {
    public static Specification<Product> priceGreaterOrEqualsThan(int minPrice) {// where b.price >= minPrice
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice);
    }

    public static Specification<Product> priceLesserOrEqualsThan(int maxPrice) {// where b.price <= maxPrice
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice);
    }

    public static Specification<Product> titleLike(String titlePart) {// where b.title like %titlePart%
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), String.format("%%%s%%",titlePart));
    }

    public static Specification<Product> categoryIdIs(Long category) {
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("category").get("id"), category);
    }


}
