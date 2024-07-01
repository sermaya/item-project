package com.category.item.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Category {
    private Long id;
    private String name;

    public Category(String name) {
        this.name = name;
    }
}
