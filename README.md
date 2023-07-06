Keycloak-hashprovider-extension
===============

![GitHub release (latest SemVer)](https://img.shields.io/github/v/release/inventage/keycloak-password-hashprovider-extension?sort=semver)
![Keycloak Dependency Version](https://img.shields.io/badge/Keycloak-21.1.2-blue)
![GitHub Release Date](https://img.shields.io/github/release-date-pre/inventage/keycloak-password-hashprovider-extension)
![Github Last Commit](https://img.shields.io/github/last-commit/inventage/keycloak-password-hashprovider-extension)

![CI build](https://github.com/inventage/keycloak-password-hashprovider-extension/actions/workflows/build-pipeline.yml/badge.svg)
![open issues](https://img.shields.io/github/issues/inventage/keycloak-password-hashprovider-extension)

## What is it good for?

The `Keycloak Hashprovider` extension enables Keycloak to support other password hashing algorithm than the
built-in [PBKDF2](https://www.keycloak.org/docs/21.1.2/server_admin/#password-policy-types).

Currently, we support

- (only available on Keycloak version 21+): validation of [Argon2](https://en.wikipedia.org/wiki/Argon2)
  and [BCrypt](https://en.wikipedia.org/wiki/Bcrypt) password hashes. This can be used for migrating existing user
  credentials to keycloak. Please consult the [User migration guide](USER_MIGRATION_GUIDE.md) for more information.

## Installation

Our `Keycloak Password Hashprovider` extension can be downloaded as a Java Archive (jar) and can simply be placed in the
providers directory of your Keycloak.

## Development

This project is based on our [Custom Keycloak](https://github.com/inventage/keycloak-custom) repository. It is
structured as a multi-module Maven build and contains the following top-level modules:

- config  : provides the build stage configuration and the setup of Keycloak
- container : creates the custom docker image
- docker-compose : provides a sample for launching the custom docker image
- extensions : provides the keycloak hashprovider extension
- server : provides a Keycloak installation for local development & testing
- themes : provides samples for custom themes

Please see the [tutorial](https://keycloak.ch/keycloak-tutorials/tutorial-custom-keycloak/) for the setup of this
project.

## Sponsors

https://sozialinfo.ch

## Support

For more support for this extension or your Keycloak project in general have a look
at [keycloak.ch](https://keycloak.ch)
