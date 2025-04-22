package com.cdp.controller;


import com.cdp.domain.model.ApiEvent;
import com.cdp.domain.model.ApiEventStatus;
import com.cdp.domain.model.ApiEventType;
import com.cdp.domain.repository.ApiEventRepository;
import com.cdp.exception.ApiException;
import com.cdp.service.AdpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class OrderController {

    private static final String AUTH_TOKEN = "token bccc00df4e7f11a:57e9df7ccc666f4";

    @Autowired
    private ApiEventRepository apiEventRepository;

    @Autowired
    private AdpService adpService;

    @PostMapping("/order")
    public ResponseEntity<?> createOrder(
            @RequestHeader(value = "Authorization", required = false) String authToken,
            @RequestBody String requestJSON) throws ApiException {
        
        verifyToken(authToken);
        ApiEvent apiEvent = new ApiEvent();
        apiEvent.setEventType(ApiEventType.OUTWARD_ORDER);
        apiEvent.setPayload(requestJSON);
        apiEvent.setStatus(ApiEventStatus.PENDING);
        
        apiEvent = apiEventRepository.save(apiEvent);
        adpService.createOutwardOrder(requestJSON, apiEvent);

        return ResponseEntity.ok(apiEvent);

    }

    @PostMapping("/pack-order")
    public ResponseEntity<?> packOrder(
            @RequestHeader(value = "Authorization", required = false) String authToken,
            @RequestBody String requestJSON) throws ApiException {
        verifyToken(authToken);
        ApiEvent apiEvent = new ApiEvent();
        apiEvent.setEventType(ApiEventType.PACK_ORDER);
        apiEvent.setPayload(requestJSON);
        apiEvent.setStatus(ApiEventStatus.PENDING);

        apiEvent = apiEventRepository.save(apiEvent);
        adpService.packOrder(requestJSON, apiEvent);

        return ResponseEntity.ok(apiEvent);
    }

    @PostMapping("/return")
    public ResponseEntity<?> returnOrder(
            @RequestHeader(value = "Authorization", required = false) String authToken,
            @RequestBody String requestJSON) throws ApiException {
        verifyToken(authToken);
        ApiEvent apiEvent = new ApiEvent();
        apiEvent.setEventType(ApiEventType.RETURN_ORDER);
        apiEvent.setPayload(requestJSON);
        apiEvent.setStatus(ApiEventStatus.PENDING);

        apiEvent = apiEventRepository.save(apiEvent);
        adpService.returnOrder(requestJSON, apiEvent);

        return ResponseEntity.ok(apiEvent);
    }

    @GetMapping("")
    public String hello(){
        return "hi";
    }

    private void verifyToken(String authToken) throws ApiException {
        if(!authToken.equals(AUTH_TOKEN)){
            throw new ApiException("Invalid Auth Token", HttpStatus.UNAUTHORIZED);
        }
    }
}
