package com.evanlennick.pd.addressbook;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class AddressbookConfiguration {

    @Bean
    @Qualifier("pagerDutyUsersApiRestClient")
    public RestClient pagerDutyUsersApiRestClient(@Value("${rest.client.pagerduty.users.baseurl}") String baseUrl) {
        return RestClient.builder()
                .baseUrl(baseUrl)
                .build();
    }
}
