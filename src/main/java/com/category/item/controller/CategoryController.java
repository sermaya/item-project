package com.category.item.controller;

import com.category.item.controller.requestdto.BrandRequest;
import com.category.item.controller.requestdto.CategoryRequest;
import com.category.item.controller.responsedto.BrandResponse;
import com.category.item.controller.responsedto.CategoryResponse;
import com.category.item.controller.responsedto.CommonResponse;
import com.category.item.exception.BrandEntityException;
import com.category.item.exception.CategoryEntityException;
import com.category.item.service.BrandService;
import com.category.item.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping(path = "/category")
    public ResponseEntity<CommonResponse> addBrand(@RequestBody CategoryRequest request) {

        CategoryResponse result = categoryService.save(request);

        return ResponseEntity
                .ok(CommonResponse.response(HttpStatus.CREATED, HttpStatus.CREATED.toString(), result));
    }

    // 데이터 확인하기 위한 end point
    @GetMapping(path = "/category/{caetgoryName}")
    public ResponseEntity<CommonResponse> findBrandByName(@PathVariable String caetgoryName) {
        CategoryResponse result = categoryService.findCategory(caetgoryName);
        return ResponseEntity
                .ok(CommonResponse.response(HttpStatus.OK, null, result));
    }

    @ExceptionHandler({CategoryEntityException.class})
    public ResponseEntity<?> handleException(CategoryEntityException exception) {
        return ResponseEntity.ok(CommonResponse.response(HttpStatus.BAD_REQUEST, exception.getMessage(), null));
    }
}
