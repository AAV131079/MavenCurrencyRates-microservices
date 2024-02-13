package com.interfaces.client;

import com.dto.Request.ClientRequestDTO;
import com.dto.Response.BaseClientResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

public interface IClientController
{
    public @ResponseBody ResponseEntity<BaseClientResponseDTO> getForCurrentDay();
    public @ResponseBody ResponseEntity<BaseClientResponseDTO> getForPeriodJson(
            @RequestBody ClientRequestDTO clientRequestDTO);
    public @ResponseBody ResponseEntity<BaseClientResponseDTO> getForPeriodUrl(
            @PathVariable String startDate, @PathVariable String finishDate);
}