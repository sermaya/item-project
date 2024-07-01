package com.category.item.exception;

public class ItemNotFoundException extends ItemEntityException {
    public ItemNotFoundException() {
        super("아이템 엔티티를 찾을 수 없습니다");
    }
}
