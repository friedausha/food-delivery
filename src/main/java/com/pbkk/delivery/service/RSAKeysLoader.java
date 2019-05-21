package com.pbkk.delivery.service;


import java.io.StringReader;
import java.security.KeyPair;
import java.security.Security;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;

public class RSAKeysLoader {
    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    public static RSAPrivateKey createPrivateKeyPKCS1Format(String privateKey) {
        try {
            PEMParser pemParser = new PEMParser(new StringReader(privateKey));
            JcaPEMKeyConverter converter = new JcaPEMKeyConverter().setProvider("BC");
            Object object = pemParser.readObject();
            KeyPair kp = converter.getKeyPair((PEMKeyPair) object);
            return (RSAPrivateKey) kp.getPrivate();
        } catch (Exception e) {
            return null;
        }
    }

    public static RSAPublicKey createPublicKeyPKCS1Format(String privateKey) {
        try {
            PEMParser pemParser = new PEMParser(new StringReader(privateKey));
            JcaPEMKeyConverter converter = new JcaPEMKeyConverter().setProvider("BC");

            Object object = pemParser.readObject();
            KeyPair kp = converter.getKeyPair((PEMKeyPair) object);

            return (RSAPublicKey) kp.getPublic();
        } catch (Exception e) {
            return null;
        }
    }
}