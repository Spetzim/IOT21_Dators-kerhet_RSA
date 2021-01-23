package com.company;

import java.io.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Scanner;

public class RSA {
    public static KeyPair publicKey;
    public static KeyPair privateKey;
    public static final int bitLength = 2048;
    public static Scanner sc = new Scanner(System.in);

    public static void saveKey(File fileName, KeyPair key) {
        if(fileName.exists()){
            System.out.println("This file already exists, please choose another name");
            return;
        }
        try {
            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(key);
            out.close();
            System.out.println("Saved Key as " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static KeyPair readKey(File fileName) {
        KeyPair key = null;
        try {
            if(fileName.exists()) {
                FileInputStream fileIn = new FileInputStream(fileName);
                ObjectInputStream in = new ObjectInputStream(fileIn);
                key = (KeyPair) in.readObject();
                in.close();
            }else{
                System.out.println("File does not exist, try again.");
            }
        } catch (ClassNotFoundException | IOException | NullPointerException e) {
            e.printStackTrace();
        }
        return key;
    }

    public static void generateKeys(int bitLength) {
        System.out.println("Keys generated, choose a name for your file.");
        File fileName = new File(sc.nextLine());

        SecureRandom rand = new SecureRandom();

        BigInteger p = new BigInteger(bitLength / 2, 100, rand);
        BigInteger q = new BigInteger(bitLength / 2, 100, rand);
        BigInteger n = p.multiply(q);
        BigInteger phiN = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        BigInteger e = new BigInteger("3");
        while (phiN.gcd(e).intValue() > 1) {
            e = e.add(new BigInteger("2"));
        }

        BigInteger d = e.modInverse(phiN);
        publicKey = new KeyPair(e, n);
        privateKey = new KeyPair(d, n);
        RSA.saveKey(new File(fileName + "_pub.key"), RSA.publicKey);
        RSA.saveKey(new File(fileName + "_priv.key"), RSA.privateKey);
    }
}
