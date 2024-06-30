package com.cydeo.client;

import com.cydeo.dto.country.CountryResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(url = "https://restcountries.com/v3.1",name = "COUNTRY-CLIENT")
public interface CountryClient {

//    https://restcountries.com/v3.1 -->base url
//    /name -->endpoint
//    /france ->path

    @GetMapping("/name/{countryName}")
    List<CountryResponse> getCountryInfo(@PathVariable("countryName") String countryName);
}
