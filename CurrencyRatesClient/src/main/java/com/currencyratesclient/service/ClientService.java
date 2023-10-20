package com.currencyratesclient.service;

import com.currencyratesclient.dto.Response.*;
import com.currencyratesclient.enums.CurrencyEnum;
import com.currencyratesclient.helper.DataHelper;
import com.currencyratesclient.ropository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ClientService implements IClientService {

    @Autowired
    private ClientRepository clientRepository;

    private final static String CURRENT_DATE = "The average exchange rate for today (Privatbank, Monobank, NBU)";
    private final static String PERIOD_DATE = "The average exchange rate for period (Privatbank, Monobank, NBU)";
    private final static String NOT_FOUND = "Nothing was found in the specified period.";

    public ClientService() {
    }

    @Override
    public Map<BaseClientResponseDTO, HttpStatus> getForCurrentDay() {
        String currentDate = DataHelper.getCurrentDate();
        return getClientResponse(currentDate, currentDate);
    }

    @Override
    public Map<BaseClientResponseDTO, HttpStatus> getForPeriod(String startDate, String finishDate) {
        return getClientResponse(startDate, finishDate);
    }

    private Map<BaseClientResponseDTO, HttpStatus> getClientResponse(String startDate, String finishDate) {

        List<CurrencyRate> currencyRateList = new ArrayList<>();
        Arrays.stream(CurrencyEnum.values()).toList().forEach(currency -> {
            CurrencyRate currencyRate = null;
            currencyRate = getNewCurrencyRatesDTO
                    (
                    currency.name(),
                    clientRepository.avgBuyByPeriod(currency, DataHelper.getStartDate(startDate), DataHelper.getFinishDate(finishDate)),
                    clientRepository.avgSaleByPeriod(currency, DataHelper.getStartDate(startDate), DataHelper.getFinishDate(finishDate))
                    );
            if(Objects.nonNull(currencyRate)) {
                currencyRateList.add(currencyRate);
            }
        });

        if (currencyRateList.isEmpty()) {
            BaseClientResponseDTO clientResponse = new ShortClientResponseDTO
                    (
                    DataHelper.getStartDate(startDate).toString(),
                    DataHelper.getFinishDate(finishDate).toString(),
                    startDate.equals(finishDate) ? CURRENT_DATE : PERIOD_DATE,
                    NOT_FOUND
                    );
            return createMap(clientResponse, HttpStatus.PARTIAL_CONTENT);
        } else {
            BaseClientResponseDTO clientResponse = new FullClientResponseDTO
                    (
                    DataHelper.getStartDate(startDate).toString(),
                    DataHelper.getFinishDate(finishDate).toString(),
                    startDate.equals(finishDate) ? CURRENT_DATE : PERIOD_DATE,
                   null
                    );
            ((FullClientResponseDTO) clientResponse).setCurrencyRates(currencyRateList.toArray(new CurrencyRate[0]));
            return createMap(clientResponse, HttpStatus.OK);
        }

    }

    private CurrencyRate getNewCurrencyRatesDTO(String currency, Float buy, Float sale) {
        if (Objects.nonNull(buy) && buy > 0F || Objects.nonNull(sale) && sale > 0F) {
            return new CurrencyRate(currency, buy, sale);
        } else {
            return null;
        }
    }

    private Map<BaseClientResponseDTO, HttpStatus> createMap(BaseClientResponseDTO clientResponse, HttpStatus httpStatus) {
        Map<BaseClientResponseDTO, HttpStatus> map = new HashMap<>();
        map.put(clientResponse, httpStatus);
        return map;
    }

}