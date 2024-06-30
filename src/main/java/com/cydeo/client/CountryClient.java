package com.cydeo.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "https://restcountires.com/v3.1", name = "COUNTRY-CLIENT")
public interface CountryClient {

    @GetMapping("/name/{countryName}")
    Object getCountryInfo(@PathVariable("countryName") String countryName);



}
