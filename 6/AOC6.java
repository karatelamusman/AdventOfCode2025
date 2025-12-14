import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class AOC6 {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java <inputfile>");
            return;
        }

        String fileName = args[0];

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
			List<String> listOfLines = new ArrayList<>();

            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()){
					continue;		
				}
				listOfLines.add(line);
            }

			long result = PartOne(listOfLines); 
			System.out.println("Result: "+ result);
			
			//long result = ParTwo1(freshRange); 
			//System.out.println("Result: "+ result);
			
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
		
    }
	
	public static long PartOne(List<String> list){
		long result = 0;
		
		String[] signArray = list.get(list.size() - 1).split("\\s+");		
		
		long[][] converted = new long[list.size()-1][];
		
		for(int i = 0; i < list.size() - 1; i++){
			String currentLine = list.get(i);
			String[] strCur = currentLine.split("\\s+");
			converted[i] = new long[strCur.length];
			
			for (int j = 0; j < strCur.length; j++){
				converted[i][j] = Long.parseLong(strCur[j].trim());
			}
		}
		
		for(int col = 0; col < signArray.length; col++){
			String currentSign = signArray[col].trim();
			long loopTotal = 0;
			if("*".equals(currentSign)){
				loopTotal = 1;
			}
			
			for(int row=0; row < converted.length; row++) {
				if("*".equals(currentSign)){
					loopTotal *= converted[row][col];
				}else if("+".equals(currentSign)){
					loopTotal += converted[row][col];
				}
			}
			result += loopTotal;
		}
		return result;
	}
	
}
