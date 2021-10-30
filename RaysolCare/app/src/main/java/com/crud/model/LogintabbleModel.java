package com.crud.model;

import com.crud.entities.Logintabble;
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

public class LogintabbleModel {
    private String BASE_URL = "http://www.raysolcare.com/api/Loginapi/Post";
    private RestTemplate restTemplate = new RestTemplate();
    public boolean isLoginId = false;

    public String create(Logintabble logintabble){
        try{
            Map<String, String> values = new HashMap<String, String>();
            values.put("Username", logintabble.getUsername());
            values.put("Password", logintabble.getPassword());
            values.put("UserEmail", logintabble.getUserEmail());
            values.put("UserPhoneno", logintabble.getUserPhoneno());
            values.put("Name", logintabble.getName());
            if(isLoginId){
                values.put("LoginId", String.valueOf(logintabble.getLoginId()));
            }else {
                values.put("LoginId", "0");
            }
            JSONObject jsonObject = new JSONObject(values);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> httpEntity = new HttpEntity<String>(jsonObject.toString(), httpHeaders);

            ResponseEntity<String> responseEntity = restTemplate.postForEntity(BASE_URL  , httpEntity, String.class);
            HttpStatus status = responseEntity.getStatusCode();
            String restCall = responseEntity.getBody();
            return restCall;

        }catch (Exception e){
            return  e.getCause().getMessage().toString();
        }
    }
}
