package com.crud.model;

import com.crud.entities.Servicesvm;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;

/**
 * Created by Mobashshir on 8/7/2018.
 */

public class ServicesvmModel {
    private String BASE_URL = "http://www.raysolcare.com/api/Servicesapi/";
    private RestTemplate restTemplate = new RestTemplate();

    public boolean create(Servicesvm servicesvm){
        try{
            Map<String, String> values = new HashMap<String, String>();
            values.put("Username", servicesvm.getUsername());
            values.put("EmailId", servicesvm.getEmailId());
            values.put("MobileNo", servicesvm.getMobileNo());
            values.put("Useraddress", servicesvm.getUseraddress());
            values.put("Bookingdate", servicesvm.getBookingdate());
            values.put("ServicesOrdered", servicesvm.getServicesOrdered());
            values.put("Timedata", servicesvm.getTimedata());
            JSONObject jsonObject = new JSONObject(values);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> httpEntity = new HttpEntity<String>(jsonObject.toString(), httpHeaders);

            ResponseEntity<String> responseEntity = restTemplate.postForEntity(BASE_URL  , httpEntity, String.class);
            HttpStatus status = responseEntity.getStatusCode();
            String restCall = responseEntity.getBody();
            return true;

        }catch (Exception e){
            return  false;
        }
    }
}
