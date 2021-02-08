package com.denisKhalizov.api;

import com.denisKhalizov.dto.Series;
import com.denisKhalizov.dto.SeriesSearchResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

public interface TVDBApi {

    @GetMapping("/series/search")
    @ResponseBody
    List<SeriesSearchResult> searchSeriesByName(@RequestParam("name") String name);

    @GetMapping("/series/{id}")
    @ResponseBody
    Series searchSeriesById(@PathVariable int id);
}
