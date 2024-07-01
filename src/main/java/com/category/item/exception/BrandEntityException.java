package com.category.item.exception;

public class BrandEntityException extends RuntimeException {
    public BrandEntityException(String format, Object... args) {
        super(String.format(format, args));
    }
}
