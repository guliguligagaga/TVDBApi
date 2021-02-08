package com.denisKhalizov.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class SeriesResponse {

    Series data;

    SeriesResponse() {
    }
}
