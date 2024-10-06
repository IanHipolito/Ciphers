import java.util.Scanner;

public class HillCipher {
    private static final int MATRIX_SIZE = 2;

    // Function to encrypt the plaintext
    public static String encrypt(String plaintext, int[][] keyMatrix) {
        plaintext = plaintext.toUpperCase().replaceAll("[^A-Z]", "");
        StringBuilder ciphertext = new StringBuilder();

        for (int i = 0; i < plaintext.length(); i += MATRIX_SIZE) {
            int[] block = new int[MATRIX_SIZE];

            for (int j = 0; j < MATRIX_SIZE; j++) {
                if (i + j < plaintext.length()) {
                    block[j] = plaintext.charAt(i + j) - 'A';
                }
            }

            int[] encryptedBlock = new int[MATRIX_SIZE];
            
            for (int j = 0; j < MATRIX_SIZE; j++) {
                encryptedBlock[j] = (keyMatrix[j][0] * block[0] + keyMatrix[j][1] * block[1]) % 26;
                ciphertext.append((char) (encryptedBlock[j] + 'A'));
            }
        }

        return ciphertext.toString();
    }
}
