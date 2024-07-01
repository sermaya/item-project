package com.category.item.controller;

import com.category.item.controller.responsedto.BrandItemByCategoryNameResponse;
import com.category.item.controller.responsedto.BrandMinPriceResponse;
import com.category.item.controller.responsedto.CategoryBrandMinPriceResponse;
import com.category.item.controller.responsedto.CommonResponse;
import com.category.item.exception.CompositeEntityException;
import com.category.item.exception.ItemEntityException;
import com.category.item.service.CompositeService;
import com.category.item.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CompositeController {
    private final CompositeService compositeService;

    @GetMapping(path = "/composite/category-min")
    public ResponseEntity<CommonResponse> findCategoryMinPriceItem() {
        CategoryBrandMinPriceResponse result = compositeService.findCategoryMinPrice();

        return ResponseEntity
                .ok(CommonResponse.response(HttpStatus.OK, null, result));
    }

    @GetMapping(path = "/composite/brand-min")
    public ResponseEntity<CommonResponse> findBrandMinPriceItem() {
        BrandMinPriceResponse result = compositeService.findBrandMinPrice();

        return ResponseEntity
                .ok(CommonResponse.response(HttpStatus.OK, null, result));
    }

    @GetMapping(path = "/composite/brand-min-max/{categoryName}")
    public ResponseEntity<CommonResponse> findBrandItemByCategoryName(@PathVariable String categoryName) {
        BrandItemByCategoryNameResponse result = compositeService.findBrandItemByCategoryName(categoryName);

        return ResponseEntity.ok(CommonResponse.response(HttpStatus.OK, null, result));
    }

    @ExceptionHandler({CompositeEntityException.class})
    public ResponseEntity<?> handleException(CompositeEntityException exception) {
        return ResponseEntity.ok(CommonResponse.response(HttpStatus.BAD_REQUEST, exception.getMessage(), null));
    }
}
