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

    // Function to decrypt the ciphertext
    public static String decrypt(String ciphertext, int[][] keyMatrix) {
        int[][] inverseKeyMatrix = getInverseKeyMatrix(keyMatrix);
        StringBuilder decryptedText = new StringBuilder();

        for (int i = 0; i < ciphertext.length(); i += MATRIX_SIZE) {
            int[] block = new int[MATRIX_SIZE];
            for (int j = 0; j < MATRIX_SIZE; j++) {
                if (i + j < ciphertext.length()) {
                    block[j] = ciphertext.charAt(i + j) - 'A';
                }
            }

            int[] decryptedBlock = new int[MATRIX_SIZE];
            for (int j = 0; j < MATRIX_SIZE; j++) {
                decryptedBlock[j] = (inverseKeyMatrix[j][0] * block[0] + inverseKeyMatrix[j][1] * block[1]) % 26; // Matrix multiplication
                if (decryptedBlock[j] < 0) { 
                    decryptedBlock[j] += 26;
                }
                decryptedText.append((char) (decryptedBlock[j] + 'A'));
            }
        }

        return decryptedText.toString();
    }

    // Function to get the inverse key matrix
    private static int[][] getInverseKeyMatrix(int[][] matrix) {
        int determinant = getDeterminant(matrix);
        int modularInverse = getModularInverse(determinant);

        if (modularInverse == -1) {
            throw new IllegalArgumentException("Key matrix is not invertible");
        }

        int[][] inverseMatrix = new int[MATRIX_SIZE][MATRIX_SIZE];
        inverseMatrix[0][0] = matrix[1][1] * modularInverse % 26;
        inverseMatrix[0][1] = (-matrix[0][1] + 26) * modularInverse % 26;
        inverseMatrix[1][0] = (-matrix[1][0] + 26) * modularInverse % 26;
        inverseMatrix[1][1] = matrix[0][0] * modularInverse % 26;

        return inverseMatrix;
    }

    // Function to calculate the determinant of the key matrix
    private static int getDeterminant(int[][] matrix) {
        return (matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0]) % 26;
    }

    // Function to get the modular inverse of the determinant
    private static int getModularInverse(int determinant) {
        for (int i = 1; i < 26; i++) {
            if ((determinant * i) % 26 == 1) {
                return i;
            }
        }
        return -1; // Inverse doesn't exist
    }
    
}
