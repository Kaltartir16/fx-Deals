package com.progresssoft.fxdeals.platform.spring.configuration;

import com.progresssoft.fxdeals.domain.repository.DealRepository;
import com.progresssoft.fxdeals.domain.service.DealService;
import com.progresssoft.fxdeals.domain.validator.DealRequestValidator;
import com.progresssoft.fxdeals.domain.validator.DealValidator;
import com.progresssoft.fxdeals.platform.repository.DealJpaRepository;
import com.progresssoft.fxdeals.platform.repository.DealJpaSqlRepositoryImpl;
import com.progresssoft.fxdeals.platform.spring.service.DealServiceImpl;
import com.progresssoft.fxdeals.platform.validator.DealRequestValidatorImpl;
import com.progresssoft.fxdeals.platform.validator.DealValidatorImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DealConfiguration {

    @Bean
    public DealService dealService(DealRepository dealRepository, DealValidator dealValidator, DealRequestValidator dealRequestValidator) {
        return new DealServiceImpl(dealRepository,dealRequestValidator,dealValidator);
    }

    @Bean
    public DealRepository dealRepository(final DealJpaRepository repository) {
        return new DealJpaSqlRepositoryImpl(repository);
    }

    @Bean
    public DealValidator dealValidator() {
        return new DealValidatorImpl();

    }
    @Bean
    public DealRequestValidator dealRequestValidator() {
       return new DealRequestValidatorImpl();
    }

}
