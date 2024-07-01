package com.category.item.exception;

public class BrandNotFoundException extends BrandEntityException {
    public BrandNotFoundException() {
        super("브랜드 엔티티를 찾을 수 없습니다");
    }
}
