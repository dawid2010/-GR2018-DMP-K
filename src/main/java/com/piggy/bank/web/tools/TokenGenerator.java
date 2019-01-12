package com.piggy.bank.web.tools;

import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Class that generates tokens
 */
@Component
public class TokenGenerator {
    /**
     * @return unique token
     */
    public String generateToken() {
        return UUID.randomUUID().toString();
    }
}
