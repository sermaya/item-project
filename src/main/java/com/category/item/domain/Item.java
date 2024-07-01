package com.category.item.domain;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Item {
    private Long id;
    private Long brandId;
    private Long categoryId;
    private int price;

    public Item(Long brandId, Long categoryId, int price) {
        this.brandId = brandId;
        this.categoryId = categoryId;
        this.price = price;
    }
}
