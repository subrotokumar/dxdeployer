package dev.subrotokumar.gateway.exception;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
public class ExpiredTokenException extends RuntimeException {
    final private String message;
}
