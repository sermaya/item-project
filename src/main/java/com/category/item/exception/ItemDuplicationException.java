package com.category.item.exception;

public class ItemDuplicationException extends  ItemEntityException {
    public ItemDuplicationException() {
        super("등록된 Item 엔티티가 존재합니다.");
    }
}
