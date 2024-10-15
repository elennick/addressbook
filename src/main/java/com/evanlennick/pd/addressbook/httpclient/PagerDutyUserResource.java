package com.evanlennick.pd.addressbook.httpclient;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@AllArgsConstructor
@ToString
public class PagerDutyUserResource {

    @JsonProperty("id")
    private final String id;

    @JsonProperty("name")
    private final String name;

    @JsonProperty("contact_methods")
    private final List<PagerDutyContactMethodResource> contactMethods;
}
