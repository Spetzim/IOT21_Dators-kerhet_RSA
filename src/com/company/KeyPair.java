package com.company;

import java.math.BigInteger;

public class KeyPair implements java.io.Serializable {
    private static final long serialVersionUID = 4L;
    private BigInteger key;
    private BigInteger n;

    public KeyPair(BigInteger key, BigInteger n) {
        this.setKey(key);
        this.setN(n);
    }

    public BigInteger getN() {
        return this.n;
    }

    public BigInteger getKey() {
        return this.key;
    }

    public void setN(BigInteger n) {
        this.n =n;
    }

    public void setKey(BigInteger key) {
        this.key = key;
    }

}


// key size? konstruktor?
// medlemsvariabler med nycklar
// Signering