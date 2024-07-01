package com.category.item.service;

import com.category.item.controller.requestdto.BrandRequest;
import com.category.item.controller.responsedto.BrandResponse;
import com.category.item.domain.Brand;
import com.category.item.exception.BrandDuplicationException;
import com.category.item.repository.BrandJpaAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class BrandServiceImpl  implements BrandService{
    private final BrandJpaAdapter brandJpaAdapter;

    @Transactional
    @Override
    public BrandResponse save(BrandRequest request) {
        Brand findBrand = brandJpaAdapter.findByName(request.getName());
        if (findBrand != null) {
            throw new BrandDuplicationException();
        }

        Brand result = brandJpaAdapter.save(new Brand(request.getName()));

        return BrandResponse.ofDomain(result);
    }

    @Transactional
    @Override
    public BrandResponse updateBrand(Long id, BrandRequest request) {
        Brand result = brandJpaAdapter.update(id, request.getName());

        return BrandResponse.ofDomain(result);
    }

    @Override
    public BrandResponse findBrand(String name) {
        return BrandResponse.ofDomain(brandJpaAdapter.findByName(name));
    }

    @Override
    public void deleteBrand(Long id) {
        brandJpaAdapter.delete(id);
    }
}
