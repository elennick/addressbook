package com.evanlennick.pd.addressbook.controller;

import com.evanlennick.pd.addressbook.resource.UserResource;

import java.util.List;

public record UserCollectionResource(List<UserResource> users, int limit, int offset, boolean more, int total) {
}
