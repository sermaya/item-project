package com.category.item.repository;

import com.category.item.domain.Item;
import com.category.item.exception.ItemNotFoundException;
import com.category.item.repository.jpaentity.CategoryJpaEntity;
import com.category.item.repository.jpaentity.ItemJpaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class ItemJpaAdapterImpl implements ItemJpaAdapter {
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    @Override
    public Item save(Item item) {
        ItemJpaEntity saveEntity = itemRepository.save(new ItemJpaEntity(item.getBrandId(), item.getCategoryId(), item.getPrice()));
        return new Item(saveEntity.getId(), saveEntity.getBrandId(), saveEntity.getCategoryId(), saveEntity.getPrice());
    }

    @Override
    public Item update(Long id, Item item) {
        Optional<ItemJpaEntity> findItem = itemRepository.findById(id);
        if (findItem.isPresent()) {
            findItem.get().setBrandId(item.getBrandId());
            findItem.get().setCategoryId(item.getCategoryId());
            findItem.get().setPrice(item.getPrice());
        } else {
            throw new ItemNotFoundException();
        }

        ItemJpaEntity updateItem = itemRepository.save(findItem.get());

        return itemMapper.mapToDomainEntity(updateItem);
    }

    @Override
    public Item findById(Long itemId) {

        Optional<ItemJpaEntity> itemJpaEntity = itemRepository.findById(itemId);
        if (itemJpaEntity.isPresent()) {
            return itemMapper.mapToDomainEntity(itemJpaEntity.get());
        } else {
            throw new ItemNotFoundException();
        }
    }

    @Override
    public void delete(Long itemId) {
        Optional<ItemJpaEntity> findItem = itemRepository.findById(itemId);
        if (findItem.isPresent()) {
            itemRepository.deleteById(itemId);
        } else {
            throw new ItemNotFoundException();
        }
    }

    @Override
    public Item findByBrandIdAndCategoryId(Long brandId, Long categoryId) {
        Optional<ItemJpaEntity> itemJpaEntity = itemRepository.findItemByBrandIdAndCategoryId(brandId, categoryId);
        if (itemJpaEntity.isPresent()) {
            return itemMapper.mapToDomainEntity(itemJpaEntity.get());
        } else {
            return null;
        }
    }

    @Override
    public List<Item> findByBrandId(Long brandId) {
        return itemRepository.findItemByBrandId(brandId).stream()
                .map(item -> itemMapper.mapToDomainEntity(item)).collect(Collectors.toList());
    }

    @Override
    public List<Item> findByCategoryId(Long categoryId) {
        return itemRepository.findItemByCategoryId(categoryId).stream()
                .map(item -> itemMapper.mapToDomainEntity(item)).collect(Collectors.toList());
    }
}
