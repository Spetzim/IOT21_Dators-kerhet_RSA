package com.company;

import java.io.File;
import java.util.Scanner;

public class Menu {
    public static Scanner sc = new Scanner(System.in);

    public Menu() {
        while (true) {
            System.out.println();
            System.out.println("Choose an option");
            System.out.println("1. Generate and Save keys");
            System.out.println("2. Read your generated keys");
            System.out.println("3. Encrypt Textmessage in a textfile");
            System.out.println("4. Decrypt Textmessage in a textfile");
            System.out.println("5. Exit");
            String selection = sc.nextLine();
            switch (selection) {
                case "1" -> {
                    RSA.generateKeys(RSA.bitLength);
                }
                case "2" -> {
                    System.out.println("Write the name of the file you want to read");
                    File input = new File(sc.nextLine());
                    RSA.readKey(new File(input + "_pub.key"));
                    System.out.println("Read key from : " + input + "_pub.key");
                    RSA.readKey(new File(input + "_priv.key"));
                    System.out.println("Read key from : " + input+ "_priv.key");
                }
                case "3" -> {
                    String encryptedString = Encryption.encrypt();
                    Encryption.saveEncryptedTextFile(new File(sc.nextLine()), encryptedString);
                }
                case "4" -> {
                    String decryptedMessage = Encryption.decrypt();
                    System.out.println("The encrypted message was: " + decryptedMessage);
                }
                case "5" -> {
                    System.out.println("Exiting program....");
                    System.exit(0);
                }
                default -> System.out.println("You can only choose between 1-4, try again.");
            }
        }
    }
}

