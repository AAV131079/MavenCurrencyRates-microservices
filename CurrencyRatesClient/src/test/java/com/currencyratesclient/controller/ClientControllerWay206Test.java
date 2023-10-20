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
class ClientControllerWay206Test {

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
    void getForCurrentDayWay206Test() throws Exception {

        when(clientRepository.avgSaleByPeriod(CurrencyEnum.USD, startDate, finishDate)).thenReturn(0.0F).toString();
        when(clientRepository.avgSaleByPeriod(CurrencyEnum.EUR, startDate, finishDate)).thenReturn(0.0F).toString();
        when(clientRepository.avgBuyByPeriod(CurrencyEnum.USD, startDate, finishDate)).thenReturn(0.0F).toString();
        when(clientRepository.avgBuyByPeriod(CurrencyEnum.EUR, startDate, finishDate)).thenReturn(0.0F).toString();

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
                .andExpect(jsonPath("$.startDateTime").value(DataHelper.getStartDate(currentDate).toString()))
                .andExpect(jsonPath("$.finishDateTime").value(DataHelper.getFinishDate(currentDate).toString()))
                .andExpect(jsonPath("$.message").value("Nothing was found in the specified period."));

    }

    @Test
    void getForPeriodUrlWay206Test() throws Exception {

        when(clientRepository.avgSaleByPeriod(CurrencyEnum.USD, startDate, finishDate)).thenReturn(0.0F).toString();
        when(clientRepository.avgSaleByPeriod(CurrencyEnum.EUR, startDate, finishDate)).thenReturn(0.0F).toString();
        when(clientRepository.avgBuyByPeriod(CurrencyEnum.USD, startDate, finishDate)).thenReturn(0.0F).toString();
        when(clientRepository.avgBuyByPeriod(CurrencyEnum.EUR, startDate, finishDate)).thenReturn(0.0F).toString();


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
                .andExpect(jsonPath("$.startDateTime").value(DataHelper.getStartDate(currentDate).toString()))
                .andExpect(jsonPath("$.finishDateTime").value(DataHelper.getFinishDate(currentDate).toString()))
                .andExpect(jsonPath("$.message").value("Nothing was found in the specified period."));

    }


    @Test
    void getForPeriodJsonWay206Test() throws Exception {

        when(clientRepository.avgSaleByPeriod(CurrencyEnum.USD, startDate, finishDate)).thenReturn(0.0F).toString();
        when(clientRepository.avgSaleByPeriod(CurrencyEnum.EUR, startDate, finishDate)).thenReturn(0.0F).toString();
        when(clientRepository.avgBuyByPeriod(CurrencyEnum.USD, startDate, finishDate)).thenReturn(0.0F).toString();
        when(clientRepository.avgBuyByPeriod(CurrencyEnum.EUR, startDate, finishDate)).thenReturn(0.0F).toString();

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
                .andExpect(jsonPath("$.startDateTime").value(DataHelper.getStartDate(currentDate).toString()))
                .andExpect(jsonPath("$.finishDateTime").value(DataHelper.getFinishDate(currentDate).toString()))
                .andExpect(jsonPath("$.message").value("Nothing was found in the specified period."));

    }

}