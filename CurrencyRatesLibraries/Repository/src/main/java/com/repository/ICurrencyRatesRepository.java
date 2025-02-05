package com.repository;

import com.dao.CurrencyRatesEntity;
import com.enumerators.CurrencyEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface ICurrencyRatesRepository extends JpaRepository<CurrencyRatesEntity, Long> {

    @Query("select AVG(currencyRates.buy) from CurrencyRatesEntity currencyRates " +
            "where currencyRates.createTime between :start and :finish and currencyRates.currencyType = :currencyEnum")
    Float avgBuyByPeriod(CurrencyEnum currencyEnum, Date start, Date finish);

    @Query("select AVG(currencyRates.sale) from CurrencyRatesEntity currencyRates " +
            "where currencyRates.createTime between :start and :finish and currencyRates.currencyType = :currencyEnum")
    Float avgSaleByPeriod(CurrencyEnum currencyEnum, Date start, Date finish);

}