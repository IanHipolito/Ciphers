import java.util.Scanner;

class CaesarCipher {
    
    // Encrypts text using a shift of s
    public static StringBuffer encrypt(String text, int s) {
        StringBuffer result = new StringBuffer(); // StringBuffer to hold the encrypted result

        // Loop over each character in the input text
        for (int i = 0; i < text.length(); i++) {
            char currentChar = text.charAt(i); // Get the current character from the input text

            // If the current character is an uppercase letter
            if (Character.isUpperCase(currentChar)) {
                // Shift the character based on 'A' and append to the result
                char ch = (char)(((int)text.charAt(i) + s - 65) % 26 + 65);
                result.append(ch);
            } else if (Character.isLowerCase(currentChar)){ // If the current character is a lowercase letter
                // Shift the character based on 'a' and append to the result
                char ch = (char)(((int)text.charAt(i) + s - 97) % 26 + 97);
                result.append(ch);
            } else {
                // If it's not a letter (space or punctuation), append it without modification
                result.append(currentChar);
            }
        }

        return result; // Return the final encrypted string
    }

    // Decrypts text using a shift of s
    public static StringBuffer decrypt(String text, int s) {
        return encrypt(text, 26 - s);  // Reverse the encryption shift to decrypt
    }

    // Main method to choose between encryption and decryption
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get the user's choice
        System.out.println("Please Input (1) If You Want To Encrypt or (2) If You Want To Decrypt: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Get the text input
        System.out.println("Enter The Text:");
        String text = scanner.nextLine();

        // Get the shift value
        System.out.println("Enter The Shift Value:");
        int s = scanner.nextInt();

        // Process based on user choice
        if (choice == 1) {
            System.out.println("Encrypted Text: " + encrypt(text, s));
        } else if (choice == 2) {
            System.out.println("Decrypted Text: " + decrypt(text, s));
        } else {
            System.out.println("Invalid Choice - Please Enter Either (1) or (2)");
        }

        scanner.close();
    }
}
