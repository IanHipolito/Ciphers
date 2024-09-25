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

    public static boolean isValidInputv(String text) {
        return text.matches("[a-zA-Z]+");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String text;
        int shift;

        while(true) {
            System.out.println("Enter the text to ENCRYPT: ");
            text = sc.nextLine();

            if (isValidInputv(text)) {
                break;
            } else {
                System.out.println("***ERROR: INVALID INPUT - PLEASE ONLY INPUT ALPHABATIC CHARACTERS***");
            }
        }

        System.out.println("Enter the SHIFT value: ");
        shift = sc.nextInt();
        String encrypted = Encrypt(text, shift);
        System.out.println("Encrypted text: " + encrypted);
        String decrypted = Decrypt(encrypted, shift);
        System.out.println("Decrypted text: " + decrypted);
        sc.close();
    }
}