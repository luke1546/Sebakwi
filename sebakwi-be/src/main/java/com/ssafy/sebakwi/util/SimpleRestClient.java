package com.ssafy.sebakwi.util;

import com.ssafy.sebakwi.wheel.dto.CreateWheelRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Slf4j
@Service
public class SimpleRestClient {
    private static final String URL = "http://localhost:8080/api/checkup_list/data";
    private static final String WEB_HOOK_URL = "https://meeting.ssafy.com/hooks/dgs9xfrf17fhd8p9qbir45amsr";

    public void sendPostRequest(CreateWheelRequest checkupData) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<CreateWheelRequest> requestEntity = new HttpEntity<>(checkupData, headers);
        String response = restTemplate.postForObject(URL, requestEntity, String.class);
        log.info("Response from checkup_list/data={}", response);
    }

    public void notifyWebhook(String message) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String jsonPayload = String.format("{\"text\": \"%s\"}", message);
        HttpEntity<String> requestEntity = new HttpEntity<>(jsonPayload, headers);

        String response = restTemplate.postForObject(WEB_HOOK_URL, requestEntity, String.class);
        log.info("Response from Mattermost: {}", response);
    }
}
