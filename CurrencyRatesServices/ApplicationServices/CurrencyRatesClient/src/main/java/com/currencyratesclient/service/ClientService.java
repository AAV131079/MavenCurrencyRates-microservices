package com.currencyratesclient.service;

import com.dto.Response.BaseClientResponseDTO;
import com.dto.Response.CurrencyRate;
import com.dto.Response.FullClientResponseDTO;
import com.dto.Response.ShortClientResponseDTO;
import com.enumerators.CurrencyEnum;
import com.helpers.data.DataHelper;
import com.interfaces.client.IClientService;
import com.repository.ICurrencyRatesRepository;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@EntityScan("com.dao")
@ComponentScan({"com.helpers", "com.repository"})
public class ClientService implements IClientService {

    private final ICurrencyRatesRepository currencyRatesRepository;
    private final DataHelper dataHelper;

    private final static String CURRENT_DATE = "The average exchange rate for today (Privatbank, Monobank, NBU)";
    private final static String PERIOD_DATE = "The average exchange rate for period (Privatbank, Monobank, NBU)";
    private final static String NOT_FOUND = "Nothing was found in the specified period.";

    @Autowired
    public ClientService(ICurrencyRatesRepository currencyRatesRepository, DataHelper dataHelper) {
        this.currencyRatesRepository = currencyRatesRepository;
        this.dataHelper = dataHelper;
    }

    @Override
    public Map<BaseClientResponseDTO, HttpStatus> getForCurrentDay() {
        String currentDate = dataHelper.getCurrentDate();
        return getClientResponse(currentDate, currentDate);
    }

    @Override
    public Map<BaseClientResponseDTO, HttpStatus> getForPeriod(String startDate, String finishDate) {
        return getClientResponse(startDate, finishDate);
    }

    private @NotNull Map<BaseClientResponseDTO, HttpStatus> getClientResponse(String startDate, String finishDate) {

        List<CurrencyRate> currencyRateList = new ArrayList<>();
        Arrays.stream(CurrencyEnum.values()).toList().forEach(currency -> {
            CurrencyRate currencyRate = null;
            currencyRate = getNewCurrencyRatesDTO
                    (
                    currency.name(),
                    currencyRatesRepository.avgBuyByPeriod(currency, dataHelper.getStartDate(startDate),
                            dataHelper.getFinishDate(finishDate)),
                    currencyRatesRepository.avgSaleByPeriod(currency, dataHelper.getStartDate(startDate),
                            dataHelper.getFinishDate(finishDate))
                    );
            if(Objects.nonNull(currencyRate)) {
                currencyRateList.add(currencyRate);
            }
        });

        if (currencyRateList.isEmpty()) {
            BaseClientResponseDTO clientResponse = new ShortClientResponseDTO
                    (
                    dataHelper.getStartDate(startDate).toString(),
                    dataHelper.getFinishDate(finishDate).toString(),
                    startDate.equals(finishDate) ? CURRENT_DATE : PERIOD_DATE,
                    NOT_FOUND
                    );
            return createMap(clientResponse, HttpStatus.PARTIAL_CONTENT);
        } else {
            BaseClientResponseDTO clientResponse = new FullClientResponseDTO
                    (
                    dataHelper.getStartDate(startDate).toString(),
                    dataHelper.getFinishDate(finishDate).toString(),
                    startDate.equals(finishDate) ? CURRENT_DATE : PERIOD_DATE,
                   null
                    );
            ((FullClientResponseDTO) clientResponse).setCurrencyRates(currencyRateList.toArray(new CurrencyRate[0]));
            return createMap(clientResponse, HttpStatus.OK);
        }

    }

    private @Nullable CurrencyRate getNewCurrencyRatesDTO(String currency, Float buy, Float sale) {
        if (Objects.nonNull(buy) && buy > 0F || Objects.nonNull(sale) && sale > 0F) {
            return new CurrencyRate(currency, buy, sale);
        } else {
            return null;
        }
    }

    private @NotNull Map<BaseClientResponseDTO, HttpStatus> createMap(BaseClientResponseDTO clientResponse,
                                                                      HttpStatus httpStatus) {
        Map<BaseClientResponseDTO, HttpStatus> map = new HashMap<>();
        map.put(clientResponse, httpStatus);
        return map;
    }

}