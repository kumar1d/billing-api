package com.logistiex.billing.service;

import com.logistiex.common.config.LogistiexDelegateMapping;
import com.logistiex.common.exception.BadConfigurationException;
import com.logistiex.common.service.remote.RemoteInvocationException;
import com.logistiex.billing.service.dto.UserProfileDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class JanusService {

    private final WebClient webClient;

    private final LogistiexDelegateMapping mappings;

    @Value("${logistiex.header.pod-id}")
    private String xPodId;

    @Value("${logistiex.header.tenant-id}")
    private String xTenantId;

    public JanusService(WebClient webClient, LogistiexDelegateMapping mappings) {
        this.webClient = webClient;
        this.mappings = mappings;
    }

    public String getTargetUri() {
        final Map<String, String> map = mappings.getUri();
        if (map.containsKey("janus")) {
            return map.get("janus") + "/api/";
        } else
            throw new BadConfigurationException("error.missing.delegate", "Unable to find delegate", "logistiex.delegate.service.uri." + getTargetUri());
    }

    public Optional<UserProfileDTO> getUserDetailsByRefId(String refId) {
        final String uri = getTargetUri() + "/user-details/" + xPodId + "/" + refId;
        Mono<ResponseEntity<UserProfileDTO>> retrievedResource = webClient.get()
                .uri(uri)
                .retrieve()
                .toEntity(UserProfileDTO.class);

        try {
            final ResponseEntity<UserProfileDTO> clientResponse = retrievedResource.block();
            assert clientResponse != null;
            return Optional.ofNullable(clientResponse.getBody());
        } catch (Exception ex) {
            log.error("Error while calling janus-service.", ex);
            throw new RemoteInvocationException("Error while calling janus-service: ", ex.getMessage(), ex);
        }
    }
}