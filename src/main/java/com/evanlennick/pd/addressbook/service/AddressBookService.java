package com.evanlennick.pd.addressbook.service;

import com.evanlennick.pd.addressbook.httpclient.PagerDutyUsersClient;
import com.evanlennick.pd.addressbook.resource.UserResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressBookService {

    private final PagerDutyUsersClient client;

    @Autowired
    public AddressBookService(final PagerDutyUsersClient client) {
        this.client = client;
    }

    public Optional<UserResource> getUser(final String userId) {
        return client.getUser(userId);
    }

    public List<UserResource> getUsers() {
        throw new UnsupportedOperationException("not implemented yet");
    }
}
