package com.inventage.keycloak.passwordhashprovider.argon;

import com.google.auto.service.AutoService;
import org.keycloak.Config;
import org.keycloak.credential.hash.PasswordHashProvider;
import org.keycloak.credential.hash.PasswordHashProviderFactory;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;

@AutoService(PasswordHashProviderFactory.class)
public class ArgonPasswordHashProviderFactory implements PasswordHashProviderFactory {
    public static final String ID = "argon";

    @Override
    public PasswordHashProvider create(KeycloakSession session) {
        return new ArgonPasswordHashProvider(ID);
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
