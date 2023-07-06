package com.inventage.keycloak.passwordhashprovider.bcrypt;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

class Typo3BCryptPasswordHashProviderTest {

    private static Typo3BCryptPasswordHashProvider bCryptPasswordHashProvider;

    @BeforeAll
    public static void init_bcrypt_provider() {
        Typo3BCryptPasswordHashProviderFactory typo3BCryptFactory = new Typo3BCryptPasswordHashProviderFactory();
        bCryptPasswordHashProvider = (Typo3BCryptPasswordHashProvider) typo3BCryptFactory.create(null);
    }

    @Test
    final void valid_typo3_bcrypt_hashes() {
        //given
        final List<String> argonHashes = List.of(
                "$2y$12$xtQ/70RpLO8pzGQjYjzsmuJ.eFBAFmizDotdHUBKd9.y755qj/OWu",
                "$2y$12$aZQGW57xmyPgaAX2N42lZOok3u4npX0sqGtcbNZdBYHM/cCzTWzuW");
        final List<String> passwords = List.of(
                "sozialinfo",
                "kevin12345");
        for (int i = 0; i < argonHashes.size(); i++) {
            final String typo3BCryptHash = argonHashes.get(i);
            final String password = passwords.get(i);
            //when
            final boolean passwordMatches = bCryptPasswordHashProvider.verifyTypo3BCryptPassword(password, typo3BCryptHash);
            //then
            Assertions.assertTrue(passwordMatches, "Password " + password + " does not match " + typo3BCryptHash);
        }
    }

    @Test
    final void invalid_typo3_bcrypt_hashes() {
        //given
        final List<String> argonHashes = List.of(
                "$2y$12$xtQ/70RpLO8pzGQjYjzsmuJ.eFBAFmizDotdHUBKd9.y755qj/OWu",
                "$2y$12$aZQGW57xmyPgaAX2N42lZOok3u4npX0sqGtcbNZdBYHM/cCzTWzuW");
        final List<String> passwords = List.of(
                "invalid-sozialinfo",
                "invalid-kevin12345");
        for (int i = 0; i < argonHashes.size(); i++) {
            final String typo3BCryptHash = argonHashes.get(i);
            final String password = passwords.get(i);
            //when
            final boolean passwordMatches = bCryptPasswordHashProvider.verifyTypo3BCryptPassword(password, typo3BCryptHash);
            //then
            Assertions.assertFalse(passwordMatches, "Password " + password + " should not match " + typo3BCryptHash);
        }
    }

    @Test
    void valid_bcrypt_hashes() {
        //given
        final List<String> argonHashes = List.of(
                "$2y$12$Q4XG3uE0p.PkqWxbIbWgOeHCdUDSvZjCeCJIioV8eJgsNIJZT73pm",
                "$2y$12$MFuQnMkf/A5J32FLuXNheOlbPgPnqIKUBT/RsS.rRw.Io2V4SYkmu",
                "$2a$12$JoJiil4yMHIeIDYIBIKYE.PGlgQfEdi3TNTtGm9/y2rrUP4bdoxeW");
        final List<String> passwords = List.of(
                "sozialinfo",
                "kevin12345",
                "password123");
        for (int i = 0; i < argonHashes.size(); i++) {
            final String bcryptHash = argonHashes.get(i);
            final String password = passwords.get(i);
            //when
            final boolean passwordMatches = bCryptPasswordHashProvider.verifyBCryptPassword(password, bcryptHash);
            //then
            Assertions.assertTrue(passwordMatches, "Password " + password + " does not match " + bcryptHash);
        }
    }

    @Test
    final void invalid_bcrypt_hashes() {
        //given
        final List<String> argonHashes = List.of(
                "$2y$12$xtQ/70RpLO8pzGQjYjzsmuJ.eFBAFmizDotdHUBKd9.y755qj/OWu",
                "$2y$12$aZQGW57xmyPgaAX2N42lZOok3u4npX0sqGtcbNZdBYHM/cCzTWzuW");
        final List<String> passwords = List.of(
                "invalid-sozialinfo",
                "invalid-kevin12345");
        for (int i = 0; i < argonHashes.size(); i++) {
            final String bcryptHash = argonHashes.get(i);
            final String password = passwords.get(i);
            //when
            final boolean passwordMatches = bCryptPasswordHashProvider.verifyTypo3BCryptPassword(password, bcryptHash);
            //then
            Assertions.assertFalse(passwordMatches, "Password " + password + " should not match " + bcryptHash);
        }
    }

}
