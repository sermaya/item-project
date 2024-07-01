package com.category.item.repository.jpaentity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "category")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryJpaEntity {
    @Id
    @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String name;

    public CategoryJpaEntity(String name) {
        this.name = name;
    }
}
