package com.logistiex.billing.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.logistiex.billing.service.dto.JuspayResponseDTO;
import com.logistiex.common.data.repository.BaseRepository;
import com.logistiex.common.service.BaseCrudService;
import com.logistiex.common.service.mapper.EntityMapper;
import com.logistiex.billing.data.model.Session;
import com.logistiex.billing.service.dto.SessionDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.nio.charset.Charset;
import java.util.Base64;

@Service
@Slf4j
public class SessionService extends BaseCrudService<String, SessionDTO, Session> {

    private final String JUSPAY_API_URL = "https://api.juspay.in/session";
    private final String JUSPAY_API_KEY = "AC109F08CD04F9F8A59725C3B860B0";
    private final String MERCHANT_ID = "dtu";
    private final String CLIENT_ID = "dtudtu";

    @Autowired
    private ObjectMapper objectMapper;

    public SessionService(BaseRepository<Session, String> repository, EntityMapper<SessionDTO, Session> mapper) {
        super(repository, mapper);
    }

    @Override
    protected SessionDTO beforeMapping(SessionDTO dto, Method method) {
        final JuspayResponseDTO juspayResponseDTO = createSession(dto);
        if(juspayResponseDTO != null){
            log.debug("juspay response: {} ", juspayResponseDTO);
            dto.setJuspayOrderId(juspayResponseDTO.getId());
            dto.setPaymentLinks(juspayResponseDTO.getPaymentLinks().getWeb());
            dto.setStatus(juspayResponseDTO.getStatus());
        }
        return super.beforeMapping(dto, method);
    }
//    @Override
//    protected void afterCreate(SessionDTO dto, Session entity) {
//        super.afterCreate(dto, entity);
//        final JuspayResponseDTO juspayResponseDTO = createSession(dto);
//        log.debug("juspay response: {} ", juspayResponseDTO);
//    }

    public JuspayResponseDTO createSession(SessionDTO sessionDTO) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-merchantid", MERCHANT_ID);
        headers.set("Content-Type", "application/json");

        String auth = JUSPAY_API_KEY + ":";
        byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(Charset.forName("US-ASCII")));
        String authHeader = "Basic " + new String(encodedAuth);
        headers.set("Authorization", authHeader);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());

        HttpEntity<SessionDTO> requestEntity = new HttpEntity<>(sessionDTO, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<JuspayResponseDTO> responseEntity = restTemplate.exchange(
                JUSPAY_API_URL,
                HttpMethod.POST,
                requestEntity,
                JuspayResponseDTO.class
        );

        HttpStatusCode statusCode = responseEntity.getStatusCode();

        if (statusCode == HttpStatus.OK) {
            log.debug("body: {}", responseEntity.getBody());
            return responseEntity.getBody();
        } else if (statusCode == HttpStatus.BAD_REQUEST) {
            log.error("Error: Invalid Input data. Details of keys missing: {}", responseEntity.getBody());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Input data. Details of keys missing: " + responseEntity.getBody());
        } else if (statusCode == HttpStatus.UNAUTHORIZED) {
            log.error("Error: Authentication Failed. {}", responseEntity.getBody());
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Authentication Failed. " + responseEntity.getBody());
        } else if (statusCode == HttpStatus.NOT_FOUND) {
            log.error("Error: Not Found. {}", responseEntity.getBody());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found. " + responseEntity.getBody());
        } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR) {
            log.error("Error: Unexpected Error. {}", responseEntity.getBody());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected Error. " + responseEntity.getBody());
        } else {
            log.error("Error: {}. Response Body: {}", statusCode, responseEntity.getBody());
            throw new ResponseStatusException(statusCode, "Error occurred. " + responseEntity.getBody());
        }
    }

}