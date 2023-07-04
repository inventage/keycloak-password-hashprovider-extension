package com.inventage.keycloak.passwordhashprovider.argon;

import com.webauthn4j.util.exception.NotImplementedException;
import org.keycloak.credential.hash.PasswordHashProvider;
import org.keycloak.models.PasswordPolicy;
import org.keycloak.models.credential.PasswordCredentialModel;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;


public class ArgonPasswordHashProvider implements PasswordHashProvider {
    private final String providerId;
    public ArgonPasswordHashProvider(final String providerId) {
        this.providerId = providerId;
    }

    @Override
    public boolean policyCheck(final PasswordPolicy policy, final PasswordCredentialModel credential) {
        return providerId.equals(credential.getPasswordCredentialData().getAlgorithm());
    }

    @Override
    public PasswordCredentialModel encodedCredential(final String rawPassword, final int iterations) {
        throw new NotImplementedException("Argon credential encoding not implemented! Implementation only required if we want to store the password in argon2i");
    }

    @Override
    public String encode(final String rawPassword, final int iterations) {
        throw new NotImplementedException("Argon encoding not implemented! Implementation only required if we want to store the password in argon2i");
    }
    @Override
    public void close() {
    }

    @Override
    public boolean verify(final String rawPassword, final PasswordCredentialModel credential) {
        final String expectedHash = credential.getPasswordSecretData().getValue();
        return verifyArgonPassword(rawPassword, expectedHash);
    }

    boolean verifyArgonPassword(final String rawPassword, final String expectedHash){
        Argon2PasswordEncoder argon2PasswordEncoder = Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8();
        return argon2PasswordEncoder.matches(rawPassword, expectedHash);
    }

}