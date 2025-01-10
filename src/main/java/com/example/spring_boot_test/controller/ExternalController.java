package com.example.spring_boot_test.controller;

import com.example.spring_boot_test.dto.ExternalAddress;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/externals")
public class ExternalController {

    @GetMapping("/get-provinces")
    public ResponseEntity<?> getAllProvince() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://open.oapi.vn/location/provinces?page=0&size=90&query=";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        String bodyJson = response.getBody();
        ExternalAddress externalAddress = new ExternalAddress();
        try {
            externalAddress = objectMapper.readValue(bodyJson, ExternalAddress.class);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(externalAddress, response.getStatusCode());
    }
}
