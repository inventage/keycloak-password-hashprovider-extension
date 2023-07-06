package com.inventage.keycloak.passwordhashprovider.argon;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

class ArgonPasswordHashProviderTest {

    private static ArgonPasswordHashProvider argonPasswordHashProvider;

    @BeforeAll
    static void init_argon_provider() {
        ArgonPasswordHashProviderFactory argonFactory = new ArgonPasswordHashProviderFactory();
        argonPasswordHashProvider = (ArgonPasswordHashProvider) argonFactory.create(null);
    }

    @Test
    final void valid_argon2i_hashes() {
        //given
        final List<String> argonHashes = List.of("$argon2i$v=19$m=65536,t=16,p=1$bnI2SEl3UXNicmovRTZYdg$MeU+vEnpIQb1q1QiWNiIq70K8hoWWb3gbp1CfqH6jAU",
                "$argon2i$v=19$m=16,t=2,p=1$SG5lU1J6bVQwQVdqWnJVVg$D4lhpKP+NECizhGS+9uZiA",
                "$argon2i$v=19$m=65536,t=2,p=1$TG16SkpPTkVwZm9XM2dKQg$+aI4J6tS+V6fwILXuDieQA",
                "$argon2i$v=19$m=65536,t=2,p=8$Z1hRNTA1UDV2MW8xOFF2eQ$p2+OOn15Aez9qMlPqrmAYw");
        final List<String> passwords = List.of(
                "sozialinfo",
                "password123",
                "qwertyuiop",
                "letmein");
        for (int i = 0; i < argonHashes.size(); i++){
            final String argonHash = argonHashes.get(i);
            final String password = passwords.get(i);
            //when
            final boolean passwordMatches = argonPasswordHashProvider.verifyArgonPassword(password, argonHash);
            //then
            Assertions.assertTrue(passwordMatches, "Password " + password + " does not match " + argonHash);
        }
    }

    @Test
    final void invalid_argon2i_hashes() {
        //given
        final List<String> argonHashes = List.of("$argon2i$v=19$m=65536,t=16,p=1$bnI2SEl3UXNicmovRTZYdg$MeU+vEnpIQb1q1QiWNiIq70K8hoWWb3gbp1CfqH6jAU",
                "$argon2i$v=19$m=16,t=2,p=1$SG5lU1J6bVQwQVdqWnJVVg$D4lhpKP+NECizhGS+9uZiA",
                "$argon2i$v=19$m=65536,t=2,p=1$TG16SkpPTkVwZm9XM2dKQg$+aI4J6tS+V6fwILXuDieQA",
                "$argon2i$v=19$m=65536,t=2,p=8$Z1hRNTA1UDV2MW8xOFF2eQ$p2+OOn15Aez9qMlPqrmAYw");
        final List<String> passwords = List.of(
                "invalid-sozialinfo",
                "invalid-password123",
                "invalid-qwertyuiop",
                "invalid-letmein");
        for (int i = 0; i < argonHashes.size(); i++){
            final String argonHash = argonHashes.get(i);
            final String password = passwords.get(i);
            //when
            final boolean passwordMatches = argonPasswordHashProvider.verifyArgonPassword(password, argonHash);
            //then
            Assertions.assertFalse(passwordMatches, "Password " + password + " should not match " + argonHash);
        }
    }


}
