package com.inventage.keycloak.passwordhashprovider.bcrypt;

import com.google.auto.service.AutoService;
import org.keycloak.Config;
import org.keycloak.credential.hash.PasswordHashProvider;
import org.keycloak.credential.hash.PasswordHashProviderFactory;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;

@AutoService(PasswordHashProviderFactory.class)
public class Typo3BCryptPasswordHashProviderFactory implements PasswordHashProviderFactory {
    public static final String ID = "bcrypt";
    public static final int DEFAULT_ITERATIONS = 12;

    @Override
    public PasswordHashProvider create(KeycloakSession session) {
        return new Typo3BCryptPasswordHashProvider(ID, DEFAULT_ITERATIONS);
    }

    @Override
    public void init(Config.Scope config) {
    }

    @Override
    public void postInit(KeycloakSessionFactory factory) {
    }

    @Override
    public String getId() {
        return ID;
    }

    @Override
    public void close() {
    }
}
