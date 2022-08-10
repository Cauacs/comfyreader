package xyz.comfyreader.models;

public record AuthenticationRequest(
        String email,
        String password
) {
}
