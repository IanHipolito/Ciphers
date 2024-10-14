import java.util.Scanner;

public class VigenereCipher {

    // Function to generate the key in cyclic manner until its length equals the length of original text
    public static String generateKey(String str, String key) {
        int x = str.length(); // Length of the original text
        StringBuilder newKey = new StringBuilder(key); // Build the new key using StringBuilder for mutability

        // Repeat the key until its length matches the original text
        for (int i = 0; ; i++) {
            if (newKey.length() == str.length()) {
                break; // Exit when key matches the length of the original text
            }
            // Append characters of key cyclically to match the length
            newKey.append(key.charAt(i % key.length()));
        }
        return newKey.toString(); // Return the generated key
    }

    // This function returns the encrypted text generated with the help of the key
    static String cipherText(String str, String key) {
        StringBuilder cipher_text = new StringBuilder(); // Store the cipher text

        // Loop over each character of the input text
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i); // Get the current character
            char k = key.charAt(i); // Get the corresponding key character

            // Encrypt lowercase letters
            if (Character.isLowerCase(c)) {
                int x = (c - 'a' + (k - 'a')) % 26; // Encrypt based on alphabet position (lowercase)
                cipher_text.append((char) (x + 'a')); // Append the encrypted character
            }
            // Encrypt uppercase letters
            else if (Character.isUpperCase(c)) {
                int x = (c - 'A' + (k - 'A')) % 26; // Encrypt based on alphabet position (uppercase)
                cipher_text.append((char) (x + 'A')); // Append the encrypted character
            }
        }
        return cipher_text.toString(); // Return the final encrypted text
    }

    // This function decrypts the encrypted text and returns the original text
    static String originalText(String cipher_text, String key) {
        StringBuilder orig_text = new StringBuilder(); // Store the decrypted original text

        // Loop over each character of the encrypted text
        for (int i = 0; i < cipher_text.length(); i++) {
            char c = cipher_text.charAt(i); // Get the current encrypted character
            char k = key.charAt(i); // Get the corresponding key character

            // Decrypt lowercase letters
            if (Character.isLowerCase(c)) {
                int x = (c - 'a' - (k - 'a') + 26) % 26; // Decrypt based on alphabet position (lowercase)
                orig_text.append((char) (x + 'a')); // Append the decrypted character
            }
            // Decrypt uppercase letters
            else if (Character.isUpperCase(c)) {
                int x = (c - 'A' - (k - 'A') + 26) % 26; // Decrypt based on alphabet position (uppercase)
                orig_text.append((char) (x + 'A')); // Append the decrypted character
            }
        }
        return orig_text.toString(); // Return the final decrypted text
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // Create a scanner object for input
        
        // Take user input for text and key
        System.out.print("Enter the text to encrypt: "); // Prompt for input
        String Str = sc.nextLine(); // Get the text input from the user
        System.out.print("Enter the key: "); // Prompt for key input
        String Keyword = sc.nextLine(); // Get the key input from the user
        
        // Generate the key
        String key = generateKey(Str, Keyword); // Generate the key for encryption
        // Encrypt the text
        String cipher_text = cipherText(Str, key); // Encrypt the input text using the key
        // Display results
        System.out.println("Ciphertext: " + cipher_text); // Display the encrypted text
        // Decrypt the text
        System.out.println("Original/Decrypted Text: " + originalText(cipher_text, key)); // Display the decrypted text

        sc.close(); // Close the scanner object
    }
}
