package org.jshap;

public interface CipherMode {
    String encrypt(String str);

    String decrypt(String str);
}
