package xyz.comfyreader.models;

public record RegistrationRequest(
        String firstName,
        String lastName,
        String email,
        String password
) {
}
