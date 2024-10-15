package com.evanlennick.pd.addressbook.httpclient;

import com.evanlennick.pd.addressbook.resource.UserResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class PagerDutyUsersClient {

    private final RestClient client;
    private final String authHeaderValue;

    @Autowired
    public PagerDutyUsersClient(@Qualifier("pagerDutyUsersApiRestClient") RestClient client,
                                @Value("${rest.client.pagerduty.apikey}") String authToken) {
        this.client = client;
        this.authHeaderValue = "Token token=" + authToken;
    }

    public Optional<UserResource> getUser(final String userId) {
        ResponseEntity<UserResource> responseEntity = client.get()
                .uri(userId)
                .header("Authorization", authHeaderValue)
                .retrieve()
                .toEntity(UserResource.class);

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            log.debug("Got user with ID {} from PagerDuty API: {}", userId, responseEntity.getBody());
        } else {
            log.debug("Failed to retrieve user from PagerDuty API with ID: {}", userId);
        }

        return Optional.ofNullable(responseEntity.getBody());
    }

    public List<UserResource> getUsers() {
        throw new UnsupportedOperationException("not implemented yet");
    }

}
