package org.jshap;

public class Main {
    public static void main(String[] args) {
        Message msg = new Message(new CaesarCipher(1));

        msg.print(new NoCipher());
        msg.print(new CaesarCipher(1));

        msg.setCipherMode(new BinaryCipher());
        msg.print(new NoCipher());
        msg.print(new BinaryCipher());
    }
}