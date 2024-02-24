package org.jshap;

public class CaesarCipher implements CipherMode {
   private final int SHIFT_CONSTANT;

    public CaesarCipher(final int SHIFT_CONSTANT) {
        this.SHIFT_CONSTANT = SHIFT_CONSTANT;
    }

    @Override
    public String encrypt(String str) {
        char[] encryptedStr = str.toCharArray();

        for (int i = 0; i < encryptedStr.length; ++i) {
/*          int newCode = encryptedStr[i] + SHIFT_CONSTANT;

            if (newCode > Character.MAX_VALUE) {
                newCode -= Character.MAX_VALUE;
            }

            encryptedStr[i] = (char) newCode;
*/
            encryptedStr[i] = (char) ((encryptedStr[i] + SHIFT_CONSTANT) % Character.MAX_VALUE);
        }

        return new String(encryptedStr);
    }

    @Override
    public String decrypt(String str) {
        return new CaesarCipher(-SHIFT_CONSTANT).encrypt(str);
    }
}
