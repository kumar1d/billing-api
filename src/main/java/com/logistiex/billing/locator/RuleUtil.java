package com.logistiex.billing.locator;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.annotation.PostConstruct;

import com.logistiex.billing.service.dto.Payment;
import com.logistiex.billing.service.dto.PaymentResponse;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;



@Component
@PropertySource(value = "classpath:application.properties")
public class RuleUtil {
    @Autowired
    private RestTemplate template;

    private HttpHeaders headers = null;
    private ObjectMapper mapper = null;
    @Autowired
    private Environment env;

    @PostConstruct
    public void init() {
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        mapper = new ObjectMapper();
    }

    public PaymentResponse processPayment(Payment payment) throws IOException {
        PaymentResponse response = null;
        String jsonRequest = null;
        String result = "";
        String URL = "";

        // Add X-VERIFY header generation logic here
        String base64EncodedPayload = Base64.getEncoder()
                .encodeToString(mapper.writeValueAsString(payment).getBytes());
        String saltKey = env.getProperty("phonepe.salt.key"); // Assuming you have a property for salt key
        int saltIndex = 1; // Assuming salt index is 1 (check documentation for confirmation)
        String xVerify = generateXVerifyHash(base64EncodedPayload, saltKey, saltIndex);
        headers.add("X-VERIFY", xVerify);

        URL = env.getProperty("payment.url");
        jsonRequest = mapper.writeValueAsString(payment);
        HttpEntity<String> entity = new HttpEntity<>(jsonRequest, headers);
        result = template.postForObject(URL, entity, String.class);
        response = mapper.readValue(result, PaymentResponse.class);

        return response;
    }



    private String generateXVerifyHash(String base64Payload, String saltKey, int saltIndex) {
        String hashString = "";
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest((base64Payload + "/pg/v1/pay" + saltKey).getBytes());
            StringBuilder stringBuilder = new StringBuilder(hash.length * 2);
            for (byte b : hash) {
                stringBuilder.append(String.format("%02x", b));
            }
            hashString = stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hashString + "###" + saltIndex;
    }
}
