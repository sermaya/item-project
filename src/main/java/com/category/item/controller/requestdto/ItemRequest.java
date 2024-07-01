package com.category.item.controller.requestdto;

import lombok.Data;

@Data
public class ItemRequest {
    private String brandName;
    private String categoryName;
    private int price;
}
