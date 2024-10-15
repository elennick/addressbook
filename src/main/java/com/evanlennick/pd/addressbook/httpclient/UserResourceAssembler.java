package com.evanlennick.pd.addressbook.httpclient;

import com.evanlennick.pd.addressbook.service.UserResource;
import com.fasterxml.jackson.databind.JsonNode;
import com.tupilabs.human_name_parser.HumanNameParserBuilder;
import com.tupilabs.human_name_parser.HumanNameParserParser;
import com.tupilabs.human_name_parser.Name;

public class UserResourceAssembler {
    public static UserResource toUserResource(JsonNode pdUserResource) {
        Name name = new Name(pdUserResource.get("user").get("name").asText());
        HumanNameParserBuilder builder = new HumanNameParserBuilder(name);
        HumanNameParserParser parser = builder.build();
        final String firstName = parser.getFirst();
        final String lastName = parser.getLast();

        return UserResource.builder()
                .id(pdUserResource.get("user").get("id").asText())
                .firstName(firstName)
                .lastName(lastName)
                .build();
    }
}
