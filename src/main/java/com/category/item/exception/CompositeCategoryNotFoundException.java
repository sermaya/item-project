package com.category.item.exception;

public class CompositeCategoryNotFoundException extends CompositeEntityException {
    public CompositeCategoryNotFoundException() {
        super("카테고리를 찾을 수 없습니다. 카테고리 이름을 확인해 주세요.");
    }
}
