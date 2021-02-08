package com.example.denisKhalizov;

import com.denisKhalizov.dto.Series;
import com.denisKhalizov.dto.SeriesResponse;
import com.denisKhalizov.dto.SeriesSearchResponse;
import com.denisKhalizov.dto.SeriesSearchResult;
import com.denisKhalizov.service.TokenService;
import org.junit.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.*;
import org.springframework.mock.http.client.MockClientHttpResponse;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

public class ApiTest extends AbstractTest {

    @MockBean
    TokenService tokenService;

    @Test
    public void getSeriesById() {
        Series series = Series.builder()
                .id(5000)
                .build();
        SeriesResponse seriesResponse = SeriesResponse.builder().data(series).build();

        ResponseEntity<SeriesResponse> myEntity = new ResponseEntity<>(seriesResponse, HttpStatus.OK);

        when(tokenService.getEntityWithHeaderToken())
                .thenReturn(new HttpEntity<>(new HttpHeaders()));

        when(restTemplate.exchange(
                eq(seriesUrl + "/" + series.getId()),
                eq(HttpMethod.GET),
                any(),
                eq(SeriesResponse.class))
        ).thenReturn(myEntity);

        assertEquals(series.getId(), tvdbApi.searchSeriesById(5000).getId());
    }

    @Test
    public void getSeriesByName() {
        List<SeriesSearchResult> seriesSearchResultList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            seriesSearchResultList.add(SeriesSearchResult.builder()
                    .seriesName("name" + i)
                    .build());
        }

        SeriesSearchResponse seriesSearchResponse = SeriesSearchResponse.builder()
                .data(seriesSearchResultList)
                .build();

        ResponseEntity<SeriesSearchResponse> myEntity = new ResponseEntity<>(seriesSearchResponse, HttpStatus.OK);
        when(restTemplate.exchange(
                eq(searchUrl + "?name={name}"),
                eq(HttpMethod.GET),
                any(),
                eq(SeriesSearchResponse.class),
                eq("testName"))
        ).thenReturn(myEntity);

        assertEquals(seriesSearchResultList.size(), tvdbApi.searchSeriesByName("testName").size());
        assertEquals(seriesSearchResultList, tvdbApi.searchSeriesByName("testName"));
    }

    @Test
    public void getSeriesByNameNotFound() {
        MockClientHttpResponse mockClientHttpResponse = new MockClientHttpResponse(new byte[0], HttpStatus.NOT_FOUND);

        ResponseStatusException responseStatusException = assertThrows(ResponseStatusException.class,
                () -> restTemplateResponseErrorHandler.handleError(mockClientHttpResponse));
        assertEquals(HttpStatus.NOT_FOUND, responseStatusException.getStatus());
    }

    @Test
    public void getSeriesByNameServerError() {
        MockClientHttpResponse mockClientHttpResponse = new MockClientHttpResponse(new byte[0], HttpStatus.INTERNAL_SERVER_ERROR);

        ResponseStatusException responseStatusException = assertThrows(ResponseStatusException.class,
                () -> restTemplateResponseErrorHandler.handleError(mockClientHttpResponse));
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseStatusException.getStatus());
    }

    @Test
    public void getSeriesByNameUnauthorized() {
        MockClientHttpResponse mockClientHttpResponse = new MockClientHttpResponse(new byte[0], HttpStatus.UNAUTHORIZED);

        ResponseStatusException responseStatusException = assertThrows(ResponseStatusException.class,
                () -> restTemplateResponseErrorHandler.handleError(mockClientHttpResponse));
        assertEquals(HttpStatus.UNAUTHORIZED, responseStatusException.getStatus());
    }

}
