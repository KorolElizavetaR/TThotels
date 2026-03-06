package koroler.TThotels.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import koroler.TThotels.entity.Brand;
import koroler.TThotels.repository.BrandRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BrandService {

    private final BrandRepository brandRepository;

    @Transactional
    public Brand findOrCreate(String brandName) {
        return brandRepository.findByBrandName(brandName)
                .orElseGet(() -> {
                    Brand newBrand = new Brand(brandName);
                    return brandRepository.save(newBrand);
                });
    }
}
