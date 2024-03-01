package com.progresssoft.fxdeals.platform.spring.service;

import com.progresssoft.fxdeals.domain.exception.DuplicateDealException;
import com.progresssoft.fxdeals.domain.model.Deal;
import com.progresssoft.fxdeals.domain.model.DealRequest;
import com.progresssoft.fxdeals.domain.repository.DealRepository;
import com.progresssoft.fxdeals.domain.service.DealService;
import com.progresssoft.fxdeals.domain.validator.DealRequestValidator;
import com.progresssoft.fxdeals.domain.validator.DealValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DealServiceImpl implements DealService {


    private DealRepository dealsRepository;
    private DealRequestValidator dealRequestValidator;
    private DealValidator dealValidator;

    @Override
    public Deal importDeal(Deal deal) {

        dealValidator.validate(deal);
        if (dealsRepository.existsById(deal.getId())) {
            throw new DuplicateDealException("Deal with ID " + deal.getId() + " is already processed.");
        }
        return dealsRepository.save(deal);
    }

    public Deal importDeal(DealRequest dealRequest) {
        dealRequestValidator.validate(dealRequest);
        if (dealsRepository.existsById(dealRequest.getId())) {
            throw new DuplicateDealException("Deal with ID " + dealRequest.getId() + " is already processed.");
        }
        return dealsRepository.save(dealRequest);
    }


}

