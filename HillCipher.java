import java.util.Scanner;

public class HillCipher {
    // Define the size of the key matrix (2x2)
    private static final int MATRIX_SIZE = 2;

    // Function to encrypt the plaintext
    public static String encrypt(String plaintext, int[][] keyMatrix) {
        // Convert plaintext to uppercase and remove non-letter characters
        plaintext = plaintext.toUpperCase().replaceAll("[^A-Z]", "");
        StringBuilder ciphertext = new StringBuilder(); // To store the resulting ciphertext

        // Process the plaintext in blocks of size 2 (MATRIX_SIZE)
        for (int i = 0; i < plaintext.length(); i += MATRIX_SIZE) {
            int[] block = new int[MATRIX_SIZE]; // Array to store a block of the plaintext

            // Fill the block with numerical values (A=0, B=1, ..., Z=25)
            for (int j = 0; j < MATRIX_SIZE; j++) {
                if (i + j < plaintext.length()) {
                    block[j] = plaintext.charAt(i + j) - 'A'; // Convert char to its position in the alphabet
                }
            }

            int[] encryptedBlock = new int[MATRIX_SIZE]; // Array to store the result of encryption for the block
            
            // Multiply the key matrix with the block
            for (int j = 0; j < MATRIX_SIZE; j++) {
                // Matrix multiplication followed by modulo 26
                encryptedBlock[j] = (keyMatrix[j][0] * block[0] + keyMatrix[j][1] * block[1]) % 26;
                // Convert the result back to a letter and append to the ciphertext
                ciphertext.append((char) (encryptedBlock[j] + 'A'));
            }
        }

        return ciphertext.toString(); // Return the final ciphertext
    }

    // Function to decrypt the ciphertext
    public static String decrypt(String ciphertext, int[][] keyMatrix) {
        // Get the inverse of the key matrix for decryption
        int[][] inverseKeyMatrix = getInverseKeyMatrix(keyMatrix);
        StringBuilder decryptedText = new StringBuilder(); // To store the resulting decrypted text
        
        // Process the ciphertext in blocks of size 2 (MATRIX_SIZE)
        for (int i = 0; i < ciphertext.length(); i += MATRIX_SIZE) {
            int[] block = new int[MATRIX_SIZE]; // Array to store a block of the ciphertext
            for (int j = 0; j < MATRIX_SIZE; j++) {
                if (i + j < ciphertext.length()) {
                    block[j] = ciphertext.charAt(i + j) - 'A'; // Convert char to its position in the alphabet
                }
            }

            int[] decryptedBlock = new int[MATRIX_SIZE]; // Array to store the result of decryption for the block

            for (int j = 0; j < MATRIX_SIZE; j++) {
                // Matrix multiplication followed by modulo 26 to decrypt
                decryptedBlock[j] = (inverseKeyMatrix[j][0] * block[0] + inverseKeyMatrix[j][1] * block[1]) % 26; // Matrix multiplication
                // Handle negative values by adding 26
                if (decryptedBlock[j] < 0) { 
                    decryptedBlock[j] += 26;
                }
                // Convert the result back to a letter and append to the decrypted text
                decryptedText.append((char) (decryptedBlock[j] + 'A'));
            }
        }

        return decryptedText.toString(); // Return the final decrypted text
    }

    // Function to get the inverse key matrix
    private static int[][] getInverseKeyMatrix(int[][] matrix) {
        int determinant = getDeterminant(matrix); // Calculate the determinant of the key matrix
        int modularInverse = getModularInverse(determinant); // Find the modular inverse of the determinant

        // If the matrix is not invertible, throw an error
        if (modularInverse == -1) {
            throw new IllegalArgumentException("Key matrix is not invertible");
        }

        int[][] inverseMatrix = new int[MATRIX_SIZE][MATRIX_SIZE]; // Initialize the inverse matrix
         // Calculate the inverse matrix elements using the cofactor matrix and modular arithmetic
        inverseMatrix[0][0] = matrix[1][1] * modularInverse % 26;
        inverseMatrix[0][1] = (-matrix[0][1] + 26) * modularInverse % 26;
        inverseMatrix[1][0] = (-matrix[1][0] + 26) * modularInverse % 26;
        inverseMatrix[1][1] = matrix[0][0] * modularInverse % 26;

        return inverseMatrix; // Return the inverse key matrix
    }

    // Function to calculate the determinant of the key matrix
    private static int getDeterminant(int[][] matrix) {
        // Calculate determinant using the formula: det(A) = ad - bc
        return (matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0]) % 26;
    }

    // Function to get the modular inverse of the determinant
    private static int getModularInverse(int determinant) {
        // Try all possible values to find the modular inverse of the determinant
        for (int i = 1; i < 26; i++) {
            if ((determinant * i) % 26 == 1) {
                return i; // Return the modular inverse
            }
        }
        return -1; // Inverse doesn't exist
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input key matrix
        System.out.println("Enter The Key Matrix (2x2): ");
        int[][] keyMatrix = new int[MATRIX_SIZE][MATRIX_SIZE]; // Initialize the key matrix
        for (int i = 0; i < MATRIX_SIZE; i++) {
            for (int j = 0; j < MATRIX_SIZE; j++) {
                keyMatrix[i][j] = scanner.nextInt(); // Input the key matrix elements
            }
        }

        // Ask the user to choose encryption or decryption
        System.out.println("Please Input (1) If You Want To Encrypt or (2) If You Want To Decrypt: ");
        int choice = scanner.nextInt();

        // Handle user choice
        if (choice == 1) {
            // Input plaintext
            System.out.println("Enter the plaintext:");
            String plaintext = scanner.next();

            // Perform encryption
            String encryptedText = encrypt(plaintext, keyMatrix);
            System.out.println("Encrypted Text: " + encryptedText);
        } else if (choice == 2) {
            // Input ciphertext
            System.out.println("Enter the ciphertext:");
            String ciphertext = scanner.next();

            // Perform decryption
            String decryptedText = decrypt(ciphertext, keyMatrix);
            System.out.println("Decrypted Text: " + decryptedText);
        } else {
            System.out.println("Invalid option. Please choose either 1 or 2.");
        }

        scanner.close();
    }  
} 
