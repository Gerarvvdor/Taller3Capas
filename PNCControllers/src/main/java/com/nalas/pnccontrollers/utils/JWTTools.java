package com.nalas.pnccontrollers.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JWTTools {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.exptime}")
    private Integer exp;

}
