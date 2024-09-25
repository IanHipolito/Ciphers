import java.util.Scanner;

public class CaesarCipher {
    public static String Encrypt(String text, int shift) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char character = text.charAt(i);

            if (Character.isUpperCase(character)) {
                char ch = (char)(((int) character + shift - 65) % 26 + 65);
                result.append(ch);
            } else if (Character.isLowerCase(character)) {
                char ch = (char)(((int) character + shift - 97) % 26 + 97);
                result.append(ch);
            } else {
                result.append(character);
            }
        }
        return result.toString();
    }

    public static String Decrypt(String text, int shift) {
        return Encrypt(text, 26 - shift);
    }

    
}