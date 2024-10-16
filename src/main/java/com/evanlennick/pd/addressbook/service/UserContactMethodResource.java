package com.evanlennick.pd.addressbook.service;

import lombok.*;

@Builder
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserContactMethodResource {

    private String id;
    private String type;
    private String summary;
    private String value;

    public UserContactMethodResource(final String id) {
        this.id = id;
    }

    public String getDisplay() {
        //TODO create an enum and make this a bit more structured
        if(type.equals("email_contact_method")) {
            return "Email";
        } else if(type.equals("phone_contact_method")) {
            return "Phone";
        } else if(type.equals("sms_contact_method")) {
            return "SMS";
        } else {
            return "Unknown";
        }
    }

}
