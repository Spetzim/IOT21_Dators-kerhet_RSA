package com.company;

import java.io.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Encryption {
    public static Scanner sc = new Scanner(System.in);
    public static void saveEncryptedTextFile(File fileName, String encryptedString) {
        if(fileName.exists()){
            System.out.println("This filename already exists, please choose another name");
            return;
        }
        try {
            FileOutputStream fileOut = new FileOutputStream(fileName+ ".enc");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(encryptedString);
            out.close();
            System.out.println("Saved encrypted text to file: " + fileName + ".enc");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readEncryptedMessage(File fileName) {
        String encryptedMessage = null;
        try{
            if(fileName.exists()) {
                FileInputStream fileIn = new FileInputStream(fileName);
                ObjectInputStream in = new ObjectInputStream(fileIn);
                encryptedMessage = (String) in.readObject();
            } else {
                System.out.println("File does not exist, try again.");
                return encryptedMessage;
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return encryptedMessage;
    }

    public static String encrypt() {
        System.out.println("What do you want to encrypt?");
        System.out.print("> ");
        String message = sc.nextLine();
        System.out.println("Enter name of key");
        System.out.print("> ");
        File fileNameOfRecipient = new File(sc.nextLine());
        KeyPair key = RSA.readKey(new File(fileNameOfRecipient + "_pub.key"));
        if (key == null) {
            new Menu();
        }
        System.out.println("Enter file name for encrypted message");
        System.out.print("> ");
        return (new BigInteger(message.getBytes()).modPow(key.getKey(), key.getN()).toString());
    }

    public static String decrypt() {
        System.out.println("Enter the file name of the encrypted message");
        System.out.print("> ");
        String encryptedMessage = Encryption.readEncryptedMessage(new File(sc.nextLine() + ".enc"));
        if(encryptedMessage == null) {
            new Menu();
        }
        System.out.println("Enter the name of the private key to use");
        System.out.print("> ");
        KeyPair key = RSA.readKey(new File(sc.nextLine() + "_priv.key"));
        String msg = new String(encryptedMessage.getBytes(StandardCharsets.UTF_8));
        return new String((new BigInteger(msg)).modPow(key.getKey(), key.getN()).toByteArray());
    }

}
