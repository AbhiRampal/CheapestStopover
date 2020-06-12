package com.rampal.abhi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.stereotype.Component;

@Component
public class AuthService {

    @Autowired
    OAuth2RestOperations oAuth2RestTemplate;

    public String getBearerAuth(){
        return oAuth2RestTemplate.getAccessToken().getValue();


    }


}
