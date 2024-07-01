package com.category.item.controller.responsedto;

import com.category.item.domain.Item;
import lombok.Data;

@Data
public class ItemResponse {
    private Long itemId;
    private Long brandId;
    private String brandName;
    private Long categoryId;
    private String categoryName;
    private int price;

    public static ItemResponse ofDomain(Item item) {
        ItemResponse itemResponse = new ItemResponse();
        itemResponse.itemId = item.getId();
        itemResponse.brandId = item.getBrandId();
        itemResponse.categoryId = item.getCategoryId();
        itemResponse.price = item.getPrice();

        return itemResponse;
    }
}
