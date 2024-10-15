package com.evanlennick.pd.addressbook.controller;

import com.evanlennick.pd.addressbook.resource.UserResource;
import com.evanlennick.pd.addressbook.service.AddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final AddressBookService service;

    @Autowired
    public UserController(final AddressBookService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<UserCollectionResource> getUsers() {
        return ResponseEntity.ok()
                .body(new UserCollectionResource(Collections.EMPTY_LIST, 0, 0, false, 0));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResource> getUser(@PathVariable("id") String userId) {
        return service.getUser(userId)
                .map(userResource -> ResponseEntity.ok().body(userResource))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
