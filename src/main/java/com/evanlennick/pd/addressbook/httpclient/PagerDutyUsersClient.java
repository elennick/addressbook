package com.evanlennick.pd.addressbook.httpclient;

import com.evanlennick.pd.addressbook.service.UserCollectionResource;
import com.evanlennick.pd.addressbook.service.UserResource;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Objects;
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
        final ResponseEntity<JsonNode> responseEntity = client.get()
                .uri(userId)
                .header("Authorization", authHeaderValue)
                .retrieve()
                .toEntity(JsonNode.class);

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            log.info("Got user with ID {} from PagerDuty API: {}", userId, responseEntity.getBody());
            UserResource userResource = UserResourceAssembler.toUserResource(responseEntity.getBody());
            return Optional.of(userResource);
        } else {
            log.info("Failed to retrieve user from PagerDuty API with ID: {}", userId);
            return Optional.empty();
        }
    }

    public UserCollectionResource getUsers() {
        final ResponseEntity<UserCollectionResource> responseEntity = client.get()
                .header("Authorization", authHeaderValue)
                .retrieve()
                .toEntity(UserCollectionResource.class);

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            log.info("Got users with from PagerDuty API: {}", responseEntity.getBody());
        } else {
            log.info("Failed to retrieve users from PagerDuty user collection API");
        }

        return responseEntity.getBody();
    }

}
