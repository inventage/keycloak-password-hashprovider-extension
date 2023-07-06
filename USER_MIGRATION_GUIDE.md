

User Migration Guide
=====================

This guide shows you how to migrate users with Argon2 or Bcrypt passwords to Keycloak by accessing the Keycloak admin UI and database. This guide assumes you are using `postgres` as your database, but it should be valid for other databases as well.

Optional: To get a locally running Keycloak instance with a Postgres database, you can simply follow this [tutorial] (https://keycloak.ch/keycloak-tutorials/tutorial-custom-keycloak/) on which this repository is based.

1. Log in to the keycloak admin ui. 
2. Create a new user in your desired realm.
3. set a password credential for this user. These credentials will later be overwritten with an argon2 or bcrypt password hash.
4. Connect to the postgres instance. 
5. On the `public` schema there are among others the tables `user_entity` and `credential`. The `credential` table contains the provisionally `hashed` passwords from step 3, that is a pbkdf2 hash (the standard keycloak password hash). These should now be replaced by argon or bcrypt. A total of two adjustments have to be made:
- In `credential_data` the `value` value must be replaced with the corresponding hashed password. The entries `salt` and `additionalParamters` listed with can be deleted for argon and bcrypt, because they are already included in the hash of the password.
- For `secret_data` the `algorithm` must be replaced. The value `argon` for any argon hash and `bcrypt` for a BCrypt hash.
- We provide some sample argon and bcrypt password hashes that you can use for testing:
```
password: password123, hash: argon2i$v=19$m=16,t=2,p=1$SG5lU1J6bVQwQVdqWnJVVg$D4lhpKP+NECizhGS+9uZiA
password: password123, hash: $2a$12$JoJiil4yMHIeIDYIBIKYE.PGlgQfEdi3TNTtGm9/y2rrUP4bdoxeW
```
6. **IMPORTANT**: Keycloak must be restarted after the changes in postgres. Keycloak probably caches the passwords and therefore needs to be restarted to take the new passwords into account when logging in.