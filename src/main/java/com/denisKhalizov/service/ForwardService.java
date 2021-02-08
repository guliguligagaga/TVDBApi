package com.denisKhalizov.service;

import com.denisKhalizov.dto.Series;
import com.denisKhalizov.dto.SeriesResponse;
import com.denisKhalizov.dto.SeriesSearchResponse;
import com.denisKhalizov.dto.SeriesSearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ForwardService {

    final TokenService tokenService;
    final RestTemplate restTemplate;
    @Value("${url.series}")
    private String seriesUrl;
    @Value("${url.search}")
    private String searchUrl;

    @Autowired
    public ForwardService(TokenService tokenService, RestTemplate restTemplate) {
        this.tokenService = tokenService;
        this.restTemplate = restTemplate;
    }

    public Series getSeriesById(int id) {
        return restTemplate.exchange(seriesUrl + "/" + id, HttpMethod.GET,
                tokenService.getEntityWithHeaderToken(), SeriesResponse.class).getBody().getData();
    }

    public List<SeriesSearchResult> getSeries(String name) {
        List<SeriesSearchResult> data = restTemplate.exchange(searchUrl + "?name={name}", HttpMethod.GET, tokenService.getEntityWithHeaderToken(),
                SeriesSearchResponse.class, name)
                .getBody().getData();
        return data;
    }

}
