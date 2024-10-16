package com.evanlennick.pd.addressbook.httpclient;

import com.evanlennick.pd.addressbook.service.UserCollectionResource;
import com.evanlennick.pd.addressbook.service.UserResource;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tupilabs.human_name_parser.HumanNameParserBuilder;
import com.tupilabs.human_name_parser.HumanNameParserParser;
import com.tupilabs.human_name_parser.Name;
import com.tupilabs.human_name_parser.ParseException;

import java.util.Arrays;
import java.util.List;

public class UserResourceAssembler {

    private final static ObjectMapper objectMapper = new ObjectMapper();

    //TODO refactor so this isnt necessary and we can use the other method signature below for all code paths
    public static UserResource toUserResource(JsonNode pdUserResource) {
        Name name = new Name(pdUserResource.get("user").get("name").asText());
        HumanNameParserBuilder builder = new HumanNameParserBuilder(name);
        HumanNameParserParser parser = builder.build();
        final String firstName = parser.getFirst();
        final String lastName = parser.getLast();

        final String id = pdUserResource.get("user").get("id").asText();
        final String self = "http://localhost:8080/api/users/" + id;

        return UserResource.builder()
                .id(id)
                .firstName(firstName)
                .lastName(lastName)
                .build();
    }

    public static UserCollectionResource toUserCollectionResource(JsonNode pdUserCollectionResource) {
        final PagerDutyUserResource[] usersArray = objectMapper.convertValue(
                pdUserCollectionResource.get("users"),
                PagerDutyUserResource[].class);
        final List<UserResource> users =
                Arrays.stream(usersArray)
                        .map(UserResourceAssembler::toUserResource)
                        .toList();

        return UserCollectionResource.builder()
                .users(users)
                .limit(pdUserCollectionResource.get("limit").asInt())
                .offset(pdUserCollectionResource.get("offset").asInt())
                .total(pdUserCollectionResource.get("total").asInt())
                .more(pdUserCollectionResource.get("more").asBoolean())
                .build();
    }

    public static UserResource toUserResource(PagerDutyUserResource user) {
        String firstName = null;
        String lastName = null;
        String self = "http://localhost:8080/api/users/" + user.getId();

        try {
            HumanNameParserBuilder builder = new HumanNameParserBuilder(user.getName());
            HumanNameParserParser parser = builder.build();
            firstName = parser.getFirst();
            lastName = parser.getLast();
        } catch (ParseException e) {
            firstName = user.getName();
        }

        return UserResource.builder()
                .id(user.getId())
                .self(self)
                .firstName(firstName)
                .lastName(lastName)
                .build();
    }
}
