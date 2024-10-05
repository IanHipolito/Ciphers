import java.util.Scanner;

public class VigenereCipher {

    // Function to encrypt the text using Vigenere Cipher
    public static String generateKey(String str, String key) {
        int x = str.length();

        for (int i = 0; ; i++) {
            if (x == i)
                i = 0;
            if (key.length() == str.length())
                break;
            key += key.charAt(i);
        }
        return key;
    }

    // This function returns the encrypted text generated with the help of the key
    static String cipherText(String str, String key) {
        StringBuilder cipher_text = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            char k = key.charAt(i);

            // Encrypt lowercase letters
            if (Character.isLowerCase(c)) {
                int x = (c - 'a' + (k - 'a')) % 26;
                cipher_text.append((char) (x + 'a'));
            }
            // Encrypt uppercase letters
            else if (Character.isUpperCase(c)) {
                int x = (c - 'A' + (k - 'A')) % 26;
                cipher_text.append((char) (x + 'A'));
            }
        }
        return cipher_text.toString();
    }

    // This function decrypts the encrypted text and returns the original text
    static String originalText(String cipher_text, String key) {
        StringBuilder orig_text = new StringBuilder();

        for (int i = 0; i < cipher_text.length(); i++) {
            char c = cipher_text.charAt(i);
            char k = key.charAt(i);

            // Decrypt lowercase letters
            if (Character.isLowerCase(c)) {
                int x = (c - 'a' - (k - 'a') + 26) % 26;
                orig_text.append((char) (x + 'a'));
            }
            // Decrypt uppercase letters
            else if (Character.isUpperCase(c)) {
                int x = (c - 'A' - (k - 'A') + 26) % 26;
                orig_text.append((char) (x + 'A'));
            }
        }
        return orig_text.toString();
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // Take user input for text and key
        System.out.print("Enter the text to encrypt: ");
        String Str = sc.nextLine();
        System.out.print("Enter the key: ");
        String Keyword = sc.nextLine();
        // Generate the key
        String key = generateKey(Str, Keyword);
        // Encrypt the text
        String cipher_text = cipherText(Str, key);
        // Display results
        System.out.println("Ciphertext: " + cipher_text);
        // Decrypt the text
        System.out.println("Original/Decrypted Text: " + originalText(cipher_text, key));

        sc.close();
    }
}
