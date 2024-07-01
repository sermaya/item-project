package com.category.item.repository;

import com.category.item.domain.Category;
import com.category.item.exception.ItemNotFoundException;
import com.category.item.repository.jpaentity.CategoryJpaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class CategoryJpaAdapterImpl implements CategoryJpaAdapter {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public Category save(Category category) {
        CategoryJpaEntity saveEntity = categoryRepository.save(new CategoryJpaEntity(category.getName()));
        return new Category(saveEntity.getId(), saveEntity.getName());
    }

    @Override
    public Category findById(Long id) {
        Optional<CategoryJpaEntity> categoryJpaEntity = categoryRepository.findById(id);
        if (categoryJpaEntity.isPresent()) {
            return categoryMapper.mapToDomainEntity(categoryJpaEntity.get());
        } else {
            throw new ItemNotFoundException();
        }
    }

    @Override
    public Category findByName(String name) {
        Optional<CategoryJpaEntity> categoryJpaEntity = categoryRepository.findCategoryByName(name);
        if (categoryJpaEntity.isPresent()) {
            return categoryMapper.mapToDomainEntity(categoryJpaEntity.get());
        } else {
            throw new ItemNotFoundException();
        }
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll().stream()
                .map(category -> categoryMapper.mapToDomainEntity(category)).collect(Collectors.toList());
    }
}
