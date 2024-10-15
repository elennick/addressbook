package com.evanlennick.pd.addressbook.controller;

import com.evanlennick.pd.addressbook.service.UserCollectionResource;
import com.evanlennick.pd.addressbook.service.UserResource;
import com.evanlennick.pd.addressbook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserApiController {

    private final UserService service;

    @Autowired
    public UserApiController(final UserService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<UserCollectionResource> getUsers() {
        return ResponseEntity.ok()
                .body(service.getUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResource> getUser(@PathVariable("id") String userId) {
        return service.getUser(userId)
                .map(userResource -> ResponseEntity.ok().body(userResource))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
