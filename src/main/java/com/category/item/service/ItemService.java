package com.category.item.service;

import com.category.item.controller.requestdto.BrandRequest;
import com.category.item.controller.requestdto.ItemRequest;
import com.category.item.controller.responsedto.*;

public interface ItemService {

    ItemResponse save(ItemRequest request);

    ItemResponse update(Long id, ItemRequest request);

    void delete(Long id);

    ItemResponse findById(Long id);

}