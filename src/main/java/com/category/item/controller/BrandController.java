package com.category.item.controller;

import com.category.item.controller.requestdto.BrandRequest;
import com.category.item.controller.responsedto.BrandResponse;
import com.category.item.controller.responsedto.CommonResponse;
import com.category.item.domain.Brand;
import com.category.item.exception.BrandEntityException;
import com.category.item.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class BrandController {

    private final BrandService brandService;

    @PostMapping(path = "/brand")
    public ResponseEntity<CommonResponse> addBrand(@RequestBody BrandRequest request) {

        BrandResponse result = brandService.save(request);

        return ResponseEntity
                .ok(CommonResponse.response(HttpStatus.CREATED, HttpStatus.CREATED.toString(), result));
    }

    @PutMapping(path = "/brand/{brandId}")
    public ResponseEntity<CommonResponse> updateBrand(@PathVariable Long brandId, @RequestBody BrandRequest request) {
        BrandResponse result = brandService.updateBrand(brandId, request);

        return ResponseEntity
                .ok(CommonResponse.response(HttpStatus.OK, null, result));
    }

    @GetMapping(path = "/brand/{brandName}")
    public ResponseEntity<CommonResponse> findBrandByName(@PathVariable String brandName) {
        BrandResponse result = brandService.findBrand(brandName);

        return ResponseEntity
                .ok(CommonResponse.response(HttpStatus.OK, null, result));
    }

    @DeleteMapping(path = "/brand/{brandId}")
    public ResponseEntity<CommonResponse> deleteBrand(@PathVariable Long brandId) {
        brandService.deleteBrand(brandId);

        return ResponseEntity.ok(CommonResponse.response(HttpStatus.OK, null, null));
    }

    @ExceptionHandler({BrandEntityException.class})
    public ResponseEntity<?> handleException(BrandEntityException exception) {
        return ResponseEntity.ok(CommonResponse.response(HttpStatus.BAD_REQUEST, exception.getMessage(), null));
    }
}

