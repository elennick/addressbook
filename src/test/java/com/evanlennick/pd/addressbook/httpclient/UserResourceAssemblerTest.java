package com.evanlennick.pd.addressbook.httpclient;

import com.evanlennick.pd.addressbook.service.UserCollectionResource;
import com.evanlennick.pd.addressbook.service.UserResource;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserResourceAssemblerTest {

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void verifyWhenUserIsValid_basicAttributesSerializeCorrectly() throws JsonProcessingException {
        UserResource userResource = UserResourceAssembler
                .toUserResource(exampleUserResponse());

        assertThat(userResource.id()).isEqualTo("PLOASXQ");
        assertThat(userResource.firstName()).isEqualTo("Alan");
        assertThat(userResource.lastName()).isEqualTo("Shepard");
    }

    @Test
    public void verifyWhenUserIsValid_contactMethodsSerializeCorrectly() {

    }

    @Test
    public void verifyWhenCollectionIsReturned_attributesSerializeCorrectly() throws JsonProcessingException {
        UserCollectionResource userCollectionResource = UserResourceAssembler
                .toUserCollectionResource(exampleCollectionResponse());

        assertThat(userCollectionResource.limit()).isEqualTo(25);
        assertThat(userCollectionResource.offset()).isEqualTo(0);
        assertThat(userCollectionResource.total()).isEqualTo(2);
        assertThat(userCollectionResource.users()).hasSize(2);
        assertThat(userCollectionResource.more()).isFalse();
    }

    //TODO move this into an external .json file
    private JsonNode exampleUserResponse() throws JsonProcessingException {
        return mapper.readTree("{\n" +
                "    \"user\": {\n" +
                "        \"name\": \"Alan B. Shepard, Jr.\",\n" +
                "        \"email\": \"alan.shepard@nasa.example\",\n" +
                "        \"time_zone\": \"America/Los_Angeles\",\n" +
                "        \"color\": \"blue-violet\",\n" +
                "        \"avatar_url\": \"https://secure.gravatar.com/avatar/e58b7fdfb50566fd0334b360a05b729c.png?d=mm&r=PG\",\n" +
                "        \"billed\": true,\n" +
                "        \"role\": \"limited_user\",\n" +
                "        \"description\": null,\n" +
                "        \"invitation_sent\": true,\n" +
                "        \"job_title\": null,\n" +
                "        \"teams\": [\n" +
                "            {\n" +
                "                \"id\": \"PQZPQGI\",\n" +
                "                \"type\": \"team_reference\",\n" +
                "                \"summary\": \"North American Space Agency (NASA)\",\n" +
                "                \"self\": \"https://api.pagerduty.com/teams/PQZPQGI\",\n" +
                "                \"html_url\": \"https://pdt-apidocs.pagerduty.com/teams/PQZPQGI\"\n" +
                "            }\n" +
                "        ],\n" +
                "        \"contact_methods\": [\n" +
                "            {\n" +
                "                \"id\": \"PSMLP14\",\n" +
                "                \"type\": \"email_contact_method_reference\",\n" +
                "                \"summary\": \"Default\",\n" +
                "                \"self\": \"https://api.pagerduty.com/users/PLOASXQ/contact_methods/PSMLP14\",\n" +
                "                \"html_url\": null\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": \"P3W47MP\",\n" +
                "                \"type\": \"phone_contact_method_reference\",\n" +
                "                \"summary\": \"Work\",\n" +
                "                \"self\": \"https://api.pagerduty.com/users/PLOASXQ/contact_methods/P3W47MP\",\n" +
                "                \"html_url\": null\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": \"PBXT65T\",\n" +
                "                \"type\": \"sms_contact_method_reference\",\n" +
                "                \"summary\": \"Mobile\",\n" +
                "                \"self\": \"https://api.pagerduty.com/users/PLOASXQ/contact_methods/PBXT65T\",\n" +
                "                \"html_url\": null\n" +
                "            }\n" +
                "        ],\n" +
                "        \"notification_rules\": [\n" +
                "            {\n" +
                "                \"id\": \"PJGOM3Q\",\n" +
                "                \"type\": \"assignment_notification_rule_reference\",\n" +
                "                \"summary\": \"0 minutes: channel PSMLP14\",\n" +
                "                \"self\": \"https://api.pagerduty.com/users/PLOASXQ/notification_rules/PJGOM3Q\",\n" +
                "                \"html_url\": null\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": \"P0K2LG6\",\n" +
                "                \"type\": \"assignment_notification_rule_reference\",\n" +
                "                \"summary\": \"0 minutes: channel PSMLP14\",\n" +
                "                \"self\": \"https://api.pagerduty.com/users/PLOASXQ/notification_rules/P0K2LG6\",\n" +
                "                \"html_url\": null\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": \"PKWQ7KR\",\n" +
                "                \"type\": \"assignment_notification_rule_reference\",\n" +
                "                \"summary\": \"0 minutes: channel P3W47MP\",\n" +
                "                \"self\": \"https://api.pagerduty.com/users/PLOASXQ/notification_rules/PKWQ7KR\",\n" +
                "                \"html_url\": null\n" +
                "            },\n" +
                "            {\n" +
                "                \"id\": \"PW2553L\",\n" +
                "                \"type\": \"assignment_notification_rule_reference\",\n" +
                "                \"summary\": \"0 minutes: channel PBXT65T\",\n" +
                "                \"self\": \"https://api.pagerduty.com/users/PLOASXQ/notification_rules/PW2553L\",\n" +
                "                \"html_url\": null\n" +
                "            }\n" +
                "        ],\n" +
                "        \"coordinated_incidents\": [],\n" +
                "        \"id\": \"PLOASXQ\",\n" +
                "        \"type\": \"user\",\n" +
                "        \"summary\": \"Alan B. Shepard, Jr.\",\n" +
                "        \"self\": \"https://api.pagerduty.com/users/PLOASXQ\",\n" +
                "        \"html_url\": \"https://pdt-apidocs.pagerduty.com/users/PLOASXQ\"\n" +
                "    }\n" +
                "}");
    }

    //TODO move this into an external .json file
    private JsonNode exampleCollectionResponse() throws JsonProcessingException {
        return mapper.readTree("{\n" +
                "    \"users\": [\n" +
                "        {\n" +
                "            \"name\": \"Alan B. Shepard, Jr.\",\n" +
                "            \"email\": \"alan.shepard@nasa.example\",\n" +
                "            \"time_zone\": \"America/Los_Angeles\",\n" +
                "            \"color\": \"blue-violet\",\n" +
                "            \"avatar_url\": \"https://secure.gravatar.com/avatar/e58b7fdfb50566fd0334b360a05b729c.png?d=mm&r=PG\",\n" +
                "            \"billed\": true,\n" +
                "            \"role\": \"limited_user\",\n" +
                "            \"description\": null,\n" +
                "            \"invitation_sent\": true,\n" +
                "            \"job_title\": null,\n" +
                "            \"teams\": [\n" +
                "                {\n" +
                "                    \"id\": \"PQZPQGI\",\n" +
                "                    \"type\": \"team_reference\",\n" +
                "                    \"summary\": \"North American Space Agency (NASA)\",\n" +
                "                    \"self\": \"https://api.pagerduty.com/teams/PQZPQGI\",\n" +
                "                    \"html_url\": \"https://pdt-apidocs.pagerduty.com/teams/PQZPQGI\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"contact_methods\": [\n" +
                "                {\n" +
                "                    \"id\": \"PSMLP14\",\n" +
                "                    \"type\": \"email_contact_method_reference\",\n" +
                "                    \"summary\": \"Default\",\n" +
                "                    \"self\": \"https://api.pagerduty.com/users/PLOASXQ/contact_methods/PSMLP14\",\n" +
                "                    \"html_url\": null\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": \"P3W47MP\",\n" +
                "                    \"type\": \"phone_contact_method_reference\",\n" +
                "                    \"summary\": \"Work\",\n" +
                "                    \"self\": \"https://api.pagerduty.com/users/PLOASXQ/contact_methods/P3W47MP\",\n" +
                "                    \"html_url\": null\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": \"PBXT65T\",\n" +
                "                    \"type\": \"sms_contact_method_reference\",\n" +
                "                    \"summary\": \"Mobile\",\n" +
                "                    \"self\": \"https://api.pagerduty.com/users/PLOASXQ/contact_methods/PBXT65T\",\n" +
                "                    \"html_url\": null\n" +
                "                }\n" +
                "            ],\n" +
                "            \"notification_rules\": [\n" +
                "                {\n" +
                "                    \"id\": \"PJGOM3Q\",\n" +
                "                    \"type\": \"assignment_notification_rule_reference\",\n" +
                "                    \"summary\": \"0 minutes: channel PSMLP14\",\n" +
                "                    \"self\": \"https://api.pagerduty.com/users/PLOASXQ/notification_rules/PJGOM3Q\",\n" +
                "                    \"html_url\": null\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": \"P0K2LG6\",\n" +
                "                    \"type\": \"assignment_notification_rule_reference\",\n" +
                "                    \"summary\": \"0 minutes: channel PSMLP14\",\n" +
                "                    \"self\": \"https://api.pagerduty.com/users/PLOASXQ/notification_rules/P0K2LG6\",\n" +
                "                    \"html_url\": null\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": \"PKWQ7KR\",\n" +
                "                    \"type\": \"assignment_notification_rule_reference\",\n" +
                "                    \"summary\": \"0 minutes: channel P3W47MP\",\n" +
                "                    \"self\": \"https://api.pagerduty.com/users/PLOASXQ/notification_rules/PKWQ7KR\",\n" +
                "                    \"html_url\": null\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": \"PW2553L\",\n" +
                "                    \"type\": \"assignment_notification_rule_reference\",\n" +
                "                    \"summary\": \"0 minutes: channel PBXT65T\",\n" +
                "                    \"self\": \"https://api.pagerduty.com/users/PLOASXQ/notification_rules/PW2553L\",\n" +
                "                    \"html_url\": null\n" +
                "                }\n" +
                "            ],\n" +
                "            \"coordinated_incidents\": [],\n" +
                "            \"id\": \"PLOASXQ\",\n" +
                "            \"type\": \"user\",\n" +
                "            \"summary\": \"Alan B. Shepard, Jr.\",\n" +
                "            \"self\": \"https://api.pagerduty.com/users/PLOASXQ\",\n" +
                "            \"html_url\": \"https://pdt-apidocs.pagerduty.com/users/PLOASXQ\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": \"Aleksei Sorokin\",\n" +
                "            \"email\": \"aleksei.sorokin@ussr.example\",\n" +
                "            \"time_zone\": \"America/Los_Angeles\",\n" +
                "            \"color\": \"deep-pink\",\n" +
                "            \"avatar_url\": \"https://secure.gravatar.com/avatar/4dfb04e6437da746733f296cac5e27a9.png?d=mm&r=PG\",\n" +
                "            \"billed\": true,\n" +
                "            \"role\": \"limited_user\",\n" +
                "            \"description\": null,\n" +
                "            \"invitation_sent\": false,\n" +
                "            \"job_title\": null,\n" +
                "            \"teams\": [\n" +
                "                {\n" +
                "                    \"id\": \"PGVXG6U\",\n" +
                "                    \"type\": \"team_reference\",\n" +
                "                    \"summary\": \"Space Cosmonauts\",\n" +
                "                    \"self\": \"https://api.pagerduty.com/teams/PGVXG6U\",\n" +
                "                    \"html_url\": \"https://pdt-apidocs.pagerduty.com/teams/PGVXG6U\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"contact_methods\": [\n" +
                "                {\n" +
                "                    \"id\": \"PYWFKTH\",\n" +
                "                    \"type\": \"email_contact_method_reference\",\n" +
                "                    \"summary\": \"Default\",\n" +
                "                    \"self\": \"https://api.pagerduty.com/users/PJ29Q1C/contact_methods/PYWFKTH\",\n" +
                "                    \"html_url\": null\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": \"PLDI27U\",\n" +
                "                    \"type\": \"phone_contact_method_reference\",\n" +
                "                    \"summary\": \"Work\",\n" +
                "                    \"self\": \"https://api.pagerduty.com/users/PJ29Q1C/contact_methods/PLDI27U\",\n" +
                "                    \"html_url\": null\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": \"PEV90FE\",\n" +
                "                    \"type\": \"sms_contact_method_reference\",\n" +
                "                    \"summary\": \"Mobile\",\n" +
                "                    \"self\": \"https://api.pagerduty.com/users/PJ29Q1C/contact_methods/PEV90FE\",\n" +
                "                    \"html_url\": null\n" +
                "                }\n" +
                "            ],\n" +
                "            \"notification_rules\": [\n" +
                "                {\n" +
                "                    \"id\": \"PRXUWU4\",\n" +
                "                    \"type\": \"assignment_notification_rule_reference\",\n" +
                "                    \"summary\": \"0 minutes: channel PYWFKTH\",\n" +
                "                    \"self\": \"https://api.pagerduty.com/users/PJ29Q1C/notification_rules/PRXUWU4\",\n" +
                "                    \"html_url\": null\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": \"PV6FX6U\",\n" +
                "                    \"type\": \"assignment_notification_rule_reference\",\n" +
                "                    \"summary\": \"0 minutes: channel PYWFKTH\",\n" +
                "                    \"self\": \"https://api.pagerduty.com/users/PJ29Q1C/notification_rules/PV6FX6U\",\n" +
                "                    \"html_url\": null\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": \"PREOLPW\",\n" +
                "                    \"type\": \"assignment_notification_rule_reference\",\n" +
                "                    \"summary\": \"0 minutes: channel PLDI27U\",\n" +
                "                    \"self\": \"https://api.pagerduty.com/users/PJ29Q1C/notification_rules/PREOLPW\",\n" +
                "                    \"html_url\": null\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": \"P7VCJKG\",\n" +
                "                    \"type\": \"assignment_notification_rule_reference\",\n" +
                "                    \"summary\": \"0 minutes: channel PEV90FE\",\n" +
                "                    \"self\": \"https://api.pagerduty.com/users/PJ29Q1C/notification_rules/P7VCJKG\",\n" +
                "                    \"html_url\": null\n" +
                "                }\n" +
                "            ],\n" +
                "            \"coordinated_incidents\": [],\n" +
                "            \"id\": \"PJ29Q1C\",\n" +
                "            \"type\": \"user\",\n" +
                "            \"summary\": \"Aleksei Sorokin\",\n" +
                "            \"self\": \"https://api.pagerduty.com/users/PJ29Q1C\",\n" +
                "            \"html_url\": \"https://pdt-apidocs.pagerduty.com/users/PJ29Q1C\"\n" +
                "        }" +
                "    ],\n" +
                "    \"limit\": 25,\n" +
                "    \"offset\": 0,\n" +
                "    \"total\": 2,\n" +
                "    \"more\": false\n" +
                "}");
    }
}