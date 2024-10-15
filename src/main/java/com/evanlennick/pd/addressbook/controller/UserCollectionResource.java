package com.evanlennick.pd.addressbook.controller;

import java.util.List;

public record UserCollectionResource(List<UserResource> users, int limit, int offset, boolean more, int total) {
}
