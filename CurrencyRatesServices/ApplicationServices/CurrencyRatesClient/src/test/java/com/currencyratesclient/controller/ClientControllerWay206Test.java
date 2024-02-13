package com.currencyratesclient.controller;

import com.enumerators.CurrencyEnum;
import com.helpers.data.DataHelper;
import com.repository.ICurrencyRatesRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import javax.ws.rs.core.MediaType;
import java.util.Date;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Disabled
@WebMvcTest(ClientController.class)
@ContextConfiguration(classes = ServiceForControllerConfig.class)
@ComponentScan({"com.helpers"})
class ClientControllerWay206Test {

    @Autowired
    private DataHelper dataHelper = null;

    private final String currentDate = dataHelper.getCurrentDate();
    private final Date startDate = dataHelper.getStartDate(currentDate);
    private final Date finishDate = dataHelper.getFinishDate(currentDate);
    private final String jsonBody = String.format("{\"startDate\":\"%s\",\"finishDate\":\"%s\"}", currentDate, currentDate);

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ApplicationContext context;

    @MockBean
    private ICurrencyRatesRepository currencyRatesRepository;

    ClientControllerWay206Test(DataHelper dataHelper) {
        this.dataHelper = dataHelper;
    }

    @Test
    void getForCurrentDayWay206Test() throws Exception {

        when(currencyRatesRepository.avgSaleByPeriod(CurrencyEnum.USD, startDate, finishDate)).thenReturn(0.0F).toString();
        when(currencyRatesRepository.avgSaleByPeriod(CurrencyEnum.EUR, startDate, finishDate)).thenReturn(0.0F).toString();
        when(currencyRatesRepository.avgBuyByPeriod(CurrencyEnum.USD, startDate, finishDate)).thenReturn(0.0F).toString();
        when(currencyRatesRepository.avgBuyByPeriod(CurrencyEnum.EUR, startDate, finishDate)).thenReturn(0.0F).toString();

        mockMvc.perform
                (
                    get("/rates/current")
                            .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().is(206))
                .andExpect(jsonPath("$.description").exists())
                .andExpect(jsonPath("$.startDateTime").exists())
                .andExpect(jsonPath("$.finishDateTime").exists())
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.description").value("The average exchange rate for today (Privatbank, Monobank, NBU)"))
                .andExpect(jsonPath("$.startDateTime").value(dataHelper.getStartDate(currentDate).toString()))
                .andExpect(jsonPath("$.finishDateTime").value(dataHelper.getFinishDate(currentDate).toString()))
                .andExpect(jsonPath("$.message").value("Nothing was found in the specified period."));

    }

    @Test
    void getForPeriodUrlWay206Test() throws Exception {

        when(currencyRatesRepository.avgSaleByPeriod(CurrencyEnum.USD, startDate, finishDate)).thenReturn(0.0F).toString();
        when(currencyRatesRepository.avgSaleByPeriod(CurrencyEnum.EUR, startDate, finishDate)).thenReturn(0.0F).toString();
        when(currencyRatesRepository.avgBuyByPeriod(CurrencyEnum.USD, startDate, finishDate)).thenReturn(0.0F).toString();
        when(currencyRatesRepository.avgBuyByPeriod(CurrencyEnum.EUR, startDate, finishDate)).thenReturn(0.0F).toString();


        mockMvc.perform
                (
                    get("/rates//period/start={startDate}/finish={finishDate}", currentDate, currentDate)
                            .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().is(206))
                .andExpect(jsonPath("$.description").exists())
                .andExpect(jsonPath("$.startDateTime").exists())
                .andExpect(jsonPath("$.finishDateTime").exists())
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.description").value("The average exchange rate for today (Privatbank, Monobank, NBU)"))
                .andExpect(jsonPath("$.startDateTime").value(dataHelper.getStartDate(currentDate).toString()))
                .andExpect(jsonPath("$.finishDateTime").value(dataHelper.getFinishDate(currentDate).toString()))
                .andExpect(jsonPath("$.message").value("Nothing was found in the specified period."));

    }

    @Test
    void getForPeriodJsonWay206Test() throws Exception {

        when(currencyRatesRepository.avgSaleByPeriod(CurrencyEnum.USD, startDate, finishDate)).thenReturn(0.0F).toString();
        when(currencyRatesRepository.avgSaleByPeriod(CurrencyEnum.EUR, startDate, finishDate)).thenReturn(0.0F).toString();
        when(currencyRatesRepository.avgBuyByPeriod(CurrencyEnum.USD, startDate, finishDate)).thenReturn(0.0F).toString();
        when(currencyRatesRepository.avgBuyByPeriod(CurrencyEnum.EUR, startDate, finishDate)).thenReturn(0.0F).toString();

        mockMvc.perform
                (
                    get("/rates/period")
                            .content(jsonBody)
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().is(206))
                .andExpect(jsonPath("$.description").exists())
                .andExpect(jsonPath("$.startDateTime").exists())
                .andExpect(jsonPath("$.finishDateTime").exists())
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.description").value("The average exchange rate for today (Privatbank, Monobank, NBU)"))
                .andExpect(jsonPath("$.startDateTime").value(dataHelper.getStartDate(currentDate).toString()))
                .andExpect(jsonPath("$.finishDateTime").value(dataHelper.getFinishDate(currentDate).toString()))
                .andExpect(jsonPath("$.message").value("Nothing was found in the specified period."));

    }

}