import java.util.Random;
import java.util.ArrayList; 

public class Lotto2 {
    public static void main(String[] args) {
        // Tworzymy obiekt ArrayList do przechowywania wylosowanych liczb
        ArrayList<Integer> numbers = new ArrayList<>();
        Random random = new Random();
            while (numbers.size() < 6) {
            //Generowanie losowej liczby calkowitej z podanego przedzialu
            int randomNumber = random.nextInt(49) + 1;

             if (!numbers.contains(randomNumber)) {
                numbers.add(randomNumber);
            }
        }                                                        

        
        System.out.println(numbers);
    }
}
    

