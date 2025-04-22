package com.cdp.utils;

import com.cdp.exception.ApiException;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class Utils {
    public static <T> T makePostApiCall(String url, Object request, Class<T> responseType, String authHeader) throws ApiException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        if(authHeader!=null){
            headers.set("Authorization", authHeader);
        }

        HttpEntity<Object> entity = new HttpEntity<>(request, headers);

        ResponseEntity<T> response = restTemplate.exchange(
            url,
            HttpMethod.POST, 
            entity,
            responseType
        );

        if(!response.getStatusCode().is2xxSuccessful())
            throw new ApiException(response.getBody().toString(), response.getStatusCode());
        
        return response.getBody();
    }

    public static <T> T makeGetApiCall(String url, Class<T> responseType, String authHeader) throws ApiException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        if(authHeader!=null){
            headers.set(Constant.AUTHORIZATION, authHeader);
        }

        HttpEntity<Object> entity = new HttpEntity<>(null, headers);

        ResponseEntity<T> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                responseType
        );

        if(!response.getStatusCode().is2xxSuccessful())
            throw new ApiException(response.getBody().toString(), response.getStatusCode());

        return response.getBody();

    }
}
