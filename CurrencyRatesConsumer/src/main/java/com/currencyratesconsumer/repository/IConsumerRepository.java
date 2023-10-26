package com.currencyratesconsumer.repository;

import com.currencyratesconsumer.model.CurrencyRatesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IConsumerRepository extends JpaRepository<CurrencyRatesEntity, Long> {
}