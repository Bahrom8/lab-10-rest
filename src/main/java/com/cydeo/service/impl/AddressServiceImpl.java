package com.cydeo.service.impl;

import com.cydeo.client.CountryClient;
import com.cydeo.client.WeatherClient;
import com.cydeo.dto.AddressDTO;
import com.cydeo.dto.country.CountryResponse;
import com.cydeo.dto.weather.WeatherResponse;
import com.cydeo.entity.Address;
import com.cydeo.exception.NotFoundException;
import com.cydeo.repository.AddressRepository;
import com.cydeo.service.AddressService;
import com.cydeo.util.MapperUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Value("${access_key}")
    private String accessKey;
    private final AddressRepository addressRepository;
    private final MapperUtil mapperUtil;
    private final WeatherClient weatherClient;
    private final CountryClient countryClient;

    public AddressServiceImpl(AddressRepository addressRepository, MapperUtil mapperUtil, WeatherClient weatherClient, CountryClient countryClient) {
        this.addressRepository = addressRepository;
        this.mapperUtil = mapperUtil;
        this.weatherClient = weatherClient;
        this.countryClient = countryClient;
    }

    @Override
    public AddressDTO findByAddressNo(String addressNo) {

        Address foundAddress = addressRepository.findByAddressNo(addressNo)
                .orElseThrow(() -> new NotFoundException("No Address Found!"));

        AddressDTO addressDTO = mapperUtil.convert(foundAddress, new AddressDTO());
        //we need to find the temperature for the city information and return it
        addressDTO.setCurrentTemperature(retrieveTemperatureByCity(addressDTO.getCity()));
        //we need to find the flag information for the country and return it
        addressDTO.setFlag(retrieveFlagByCountry(addressDTO.getCountry()));
        return addressDTO;
    }

    private String retrieveFlagByCountry(String country) {
        //we need to make request with country information and return the flag png
        List<CountryResponse> countryInfo = countryClient.getCountryInfo(country);

        return countryInfo.get(0).getFlags().getPng();
    }

    private Integer retrieveTemperatureByCity(String city) {
        //we have city information as a parameter
        //we need to make request to get the weather info
        //find the temperature and return it
        WeatherResponse currentWeather = weatherClient.getCurrentWeather(accessKey, city);

        if(currentWeather ==null ||currentWeather.getCurrent().getTemperature() == null){
            return null;
        }

        //this will return temperature information
        return currentWeather.getCurrent().getTemperature();
    }

    @Override
    public AddressDTO update(String addressNo, AddressDTO address) {

        Address foundAddress = addressRepository.findByAddressNo(addressNo)
                .orElseThrow(() -> new NotFoundException("No Address Found!"));

        Address addressToUpdate = mapperUtil.convert(address, new Address());

        addressToUpdate.setAddressNo(addressNo);
        addressToUpdate.setId(foundAddress.getId());

        Address updatedAddress = addressRepository.save(addressToUpdate);

        return mapperUtil.convert(updatedAddress, new AddressDTO());

    }
}