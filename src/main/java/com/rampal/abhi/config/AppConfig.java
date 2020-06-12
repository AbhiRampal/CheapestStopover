package com.rampal.abhi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

@EnableOAuth2Client
@Configuration
class AppConfig {

    @Value("${security.oauth2.client.access-token-uri:https://test.api.amadeus.com/v1/security/oauth2/token}")
    private String tokenUrl;

    @Value("${security.oauth2.client.client-id:8atq8f8LWbkGxNsHzzyXaWd8GAG1eNG1}")
    private String clientId;

    @Value("${security.oauth2.client.client-secret:HjcLp15YH3dnmZCT}")
    private String clientSecret;

    @Autowired(required = false)
    ClientHttpRequestFactory clientHttpRequestFactory;

    @Bean
    protected OAuth2RestOperations oAuth2RestTemplate() {

        ClientCredentialsAccessTokenProvider provider = new ClientCredentialsAccessTokenProvider();
        ClientCredentialsResourceDetails resource = new ClientCredentialsResourceDetails();
        resource.setId("1");
        resource.setAccessTokenUri(tokenUrl);
        resource.setClientId(clientId);
        resource.setClientSecret(clientSecret);
        resource.setGrantType("client_credentials");
        resource.setClientAuthenticationScheme(AuthenticationScheme.header);

        OAuth2AccessToken accessToken = provider.obtainAccessToken(resource, new DefaultAccessTokenRequest());
        return new OAuth2RestTemplate(resource, new DefaultOAuth2ClientContext(accessToken));
    }
}


