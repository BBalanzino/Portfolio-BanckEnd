package com.backend.portfolio.auth.models;

import java.io.Serializable;

public class JwtResponseModel implements Serializable {
    /**
    *
    */
    private static final long serialVersionUID = 1L;
    private final String token;
    private final Long expiresAt;

    public JwtResponseModel(String token, Long expiresAt) {
        this.token = token;
        this.expiresAt = expiresAt;
    }

    public String getToken() {
        return token;
    }

    public Long getExpiresAt()
    {
        return expiresAt;
    }
}