package com.evanlennick.pd.addressbook.httpclient;

import com.evanlennick.pd.addressbook.service.UserCollectionResource;
import com.evanlennick.pd.addressbook.service.UserContactMethodResource;
import com.evanlennick.pd.addressbook.service.UserResource;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import java.util.Optional;

@Component
@Slf4j
public class PagerDutyUsersApiClient {

    private final RestClient client;
    private final String authHeaderValue;

    @Autowired
    public PagerDutyUsersApiClient(@Qualifier("pagerDutyUsersApiRestClient") RestClient client,
                                   @Value("${rest.client.pagerduty.apikey}") String authToken) {
        this.client = client;
        this.authHeaderValue = "Token token=" + authToken;
    }

    public Optional<UserResource> getUser(final String userId) {
        ResponseEntity<JsonNode> responseEntity;
        try {
            responseEntity = client.get()
                    .uri(userId)
                    .header("Authorization", authHeaderValue)
                    .retrieve()
                    .toEntity(JsonNode.class);
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                return Optional.empty();
            } else {
                throw new RuntimeException("Unexpected error");
            }
        }
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            log.info("Got user with ID {} from PagerDuty API: {}", userId, responseEntity.getBody());
            UserResource userResource = ResourceAssembler.toUserResource(responseEntity.getBody());
            return Optional.of(userResource);
        } else {
            log.info("Failed to retrieve user from PagerDuty API with ID: {}", userId);
            return Optional.empty();
        }
    }

    public UserCollectionResource getUsers() {
        final ResponseEntity<JsonNode> responseEntity = client.get()
                .header("Authorization", authHeaderValue)
                .retrieve()
                .toEntity(JsonNode.class);

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            log.info("Got users with from PagerDuty API: {}", responseEntity.getBody());
            return ResourceAssembler.toUserCollectionResource(responseEntity.getBody());
        } else {
            log.info("Failed to retrieve users from PagerDuty user collection API");
            return null; //TODO throw an exception or something instead? empty optional?
        }
    }

    public Optional<UserContactMethodResource> getContactMethod(final String userId, final String cmId) {
        final String path = userId + "/contact_methods/" + cmId;
        final ResponseEntity<JsonNode> responseEntity = client.get()
                .uri(path)
                .header("Authorization", authHeaderValue)
                .retrieve()
                .toEntity(JsonNode.class);

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            log.info("Got contact method from from PagerDuty API: {}", responseEntity.getBody());
            UserContactMethodResource ucmr = ResourceAssembler.toUserContactMethodResource(responseEntity.getBody());
            return Optional.of(ucmr);
        } else {
            log.info("Failed to retrieve contact method from PagerDuty API for user {} and contact method {}", userId, cmId);
            return Optional.empty();
        }
    }

}
