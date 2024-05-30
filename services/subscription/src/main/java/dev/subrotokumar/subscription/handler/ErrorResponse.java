package dev.subrotokumar.subscription.handler;

import java.util.Map;

public record ErrorResponse(
    Map<String, String> errors
) {

}