public class VigenereCipher {

    // Function to encrypt the text using Vigenere Cipher
    public static String encrypt(String text, String key) {
        StringBuilder result = new StringBuilder();
        text = text.toLowerCase();
        key = key.toLowerCase();

        for (int i = 0, j = 0; i < text.length(); i++) {
            char currentChar = text.charAt(i);

            // Encrypt only alphabetic characters
            if (currentChar >= 'a' && currentChar <= 'z') {
                // Shift the current character using the key
                char encryptedChar = (char) ((currentChar + key.charAt(j) - 2 * 'a') % 26 + 'a');
                result.append(encryptedChar);

                // Move to the next character in the key
                j = (j + 1) % key.length();
            } else {
                // If the character is not alphabetic, just append it
                result.append(currentChar);
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        String text = "explanation";
        String key = "leg";
        String encryptedText = encrypt(text, key);
        
        System.out.println("Original text: " + text);
        System.out.println("Encrypted text: " + encryptedText);
    }
}
