package com.evanlennick.pd.addressbook.httpclient;

import com.evanlennick.pd.addressbook.service.UserCollectionResource;
import com.evanlennick.pd.addressbook.service.UserContactMethodResource;
import com.evanlennick.pd.addressbook.service.UserResource;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.tupilabs.human_name_parser.HumanNameParserBuilder;
import com.tupilabs.human_name_parser.HumanNameParserParser;
import com.tupilabs.human_name_parser.Name;
import com.tupilabs.human_name_parser.ParseException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ResourceAssembler {

    private final static ObjectMapper objectMapper = new ObjectMapper();

    //TODO refactor so this isnt necessary and we can use the other method signature below for all code paths
    public static UserResource toUserResource(JsonNode pdUserResource) {
        String firstName = null;
        String lastName = null;
        final String id = pdUserResource.get("user").get("id").asText();
        final String self = "http://localhost:8080/api/users/" + id;

        try {
            HumanNameParserBuilder builder = new HumanNameParserBuilder(new Name(pdUserResource.get("user").get("name").asText()));
            HumanNameParserParser parser = builder.build();
            firstName = parser.getFirst();
            lastName = parser.getLast();
        } catch (ParseException e) {
            firstName = pdUserResource.get("user").get("name").asText();
        }

        List<UserContactMethodResource> ucmrList = new ArrayList<>();
        ArrayNode contactMethods = (ArrayNode) pdUserResource.get("user").get("contact_methods");
        for (JsonNode elementNode : contactMethods) {
            final String cmId = elementNode.get("id").asText();
            ucmrList.add(new UserContactMethodResource(cmId));
        }

        return UserResource.builder()
                .id(id)
                .firstName(firstName)
                .lastName(lastName)
                .contactMethods(ucmrList)
                .build();
    }

    public static UserCollectionResource toUserCollectionResource(JsonNode pdUserCollectionResource) {
        final PagerDutyUserResource[] usersArray = objectMapper.convertValue(
                pdUserCollectionResource.get("users"),
                PagerDutyUserResource[].class);
        final List<UserResource> users =
                Arrays.stream(usersArray)
                        .map(ResourceAssembler::toUserResource)
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

    public static UserContactMethodResource toUserContactMethodResource(JsonNode pdContactMethodResource) {
        String id = pdContactMethodResource.get("contact_method").get("id").asText();
        String type = pdContactMethodResource.get("contact_method").get("type").asText();
        String summary = pdContactMethodResource.get("contact_method").get("summary").asText();
        String value = pdContactMethodResource.get("contact_method").get("address").asText();

        return UserContactMethodResource.builder()
                .id(id)
                .type(type)
                .summary(summary)
                .value(value)
                .build();
    }
}
