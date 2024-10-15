package com.evanlennick.pd.addressbook.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping
    public ResponseEntity<UserCollectionResource> getUsers() {
        return ResponseEntity.ok()
                .body(new UserCollectionResource(Collections.EMPTY_LIST, 0, 0, false, 0));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResource> getUser() {
        return ResponseEntity.ok()
                .body(new UserResource("123", "Jim", "Tester", Collections.EMPTY_LIST));
    }

}
