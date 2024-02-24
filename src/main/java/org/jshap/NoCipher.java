package org.jshap;

public class NoCipher implements CipherMode {
    @Override
    public String encrypt(String str) {
        return str;
    }

    @Override
    public String decrypt(String str) {
        return str;
    }
}
