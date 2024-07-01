package com.category.item.repository;

import com.category.item.domain.Brand;
import com.category.item.exception.BrandNotFoundException;
import com.category.item.repository.jpaentity.BrandJpaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class BrandJpaAdapterImpl implements BrandJpaAdapter {
    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper;

    @Override
    public Brand save(Brand brand) {
        BrandJpaEntity saveEntity = brandRepository.save(new BrandJpaEntity(brand.getName()));
        return new Brand(saveEntity.getId(), saveEntity.getName());
    }

    @Override
    public Brand findById(Long id) {
        Optional<BrandJpaEntity> brandJpaEntity = brandRepository.findById(id);

        if (brandJpaEntity.isPresent()) {
            return brandMapper.mapToDomainEntity(brandJpaEntity.get());
        } else {
            throw new BrandNotFoundException();
        }
    }


    @Override
    public Brand findByName(String name) {
        Optional<BrandJpaEntity> brandJpaEntity = brandRepository.findBrandByName(name);
        if (brandJpaEntity.isPresent()) {
            return brandMapper.mapToDomainEntity(brandJpaEntity.get());
        } else {
            throw new BrandNotFoundException();
        }
    }

    @Override
    public Brand update(Long id, String name) {
        Optional<BrandJpaEntity> findBrand = brandRepository.findById(id);
        if (findBrand.isPresent()) {
            findBrand.get().setName(name);
        } else {
            throw new BrandNotFoundException();
        }

        BrandJpaEntity updateBrand = brandRepository.save(findBrand.get());

        return brandMapper.mapToDomainEntity(updateBrand);
    }

    @Override
    public void delete(Long id) {
        Optional<BrandJpaEntity> findBrand = brandRepository.findById(id);
        if (findBrand.isPresent()) {
            brandRepository.deleteById(id);
        } else {
            throw new BrandNotFoundException();
        }
    }

    @Override
    public List<Brand> findAll() {
        return brandRepository.findAll().stream()
                .map(brand -> brandMapper.mapToDomainEntity(brand)).collect(Collectors.toList());
    }

    public Map<Long, String> findAllWithMap() {
        return brandRepository.findAll().stream().collect(Collectors.toMap(
                brand -> brand.getId(),
                brand -> brand.getName()
        ));
    }
}
