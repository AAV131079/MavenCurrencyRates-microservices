package com.interfaces.client;

import com.dto.Response.BaseClientResponseDTO;
import org.springframework.http.HttpStatus;

import java.util.Map;

public interface IClientService {
    public Map<BaseClientResponseDTO, HttpStatus> getForCurrentDay();
    public Map<BaseClientResponseDTO, HttpStatus> getForPeriod(String startDate, String finishDate);
}
