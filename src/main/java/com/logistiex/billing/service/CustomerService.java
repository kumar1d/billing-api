package com.logistiex.billing.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.logistiex.common.data.repository.BaseRepository;
import com.logistiex.common.service.BaseCrudService;
import com.logistiex.common.service.mapper.EntityMapper;
import com.logistiex.billing.data.model.Customer;
import com.logistiex.billing.service.dto.CustomerDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.DataInput;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Base64;

@Service
@Slf4j
public class CustomerService extends BaseCrudService<String, CustomerDTO, Customer> {
    private final String JUSPAY_API_URL = "https://api.juspay.in/customers";
    private final String JUSPAY_API_KEY = "AC109F08CD04F9F8A59725C3B860B0";
    private final String MERCHANT_ID = "dtu";
    @Autowired
    private  ObjectMapper objectMapper ;
    public CustomerService(BaseRepository<Customer, String> repository, EntityMapper<CustomerDTO, Customer> mapper) {
        super(repository, mapper);
    }

    @Override
    protected void afterCreate(CustomerDTO dto, Customer entity) {
        super.afterCreate(dto, entity);

        createCustomer(dto);

    }

    public CustomerDTO createCustomer(CustomerDTO customerDTO)  {
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-merchantid", MERCHANT_ID);
        headers.set("Content-Type", "application/x-www-form-urlencoded");
        String auth = JUSPAY_API_KEY + ":" + "";
        byte[] encodedAuth = Base64.getEncoder().encode(
                auth.getBytes(Charset.forName("US-ASCII")) );
        String authHeader = "Basic " + new String( encodedAuth );
        headers.set( "Authorization", authHeader );
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<String, String>();
        requestBody.add("object_reference_id", customerDTO.getObjectReferenceId());
        requestBody.add("mobile_country_code", customerDTO.getMobileCountryCode());
        requestBody.add("mobile_number", customerDTO.getMobileNumber());
        requestBody.add("email_address", customerDTO.getEmailAddress());
        requestBody.add("first_name", customerDTO.getFirstName());
        requestBody.add("last_name", customerDTO.getLastName());
        HttpEntity formEntity = new HttpEntity<MultiValueMap<String, String>>(requestBody, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<CustomerDTO> responseEntity = restTemplate.exchange(
                JUSPAY_API_URL,
                HttpMethod.POST,
                formEntity,
                CustomerDTO.class
        );
        if (responseEntity.getStatusCode() == HttpStatus.CREATED) {
            return responseEntity.getBody();
        } else {
            log.error("Error: {}", responseEntity.getStatusCode());
            return null;
        }
    }
}