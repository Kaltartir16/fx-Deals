package com.progresssoft.fxdeals.platform.controller;

import com.google.common.flogger.FluentLogger;
import com.progresssoft.fxdeals.domain.model.Deal;
import com.progresssoft.fxdeals.domain.service.DealService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/fxdeals")
@AllArgsConstructor
public class DealController {

    private static FluentLogger log = FluentLogger.forEnclosingClass();

    private final DealService dealService;

    @PostMapping
    public ResponseEntity<Deal> createDeal( @RequestBody Deal deal) {
        log.atInfo().log("Received request to process the deal with ID: %s", deal.getId());

        Deal savedDeal = dealService.importDeal(deal);
        log.atInfo().log("Successfully processed the deal with ID: %s", deal.getId());

        return ResponseEntity.ok(savedDeal);
    }

    @PostMapping("/batch")
    public ResponseEntity<List<Deal>> createFXDeals(@RequestBody List<Deal> fxDeals) {
        log.atInfo().log("Received request to process a batch of FX deals");
        List<Deal> savedDeals = fxDeals.stream()
                .map(dealService::importDeal)
                .collect(Collectors.toList());
        log.atInfo().log("Successfully processed batch of FX deals");
        return ResponseEntity.ok(savedDeals);
    }
}
