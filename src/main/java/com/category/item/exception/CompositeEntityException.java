package com.category.item.exception;

public class CompositeEntityException extends RuntimeException {
    public CompositeEntityException(String format, Object... args) {
        super(String.format(format, args));
    }
}
