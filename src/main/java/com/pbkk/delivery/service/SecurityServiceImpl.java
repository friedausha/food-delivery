package com.pbkk.delivery.service;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.security.interfaces.RSAPublicKey;


@Service
public class SecurityServiceImpl {
    private static final String ISSUER = "tcdelivery";

    private String readFile(String fileName) throws IOException {
        InputStream input = new ClassPathResource(fileName).getInputStream();
        byte[] bytes = IOUtils.toByteArray(input);
        return new String(bytes);
    }

    public String getRequestUsername() throws IOException {
        ServletRequestAttributes reqAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = reqAttributes.getRequest();
        String token = request.getHeader("token");

        RSAPublicKey publicKey = RSAKeysLoader.createPublicKeyPKCS1Format(
                readFile("key/jwtRS256.key"));//Get the key instance
        Algorithm algorithmRSCheck = Algorithm.RSA256(publicKey, null);

        JWTVerifier verifier = JWT.require(algorithmRSCheck)
                .withIssuer(ISSUER)
                .build(); //Reusable verifier instance
        DecodedJWT jwt = verifier.verify(token);
        Claim usernameClaim = jwt.getClaim("username");
        String username = usernameClaim.asString();
        return username;
    }
}