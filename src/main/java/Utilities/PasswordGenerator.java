package Utilities;

import java.util.Random;

public class PasswordGenerator {
    public static String characterGenerator(int længde){
        String[] tal2 = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        char[] chars = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        String[] special2 = new String[]{".", "-", "_", "+", "!", "?", "="};
        Random r = new Random();
        int type = r.nextInt(4) + 1;
        int bogstaver = r.nextInt(chars.length);
        int tal = r.nextInt(tal2.length);
        int special = r.nextInt(special2.length);
        String character="";
        for (int i = 0; i < længde - 1; i++) {
            if (type == 1) {
                character += chars[bogstaver];
                break;
            } else if (type == 2) {
                character += chars[bogstaver];
                break;
            } else if (type == 3) {
                character += tal2[tal];
                break;
            } else if (type == 4) {
                character += special2[special];
                break;
            }
        }
        return character;
    }
    public static String password(){
        Random r = new Random();
        int længde = r.nextInt(13 - 11) + 11;
        String password = "";
        for(int i=0; i<længde-1; i++){
            password+=characterGenerator(længde);
        }
        return password;
    }
}