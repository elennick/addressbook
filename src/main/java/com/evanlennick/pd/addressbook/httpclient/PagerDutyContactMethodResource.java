package com.evanlennick.pd.addressbook.httpclient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;

@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public record PagerDutyContactMethodResource(String id, String type, String summary) {
}
