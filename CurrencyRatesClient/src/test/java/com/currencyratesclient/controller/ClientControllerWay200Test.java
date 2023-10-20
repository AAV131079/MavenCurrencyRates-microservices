package com.currencyratesclient.controller;

import com.currencyratesclient.enums.CurrencyEnum;
import com.currencyratesclient.helper.DataHelper;
import com.currencyratesclient.ropository.ClientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import javax.ws.rs.core.MediaType;
import java.util.Date;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClientController.class)
@ContextConfiguration(classes = ServiceForControllerConfig.class)
class ClientControllerWay200Test {

    private String currentDate = DataHelper.getCurrentDate();
    private Date startDate = DataHelper.getStartDate(currentDate);
    private Date finishDate = DataHelper.getFinishDate(currentDate);
    private String jsonBody = String.format("{\"startDate\":\"%s\",\"finishDate\":\"%s\"}", currentDate, currentDate);

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ApplicationContext context;

    @MockBean
    private ClientRepository clientRepository;

    @Test
    void getForCurrentDayWay200Test() throws Exception {

        when(clientRepository.avgSaleByPeriod(CurrencyEnum.USD, startDate, finishDate)).thenReturn(37.7F).toString();
        when(clientRepository.avgSaleByPeriod(CurrencyEnum.EUR, startDate, finishDate)).thenReturn(41.1F).toString();
        when(clientRepository.avgBuyByPeriod(CurrencyEnum.USD, startDate, finishDate)).thenReturn(35.5F).toString();
        when(clientRepository.avgBuyByPeriod(CurrencyEnum.EUR, startDate, finishDate)).thenReturn(38.8F).toString();

        mockMvc.perform
                (
                    get("/rates/current")
                            .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description").exists())
                .andExpect(jsonPath("$.startDateTime").exists())
                .andExpect(jsonPath("$.finishDateTime").exists())
                .andExpect(jsonPath("$.currencyRates").exists())
                .andExpect(jsonPath("$.currencyRates[0].currency").exists())
                .andExpect(jsonPath("$.currencyRates[1].currency").exists())
                .andExpect(jsonPath("$.currencyRates[0].buy").exists())
                .andExpect(jsonPath("$.currencyRates[1].buy").exists())
                .andExpect(jsonPath("$.currencyRates[0].sale").exists())
                .andExpect(jsonPath("$.currencyRates[1].sale").exists())
                .andExpect(jsonPath("$.description").value("The average exchange rate for today (Privatbank, Monobank, NBU)"))
                .andExpect(jsonPath("$.startDateTime").value(DataHelper.getStartDate(currentDate).toString()))
                .andExpect(jsonPath("$.finishDateTime").value(DataHelper.getFinishDate(currentDate).toString()))
                .andExpect(jsonPath("$.currencyRates[0].currency").value("EUR"))
                .andExpect(jsonPath("$.currencyRates[1].currency").value("USD"))
                .andExpect(jsonPath("$.currencyRates[0].buy").value(38.8F))
                .andExpect(jsonPath("$.currencyRates[1].buy").value(35.5F))
                .andExpect(jsonPath("$.currencyRates[0].sale").value(41.1F))
                .andExpect(jsonPath("$.currencyRates[1].sale").value(37.7F));

    }

    @Test
    void getForPeriodUrlWay200Test() throws Exception {

        when(clientRepository.avgSaleByPeriod(CurrencyEnum.USD, startDate, finishDate)).thenReturn(37.7F).toString();
        when(clientRepository.avgSaleByPeriod(CurrencyEnum.EUR, startDate, finishDate)).thenReturn(41.1F).toString();
        when(clientRepository.avgBuyByPeriod(CurrencyEnum.USD, startDate, finishDate)).thenReturn(35.5F).toString();
        when(clientRepository.avgBuyByPeriod(CurrencyEnum.EUR, startDate, finishDate)).thenReturn(38.8F).toString();


        mockMvc.perform
                (
                    get("/rates//period/start={startDate}/finish={finishDate}", currentDate, currentDate)
                            .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description").exists())
                .andExpect(jsonPath("$.startDateTime").exists())
                .andExpect(jsonPath("$.finishDateTime").exists())
                .andExpect(jsonPath("$.currencyRates").exists())
                .andExpect(jsonPath("$.currencyRates[0].currency").exists())
                .andExpect(jsonPath("$.currencyRates[1].currency").exists())
                .andExpect(jsonPath("$.currencyRates[0].buy").exists())
                .andExpect(jsonPath("$.currencyRates[1].buy").exists())
                .andExpect(jsonPath("$.currencyRates[0].sale").exists())
                .andExpect(jsonPath("$.currencyRates[1].sale").exists())
                .andExpect(jsonPath("$.description").value("The average exchange rate for today (Privatbank, Monobank, NBU)"))
                .andExpect(jsonPath("$.startDateTime").value(DataHelper.getStartDate(currentDate).toString()))
                .andExpect(jsonPath("$.finishDateTime").value(DataHelper.getFinishDate(currentDate).toString()))
                .andExpect(jsonPath("$.currencyRates[0].currency").value("EUR"))
                .andExpect(jsonPath("$.currencyRates[1].currency").value("USD"))
                .andExpect(jsonPath("$.currencyRates[0].buy").value(38.8F))
                .andExpect(jsonPath("$.currencyRates[1].buy").value(35.5F))
                .andExpect(jsonPath("$.currencyRates[0].sale").value(41.1F))
                .andExpect(jsonPath("$.currencyRates[1].sale").value(37.7F));


    }


    @Test
    void getForPeriodJsonWay200Test() throws Exception {

        when(clientRepository.avgSaleByPeriod(CurrencyEnum.USD, startDate, finishDate)).thenReturn(37.7F).toString();
        when(clientRepository.avgSaleByPeriod(CurrencyEnum.EUR, startDate, finishDate)).thenReturn(41.1F).toString();
        when(clientRepository.avgBuyByPeriod(CurrencyEnum.USD, startDate, finishDate)).thenReturn(35.5F).toString();
        when(clientRepository.avgBuyByPeriod(CurrencyEnum.EUR, startDate, finishDate)).thenReturn(38.8F).toString();

        mockMvc.perform
                (
                    get("/rates/period")
                            .content(jsonBody)
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description").exists())
                .andExpect(jsonPath("$.startDateTime").exists())
                .andExpect(jsonPath("$.finishDateTime").exists())
                .andExpect(jsonPath("$.currencyRates").exists())
                .andExpect(jsonPath("$.currencyRates[0].currency").exists())
                .andExpect(jsonPath("$.currencyRates[1].currency").exists())
                .andExpect(jsonPath("$.currencyRates[0].buy").exists())
                .andExpect(jsonPath("$.currencyRates[1].buy").exists())
                .andExpect(jsonPath("$.currencyRates[0].sale").exists())
                .andExpect(jsonPath("$.currencyRates[1].sale").exists())
                .andExpect(jsonPath("$.description").value("The average exchange rate for today (Privatbank, Monobank, NBU)"))
                .andExpect(jsonPath("$.startDateTime").value(DataHelper.getStartDate(currentDate).toString()))
                .andExpect(jsonPath("$.finishDateTime").value(DataHelper.getFinishDate(currentDate).toString()))
                .andExpect(jsonPath("$.currencyRates[0].currency").value("EUR"))
                .andExpect(jsonPath("$.currencyRates[1].currency").value("USD"))
                .andExpect(jsonPath("$.currencyRates[0].buy").value(38.8F))
                .andExpect(jsonPath("$.currencyRates[1].buy").value(35.5F))
                .andExpect(jsonPath("$.currencyRates[0].sale").value(41.1F))
                .andExpect(jsonPath("$.currencyRates[1].sale").value(37.7F));

    }

}