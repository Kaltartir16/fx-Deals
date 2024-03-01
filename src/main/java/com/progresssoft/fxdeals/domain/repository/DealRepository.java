package com.progresssoft.fxdeals.domain.repository;

import com.progresssoft.fxdeals.domain.model.Deal;
import com.progresssoft.fxdeals.domain.model.DealRequest;

public interface DealRepository  {
    boolean existsById(String id);

    Deal save(Deal deal);

    Deal save(DealRequest deal);

}

