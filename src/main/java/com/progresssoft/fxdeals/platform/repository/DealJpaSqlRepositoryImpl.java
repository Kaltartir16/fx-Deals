package com.progresssoft.fxdeals.platform.repository;

import com.progresssoft.fxdeals.domain.model.Deal;
import com.progresssoft.fxdeals.domain.model.DealRequest;
import com.progresssoft.fxdeals.domain.repository.DealRepository;
import lombok.AllArgsConstructor;
import static com.progresssoft.fxdeals.platform.spring.mapper.DealEntityMapper.convertToDeal;
import static com.progresssoft.fxdeals.platform.spring.mapper.DealEntityMapper.convertToEntity;

@AllArgsConstructor
public class DealJpaSqlRepositoryImpl implements DealRepository {

    private final DealJpaRepository repository;

    @Override
    public boolean existsById(String id) {
        return repository.existsById(id);
    }

    @Override
    public Deal save(Deal deal) {
        return convertToDeal(repository.save(convertToEntity(deal)));
    }

    @Override
    public Deal save(DealRequest deal) {
        return convertToDeal(repository.save(convertToEntity(deal)));
    }
}
