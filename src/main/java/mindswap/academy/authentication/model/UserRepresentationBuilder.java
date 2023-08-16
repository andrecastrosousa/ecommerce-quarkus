package mindswap.academy.authentication.model;

import org.keycloak.representations.idm.UserRepresentation;

import java.util.List;

public class UserRepresentationBuilder {
    private final UserRepresentation userRepresentation;

    public UserRepresentationBuilder() {
        userRepresentation = new UserRepresentation();
    }

    public UserRepresentationBuilder withUsername(String username) {
        userRepresentation.setUsername(username);
        return this;
    }

    public UserRepresentationBuilder withFirstName(String firstName) {
        userRepresentation.setFirstName(firstName);
        return this;
    }

    public UserRepresentationBuilder withLastName(String lastName) {
        userRepresentation.setLastName(lastName);
        return this;
    }

    public UserRepresentationBuilder withRoles(List<String> roles) {
        userRepresentation.setRealmRoles(roles);
        return this;
    }

    public UserRepresentationBuilder withEmail(String email) {
        userRepresentation.setEmail(email);
        return this;
    }

    public UserRepresentation build() {
        return userRepresentation;
    }

    public static UserRepresentationBuilder builder() {
        return new UserRepresentationBuilder();
    }
}
