package com.evanlennick.pd.addressbook.service;

import lombok.Builder;

import java.util.List;

@Builder
public record UserCollectionResource(List<UserResource> users, int limit, int offset, boolean more, int total) {
}
