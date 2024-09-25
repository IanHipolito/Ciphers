import java.util.Scanner;

public class CaesarCipher {
    // Method to encrypt the message using Caesar Cipher
    public static String Encrypt(String text, int shift) {

        // StringBuilder is used to store the resulting encrypted text.
        StringBuilder result = new StringBuilder();

        // Loop through each character of the input text
        for (int i = 0; i < text.length(); i++) {
            // Get the character at the current index
            char character = text.charAt(i);

            // Check if the character is an uppercase letter
            if (Character.isUpperCase(character)) {
                // Shift the character and wrap around if necessary, then add it to the result
                char ch = (char)(((int) character + shift - 65) % 26 + 65);
                result.append(ch);
                // Check if the character is a lowercase letter
            } else if (Character.isLowerCase(character)) {
                // Shift the character and wrap around if necessary, then add it to the result
                char ch = (char)(((int) character + shift - 97) % 26 + 97);
                result.append(ch);
            } else {
                // If the character is not alphabetic, just add it to the result as is
                result.append(character);
            }
        }
        // Return the final encrypted text
        return result.toString();
    }

    // Method to decrypt the message (reverse the encryption using the same shift)
    public static String Decrypt(String text, int shift) {
        // To decrypt, we shift back by (26 - shift) positions
        return Encrypt(text, 26 - shift);
    }

    // Method to validate that the input contains only alphabetic characters
    public static boolean isValidInputv(String text) {
        // Regex checks if the input consists only of alphabetic characters (a-z, A-Z)
        return text.matches("[a-zA-Z]+");
    }

    public static void main(String[] args) {
        // Create a Scanner object to take input from the user.
        Scanner sc = new Scanner(System.in);
        // Variable to store the input text.
        String text;
        // Variable to store the shift value.
        int shift;

        // Loop until the user provides valid input (only alphabetic characters)
        while(true) {
            System.out.println("Enter the text to ENCRYPT: ");
             // Read the text from the user.
            text = sc.nextLine();

            // Validate the input using the isValidInput method
            if (isValidInputv(text)) {
                // If the input is valid, exit the loop.
                break;
            } else {
                System.out.println("***ERROR: INVALID INPUT - PLEASE ONLY INPUT ALPHABATIC CHARACTERS***");
            }
        }

        // Get the shift value from the user
        System.out.println("Enter the SHIFT value: ");
        // Read the shift value from the user.
        shift = sc.nextInt();
        // Encrypt the input text using the specified shift value
        String encrypted = Encrypt(text, shift);
        // Output the encrypted text.
        System.out.println("Encrypted text: " + encrypted);
        // Decrypt the encrypted text to verify correctness
        String decrypted = Decrypt(encrypted, shift);
        // Output the decrypted text.
        System.out.println("Decrypted text: " + decrypted);
        // Close the Scanner to free up resources.
        sc.close();
    }
}