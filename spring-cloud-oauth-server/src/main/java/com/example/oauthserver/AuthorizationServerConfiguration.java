package com.example.oauthserver;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.ProviderSettings;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

@Configuration//(proxyBeanMethods = false)
public class AuthorizationServerConfiguration {

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {
        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
        http.cors().configurationSource(corsConfigurationSource());
        return http.formLogin(Customizer.withDefaults()).build();
    }
    
    
    @Bean
    JWKSource<SecurityContext> jwkSource() throws NoSuchAlgorithmException {
        RSAKey rsaKey = generateRsa();
        JWKSet jwkSet = new JWKSet(rsaKey);
        return (jwkSelector, securityContext) -> jwkSelector.select(jwkSet);
    }
    
    @Bean
    JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwkSource) {
        return OAuth2AuthorizationServerConfiguration.jwtDecoder(jwkSource);
    }
     
    private static RSAKey generateRsa() throws NoSuchAlgorithmException {
        KeyPair keyPair = generateRsaKey();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        return new RSAKey.Builder(publicKey)
            .privateKey(privateKey)
            .keyID(UUID.randomUUID().toString())
            .build();
    }
     
    private static KeyPair generateRsaKey() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        return keyPairGenerator.generateKeyPair();
    }

    @Bean
    ProviderSettings providerSettings() {
      return ProviderSettings.builder()
          .issuer("http://auth-server:9000")
          .build();
    }
    
    @Bean
	RegisteredClientRepository registeredClientRepository() {
	    RegisteredClient registeredClient = RegisteredClient.withId(UUID.randomUUID().toString())
	        .clientId("sentencewebmobile")
	        .clientSecret("{noop}4321")
	        .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_POST)
	        .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
	        //.redirectUri("https://oidcdebugger.com/debug")
	        .redirectUri("http://127.0.0.1:4200")
	        .redirectUri("http://auth-client:9050/authorizationCodeFromSysSentence")
	        .scope(OidcScopes.OPENID)
	        .build();
	    return new InMemoryRegisteredClientRepository(registeredClient);
	}

    @Bean
	UserDetailsService users() {
	    UserDetails user = User.withDefaultPasswordEncoder()
	        .username("admin")
	        .password("123")
	        .roles("ADMIN")
	        .build();
	    return new InMemoryUserDetailsManager(user);
	}
    
    //@Bean
   	CorsConfigurationSource corsConfigurationSource() {
   		CorsConfiguration config = new CorsConfiguration();
   		config.addAllowedOrigin("*");
   		config.addAllowedHeader("*");
   		config.addAllowedMethod("POST");

   		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
   		source.registerCorsConfiguration("/oauth2/**", config);

   		return source;
   	}
  

}

