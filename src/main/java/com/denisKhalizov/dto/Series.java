package com.denisKhalizov.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class Series {

    Integer id;
    String seriesId;
    String seriesName;
    List<String> aliases;
    String season;
    String poster;
    String banner;
    String fanart;
    String status;
    String firstAired;
    String network;
    String networkId;
    String runtime;
    String language;
    List<String> genre;
    String overview;
    Integer lastUpdated;
    String airsDayOfWeek;
    String airsTime;
    String rating;
    String imdbId;
    String zap2itId;
    String added;
    String addedBy;
    Integer siteRating;
    Integer siteRatingCount;
    String slug;

    Series() {
    }

}
