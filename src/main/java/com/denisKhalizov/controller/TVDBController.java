package com.denisKhalizov.controller;

import com.denisKhalizov.api.TVDBApi;
import com.denisKhalizov.dto.SeriesSearchResult;
import com.denisKhalizov.service.ForwardService;
import com.denisKhalizov.dto.Series;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class TVDBController implements TVDBApi {

    final ForwardService forwardService;

    public TVDBController(ForwardService forwardService) {
        this.forwardService = forwardService;
    }

    @Override
    public List<SeriesSearchResult> searchSeriesByName(String name) {
        return forwardService.getSeries(name);
    }

    @Override
    public Series searchSeriesById(int id) {
        return forwardService.getSeriesById(id);
    }

}
