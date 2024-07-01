package com.category.item.controller;

import com.category.item.controller.requestdto.BrandRequest;
import com.category.item.controller.requestdto.CategoryRequest;
import com.category.item.controller.requestdto.ItemRequest;
import com.category.item.controller.responsedto.*;
import com.category.item.exception.CategoryEntityException;
import com.category.item.exception.CompositeEntityException;
import com.category.item.exception.ItemEntityException;
import com.category.item.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @PostMapping(path = "/item")
    public ResponseEntity<CommonResponse> addItem(@RequestBody ItemRequest request) {

        ItemResponse result = itemService.save(request);

        return ResponseEntity
                .ok(CommonResponse.response(HttpStatus.CREATED, HttpStatus.CREATED.toString(), result));
    }

    @PutMapping(path = "/item/{itemId}")
    public ResponseEntity<CommonResponse> updateBrand(@PathVariable Long itemId, @RequestBody ItemRequest request) {
        ItemResponse result = itemService.update(itemId, request);

        return ResponseEntity
                .ok(CommonResponse.response(HttpStatus.OK, null, result));
    }

    @GetMapping(path = "/item/{itemId}")
    public ResponseEntity<CommonResponse> findBrandByName(@PathVariable Long itemId) {
        ItemResponse result = itemService.findById(itemId);

        return ResponseEntity
                .ok(CommonResponse.response(HttpStatus.OK, null, result));
    }

    @DeleteMapping(path = "/item/{itemId}")
    public ResponseEntity<CommonResponse> deleteBrand(@PathVariable Long itemId) {
        itemService.delete(itemId);

        return ResponseEntity.ok(CommonResponse.response(HttpStatus.OK, null, null));
    }

    @ExceptionHandler({ItemEntityException.class})
    public ResponseEntity<?> handleException(ItemEntityException exception) {
        return ResponseEntity.ok(CommonResponse.response(HttpStatus.BAD_REQUEST, exception.getMessage(), null));
    }

}
