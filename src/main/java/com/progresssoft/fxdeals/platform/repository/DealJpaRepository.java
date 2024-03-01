package com.progresssoft.fxdeals.platform.repository;

import com.progresssoft.fxdeals.platform.spring.model.DealEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DealJpaRepository extends JpaRepository<DealEntity, String> {

}

