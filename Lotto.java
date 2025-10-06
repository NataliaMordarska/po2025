import java.util.Random;

public class Lotto {

    public static void main(String[] args) {
        Random random = new Random();
        int[] liczby = new int[6];
        int count = 0;

        while (count < 6) {
            int num = random.nextInt(49) + 1;
            boolean istnieje = false;

            
            for (int i = 0; i < count; i++) {
                if (liczby[i] == num) {
                    istnieje = true;
                    break;
                }
            }

            if (!istnieje) {
                liczby[count] = num;
                count++;
            }
        }

        System.out.print("Wylosowane liczby: ");
        for (int liczba : liczby) {
            System.out.print(liczba + " ");
        }
    }
}