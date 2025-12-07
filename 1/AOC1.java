import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC1 {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java <inputfile>");
            return;
        }

        String fileName = args[0];
        int position = 50;       
        long result = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                char dir = line.charAt(0);
                int distance = Integer.parseInt(line.substring(1).trim());

                for (int i = 0; i < distance; i++) {

                    if (dir == 'R') {
                        position = (position + 1) % 100;
                    } 
                    else if (dir == 'L') {
                        position = (position - 1 + 100) % 100;
                    }

                    if (position == 0) {
                        result++;
                    }
                }
            }

            System.out.println("Result: " + result);

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}
