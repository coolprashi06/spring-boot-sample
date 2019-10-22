package com.prashast.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.embedded.Ssl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/*
https://stackoverflow.com/questions/44969873/spring-boot-key-store-password-set-in-code
 */

@Configuration
@ConditionalOnProperty(name = "cloud.app", havingValue = "false")
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
        final String keystore = getKeystore();
        ssl.setKeyPassword(keystorePassword);
        ssl.setKeyStore(keystore);
        ssl.setEnabled(true);
        serverProperties.setSsl(ssl);
        return serverProperties;
    }

    private String getKeystorePassword() {
        return System.getProperty("keystorePassword");
    }

    private String getKeystore() {
        return System.getProperty("keystoreFile");
    }

}
