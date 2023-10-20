package com.currencyratesclient.controller;

import com.currencyratesclient.dto.Request.RequestDTO;
import com.currencyratesclient.dto.Response.BaseClientResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

public interface IClientController
{
    public @ResponseBody ResponseEntity<BaseClientResponseDTO> getForCurrentDay();
    public @ResponseBody ResponseEntity<BaseClientResponseDTO> getForPeriodJson(@RequestBody RequestDTO requestDTO);
    public @ResponseBody ResponseEntity<BaseClientResponseDTO> getForPeriodUrl(@PathVariable String startDate, @PathVariable String finishDate);
}
