package org.jshap;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Message {
    private ArrayList<String> text;
    private CipherMode cipherMode;

    public Message(CipherMode cipherMode) {
        text = new ArrayList<>();
        this.cipherMode = cipherMode;

        System.out.println("Enter text message. Stop symbol is '~'");

        try (Scanner scanner = new Scanner(System.in)) {
            String line = scanner.nextLine();
            while (!"~".equals(line)) {
                text.add(cipherMode.encrypt(line));
                line = scanner.nextLine();
            }
        }
    }

    public Message(String pathStr, CipherMode cipherMode) {
        text = new ArrayList<>();
        this.cipherMode = cipherMode;

        Path path = Paths.get(pathStr);
        try {
            if (!path.isAbsolute()) {
                throw new IOException("Path is not absolute");
            }

            if (!Files.exists(path)) {
                throw new FileNotFoundException("File cannot be found");
            }

            if (Files.isDirectory(path)) {
                throw new IOException("Path leads to directory instead of file");
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(pathStr))) {
            String line;
            while ((line = reader.readLine()) != null) {
                text.add(cipherMode.encrypt(line));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setCipherMode(CipherMode cipherMode) {
        text.replaceAll(str -> cipherMode.encrypt(this.cipherMode.decrypt(str)));

        this.cipherMode = cipherMode;
    }

    public void print(CipherMode cipherMode) {
        for (String str : text) {
            System.out.println(cipherMode.decrypt(str));
        }
    }
}
