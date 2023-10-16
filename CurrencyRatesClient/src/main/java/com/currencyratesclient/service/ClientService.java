package com.currencyratesclient.service;

import com.currencyratesclient.dto.Response.CurrencyRate;
import com.currencyratesclient.dto.Response.ClientResponseDTO;
import com.currencyratesclient.enums.CurrencyEnum;
import com.currencyratesclient.ropository.ClientRepository;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    private final static String CURRENT_DATE = "The average exchange rate for today (Privatbank, Monobank, NBU)";
    private final static String PERIOD_DATE = "The average exchange rate for period (Privatbank, Monobank, NBU)";
    private final static String NOT_FOUND = "Nothing was found in the specified period.";
    private final static String TIME_PATTERN_FULL = "yyyy-MM-dd HH:mm:ss";
    private final static String TIME_PATTERN_SHORT = "yyyy-MM-dd";
    private final static String START_TIME_PATTERN = " 00:00:00";
    private final static String FINISH_TIME_PATTERN = " 23:59:59";

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public ClientResponseDTO getCurrentRates() throws ParseException {
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern(TIME_PATTERN_SHORT));
        return getClientResponse(date, date);
    }

    public ClientResponseDTO getPeriodRates(String startDate, String finishDate) throws ParseException {
        return getClientResponse(startDate, finishDate);
    }

    private ClientResponseDTO getClientResponse(String startDate, String finishDate) throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat(TIME_PATTERN_FULL);
        Date start = format.parse(startDate + START_TIME_PATTERN);
        Date finish = format.parse(finishDate + FINISH_TIME_PATTERN);

        ClientResponseDTO clientResponse = new ClientResponseDTO(
                startDate + START_TIME_PATTERN,
                finishDate + FINISH_TIME_PATTERN,
                startDate.equals(finishDate) ? CURRENT_DATE : PERIOD_DATE);

        Float averageBuyByPeriodEUR = clientRepository.averageBuyByPeriod(CurrencyEnum.EUR, start, finish);
        Float averageSaleByPeriodEUR = clientRepository.averageSaleByPeriod(CurrencyEnum.EUR, start, finish);
        Float averageBuyByPeriodUSD = clientRepository.averageBuyByPeriod(CurrencyEnum.USD, start, finish);
        Float averageSaleByPeriodUSD = clientRepository.averageSaleByPeriod(CurrencyEnum.USD, start, finish);

        CurrencyRate currencyRatesEUR = getNewCurrencyRatesDTO(CurrencyEnum.EUR.name(), averageBuyByPeriodEUR, averageSaleByPeriodEUR);
        CurrencyRate currencyRatesUSD = getNewCurrencyRatesDTO(CurrencyEnum.USD.name(), averageBuyByPeriodUSD, averageSaleByPeriodUSD);

        if (Objects.isNull(currencyRatesEUR) && Objects.isNull(currencyRatesUSD)) {
            clientResponse.setCurrencyRates(null);
            clientResponse.setMessage(NOT_FOUND);
        } else {
            CurrencyRate[] currencyRates = new CurrencyRate[] {
                    Objects.nonNull(currencyRatesEUR) ? currencyRatesEUR : null,
                    Objects.nonNull(currencyRatesUSD) ? currencyRatesUSD : null
            };
            clientResponse.setCurrencyRates(currencyRates);
        }

        return clientResponse;

    }

    private CurrencyRate getNewCurrencyRatesDTO(String currency, Float buy, Float sale) {
        if (Objects.nonNull(buy) && buy > 0F || Objects.nonNull(sale) && sale > 0F) {
            return new CurrencyRate(currency, buy, sale);
        } else {
            return null;
        }
    }

}