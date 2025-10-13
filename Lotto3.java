import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Lotto3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> userNumbers = new ArrayList<>();
        System.out.println("Podaj 6 unikalnych liczb z przedziału 1-49:");

        // Uzytkownik
        while (userNumbers.size() < 6) {
            System.out.print("Liczba " + (userNumbers.size() + 1) + ": ");
            int number = scanner.nextInt();

            if (number < 1 || number > 49) {
                System.out.println("Liczba musi być w zakresie 1-49. Spróbuj ponownie.");
            } else if (userNumbers.contains(number)) {
                System.out.println("Ta liczba już została podana. Wprowadź inną.");
            } else {
                userNumbers.add(number);
            }
        }

        
        ArrayList<Integer> drawnNumbers = new ArrayList<>();
        Random random = new Random();
        while (drawnNumbers.size() < 6) {
            int randomNumber = random.nextInt(49) + 1;
            if (!drawnNumbers.contains(randomNumber)) {
                drawnNumbers.add(randomNumber);
            }
        }

        //  wyniki
        System.out.println("Wylosowane prze Ciebie lliczby: " + userNumbers);
        System.out.println("Wylosowane liczby: " + drawnNumbers);

        // trafiena
        ArrayList<Integer> matchedNumbers = new ArrayList<>();
        for (int num : userNumbers) {
            if (drawnNumbers.contains(num)) {
                matchedNumbers.add(num);
            }
        }

        System.out.println("Trafione : " + matchedNumbers);
        System.out.println("Liczba trafień: " + matchedNumbers.size());
    }
}