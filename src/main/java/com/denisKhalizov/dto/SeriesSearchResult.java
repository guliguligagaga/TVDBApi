package com.denisKhalizov.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@Builder
@AllArgsConstructor
public class SeriesSearchResult {

    Integer id;
    String seriesName;
    String overview;
    String banner;

    SeriesSearchResult() {
    }
}
