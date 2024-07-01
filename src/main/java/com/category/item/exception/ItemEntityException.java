package com.category.item.exception;

public class ItemEntityException extends RuntimeException {
    public ItemEntityException(String format, Object... args) {
        super(String.format(format, args));
    }
}
