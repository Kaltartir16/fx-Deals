package com.progresssoft.fxdeals.platform.repository;

import com.progresssoft.fxdeals.domain.repository.DealRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DealJpaRepositoryImpl implements DealRepository {

    private final DealJpaRepository repository;
    @Override
    public boolean existsById(String id) {
        return repository.existsById(id);
    }
}
