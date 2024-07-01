package com.category.item.exception;

public class CategoryEntityException extends RuntimeException {
    public CategoryEntityException(String format, Object... args) {
        super(String.format(format, args));
    }
}
