package com.progresssoft.fxdeals.domain.service;

import com.progresssoft.fxdeals.domain.model.Deal;
import com.progresssoft.fxdeals.domain.model.DealRequest;


public interface DealService {
	
    Deal importDeal(Deal deal);

     Deal importDeal(DealRequest dealRequest) ;

}

