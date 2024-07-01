package com.category.item.exception;

public class BrandDuplicationException extends  BrandEntityException {
    public BrandDuplicationException() {
        super("등록된 카테고리 엔티티가 존재합니다.");
    }
}
