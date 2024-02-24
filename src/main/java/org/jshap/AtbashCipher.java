package org.jshap;

public class AtbashCipher implements CipherMode {
    @Override
    public String encrypt(String str) {
        char[] encryptedStr = new char[str.length()];

        for (int i = 0; i < encryptedStr.length; ++i) {
            encryptedStr[i] = (char) (Character.MAX_VALUE - str.charAt(i));
        }

        return new String(encryptedStr);
    }

    @Override
    public String decrypt(String str) {
        return encrypt(str);
    }
}
