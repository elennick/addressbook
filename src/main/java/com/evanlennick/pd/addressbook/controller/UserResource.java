package com.evanlennick.pd.addressbook.controller;

import java.util.List;

public record UserResource(String id, String firstName, String lastName, List<ContactMethodResource> contactMethods) {
}
