package com.evanlennick.pd.addressbook.service;

import com.evanlennick.pd.addressbook.httpclient.PagerDutyContactMethodResource;
import lombok.Builder;

import java.util.List;

@Builder
public record UserResource(String id, String firstName, String lastName, String self, List<PagerDutyContactMethodResource> contactMethods) {
}
