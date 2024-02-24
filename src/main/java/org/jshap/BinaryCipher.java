package org.jshap;

public class BinaryCipher implements CipherMode {
    static public final int AMOUNT_OF_BITS = (int) (Math.log(Character.MAX_VALUE + 1) / Math.log(2));

    @Override
    public String encrypt(String str) {
        StringBuilder encryptedStr = new StringBuilder();

        for (char ch : str.toCharArray()) {
            StringBuilder encryptedCh = new StringBuilder();

            while (ch != 0) {
                encryptedCh.insert(0, ch % 2);
                ch /= 2;
            }

            //System.out.println(encryptedCh.toString());

            if (encryptedCh.length() < AMOUNT_OF_BITS) {
                char[] missingZeros = new char[(int) (AMOUNT_OF_BITS - encryptedCh.length())];
                encryptedCh.insert(0, new String(missingZeros).replace("\0","0"));
            }

            encryptedStr.append(encryptedCh);
        }

        return encryptedStr.toString();
    }

    @Override
    public String decrypt(String str) {
        if (str.length() % AMOUNT_OF_BITS != 0) {
            throw new IllegalArgumentException("Inappropriate length for binary format");
        }

        char[] decryptedStr = new char[str.length() / AMOUNT_OF_BITS];

        char newCode = 0;
        for (int i = 0; i <= str.length(); ++i) {
            if (i % AMOUNT_OF_BITS == 0 && i != 0) {
                decryptedStr[i / AMOUNT_OF_BITS - 1] = newCode;
                newCode = 0;
            } else {
                newCode += (char) ((str.charAt(i) - '0') * (int) Math.pow(2, AMOUNT_OF_BITS - 1 - i % AMOUNT_OF_BITS));
            }
        }

        return new String(decryptedStr);
    }
}
