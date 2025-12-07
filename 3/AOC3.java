import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC3 {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java <inputfile>");
            return;
        }

        String fileName = args[0];

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
			long result = 0;

            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
				
				int largest = 0;
				int secondLargest = 0;

				for(char battery : line.toCharArray())
				{
					int current = Character.getNumericValue(battery);
					if(current == largest || current == secondLargest)
					{
						continue;
					}
					else if(current > largest)
					{
						secondLargest = largest;
						largest = current;
					}
					else if(current > secondLargest)
					{
						secondLargest = current;
					}
				}
				int jolage = (largest*10) + secondLargest;
				System.out.println("Joltage: "+ jolage);
				result += jolage;
            }
			
			System.out.println("Result: "+ result);

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
