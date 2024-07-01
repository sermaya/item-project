package com.category.item.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Brand {

    private Long id;
    private String name;

    public Brand(String name) {
        this.name = name;
    }
}
