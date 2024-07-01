package com.category.item.exception;

public class CategoryDuplicationException extends  CategoryEntityException {
    public CategoryDuplicationException() {
        super("등록된 카테고리 엔티티가 존재합니다.");
    }
}
