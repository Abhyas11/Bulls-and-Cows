package bullscows;

import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int secretCodeLength = 0;
        int numOfPossibleSymbols = 0;
        String[] array = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
        System.out.println("Input the length of the secret code:");
        String scl = scanner.nextLine();
        try {
            secretCodeLength = Integer.parseInt(scl);
        }catch (NumberFormatException e) {
            System.out.println("Error");
            System.exit(0);
        }

        System.out.println("Input the number of possible symbols in the code:");
        String nops = scanner.nextLine();
        try {
            numOfPossibleSymbols = Integer.parseInt(nops);
        }catch (NumberFormatException e) {
            System.out.println("Error");
            System.exit(0);
        }

        String secretCode = "";
        String stars = "";
        String range = "";

        if(numOfPossibleSymbols < secretCodeLength || numOfPossibleSymbols!=(int)numOfPossibleSymbols || numOfPossibleSymbols > 36 || secretCodeLength > 36 || secretCodeLength < 0 || secretCodeLength != (int)secretCodeLength ) {
            System.out.println("Error");
            System.exit(0);
        }

        if(secretCodeLength == 0 && numOfPossibleSymbols > 0){
            System.out.println("Error");
            System.exit(0);
        }

        for(int i = 0; i < secretCodeLength; i++) {
            stars += "*";
        }

        if(secretCodeLength > 36) {
            System.out.println("Error");
        } else {
            for(int i = 0; i < secretCodeLength; i++) {
                String add = array[random.nextInt(numOfPossibleSymbols)];
                while(secretCode.contains(add)){
                    add = array[random.nextInt(numOfPossibleSymbols)];
                }
                secretCode += add;
            }
        }

        if(numOfPossibleSymbols > 10) {
            range = " (0-9, " + array[10] + "-" + array[numOfPossibleSymbols-1] + ").";
        }else{
            range = " (" + array[0] + "-" + array[numOfPossibleSymbols-1] + ").";
        }


        System.out.println("The secret is prepared: " + stars + secretCode + range);
        System.out.println("Okay, let's start a game!");


        boolean run = true;
        int turn = 1;

        while(run){
            int bulls = 0;
            int cows = 0;

            String code = scanner.nextLine();
            System.out.println("Turn " + turn + ":");

            for(int i = 0; i < code.length(); i++) {
                if(code.charAt(i) == secretCode.charAt(i)) {
                    bulls++;
                } else if (secretCode.contains(String.valueOf(code.charAt(i)))) {
                    cows++;
                }
            }

            if (bulls > 0 && cows > 0) {
                System.out.printf("Grade: %d bull(s) and %d cow(s). The secret code is %s.", bulls, cows, secretCode);
                System.out.println();
            } else if (bulls > 0) {
                System.out.printf("Grade: %d bull(s). The secret code is %s.", bulls, secretCode);
                System.out.println();
            } else if (cows > 0) {
                System.out.printf("Grade: %d cow(s). The secret code is %s.", cows, secretCode);
                System.out.println();
            } else {
                System.out.println("Grade: None. The secret code is " + secretCode);
            }

            if(bulls == secretCodeLength) {
                run = false;
            }else{
                bulls = 0;
                cows = 0;
                turn++;
            }
        }
        System.out.println("Congratulations! You guessed the secret code.");
    }
}