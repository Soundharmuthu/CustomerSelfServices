package com.crud.keycloak;


import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;


import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeyCloakConfig {
	
	@Value("${keycloak.auth-server-url}")
	private String authServerUrl;

	@Value("${admin.username}")
	private String userName;

	@Value("${admin.password}")
	private String password;
	
	

	@Value("${keycloak.token}")
	private String keyCloakToken;
	
	public Keycloak getKeycloakClient() {

		Keycloak keycloak = null;
		try {
			ResteasyClientBuilder resteasyClientBuilder = new ResteasyClientBuilder();
			keycloak = KeycloakBuilder.builder().serverUrl(authServerUrl).grantType(OAuth2Constants.PASSWORD)
					.realm("master").clientId("admin-cli").username(userName).password(password)
					.resteasyClient(resteasyClientBuilder.connectionPoolSize(10).build()).build();
			keycloak.tokenManager().getAccessToken();
		} catch (Exception e) {
			System.out.println(e);
		}
		return keycloak;
	}

}


