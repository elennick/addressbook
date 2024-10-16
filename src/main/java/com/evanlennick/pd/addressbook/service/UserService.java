package com.evanlennick.pd.addressbook.service;

import com.evanlennick.pd.addressbook.httpclient.PagerDutyUsersApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final PagerDutyUsersApiClient client;

    @Autowired
    public UserService(final PagerDutyUsersApiClient client) {
        this.client = client;
    }

    /**
     * Attempts to get a single user by user ID. If the user cannot be found, an empty optional is returned.
     * @param userId
     * @return Optional that will be populated with a UserResource object if the user is obtained.
     */
    public Optional<UserResource> getUser(final String userId) {
        return client.getUser(userId);
    }

    /**
     * Attempts to get all users in the service.
     * @return A collection of users.
     */
    public UserCollectionResource getUsers() {
        return client.getUsers();
    }

    /**
     * Creates a new user resource. This method is NOT YET IMPLEMENTED.
     * @param userToCreate
     * @return
     */
    public UserResource createUser(UserResource userToCreate) {
        throw new UnsupportedOperationException("Not Yet Implemented");
    }
}
