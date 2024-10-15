package com.evanlennick.pd.addressbook.resource;

import com.evanlennick.pd.addressbook.controller.ContactMethodResource;

import java.util.List;

public record UserResource(String id, String firstName, String lastName, List<ContactMethodResource> contactMethods) {
}
