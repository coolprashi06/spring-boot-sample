package com.prashast.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.embedded.Ssl;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/*
https://stackoverflow.com/questions/44969873/spring-boot-key-store-password-set-in-code
 */

@Component
public class KeystoreInit {

    private final Environment environment;

    @Autowired
    public KeystoreInit(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public ServerProperties serverProperties() {
        final ServerProperties serverProperties = new ServerProperties();
        final Ssl ssl = new Ssl();
        final String keystorePassword = getKeystorePassword();
        ssl.setKeyPassword(keystorePassword);
        System.setProperty("server.ssl.key-store-password", keystorePassword);
        serverProperties.setSsl(ssl);
        return serverProperties;
    }

    private String getKeystorePassword() {
        return System.getProperty("keystorePassword");
    }

}
