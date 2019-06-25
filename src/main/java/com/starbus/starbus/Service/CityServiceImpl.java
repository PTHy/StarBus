package com.starbus.starbus.Service;

import com.starbus.starbus.Domain.City;
import com.starbus.starbus.OpenResponse.Header;
import com.starbus.starbus.OpenResponse.OpenModel;
import com.starbus.starbus.OpenResponse.Response;
import com.starbus.starbus.OpenResponse.ResponseModel.ResponseCity;
import com.starbus.starbus.Repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

@Service
public class CityServiceImpl implements CityService{

    @Autowired
    private CityRepository cityRepository;

    private RestTemplate restTemplate = new RestTemplate();

    @Value("${data.serviceKey}")
    private String serviceKey;

    @Override
    public List<City> getCities() {
        return this.cityRepository.findAll();
    }

    @Override
    public boolean syncCities() {
        try {
            String url = "http://openapi.tago.go.kr/openapi/service/ArvlInfoInqireService/getCtyCodeList?serviceKey="+serviceKey+"&_type=json";

            System.out.println(URLEncoder.encode(url, "UTF-8"));
            System.out.println(url);

            OpenModel result = restTemplate.getForObject(URI.create(url), OpenModel.class);

            System.out.println(result.toString());


            if (result != null) {
                Response<ResponseCity> response = result.getResponse();
                Header header = response.getHeader();

                if (header.getResultCode() != 00) {
                    System.out.println("실패했어요오 : " + header.getResultMsg());
                    return false;
                }
                System.out.println(response.getBody().getItems().getItem().toString());
//                List<ResponseCity> cities = response.getBody().getItems().getItem();
//
//                for (ResponseCity city : cities) {
//                    System.out.println(city.getCityCode() + " " + city.getCityName());
//                }
            }

            System.out.println("no result");

            return true;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return false;
        }
    }
}
