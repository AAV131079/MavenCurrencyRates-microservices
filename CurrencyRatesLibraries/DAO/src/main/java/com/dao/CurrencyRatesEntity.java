package com.dao;

import com.enumerators.CurrencyEnum;
import com.enumerators.ProviderEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "currency_rates")
public class CurrencyRatesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true, name = "currency_rates_id")
    private Long id;
    @Column(nullable = false, name = "provider_type")
    private ProviderEnum providerType;
    @Column(nullable = false, name = "currency_type")
    private CurrencyEnum currencyType;
    @Column(nullable = false, name = "buy")
    private Float buy;
    @Column(nullable = false, name = "sale")
    private Float sale;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, name = "create_time")
    private Date createTime;

    @Override
    public String toString() {
        return String.format("CurrencyRatesEntity{ providerType = %s, currencyType = %s, buy = %s, sale = %s }",
                providerType, currencyType, buy, sale);
    }

}