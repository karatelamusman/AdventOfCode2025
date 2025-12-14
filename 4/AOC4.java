import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class AOC4 {

	//private static Map<Integer, Integer> rolls = new HashMap<>();
	private static List<AbstractMap.SimpleEntry<Integer, Integer>> rolls = new ArrayList<>();
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java <inputfile>");
            return;
        }

        String fileName = args[0];
		List<List<Character>> charMatrix = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
				
				List<Character> innerList = new ArrayList<>();
			
				for(char battery : line.toCharArray())
				{
					innerList.add(battery);
				}
				
				charMatrix.add(innerList);
            }

			long totalResult = 0; 
			long result = 0; 
			do {
				result = CalculateRollsAccess(charMatrix);
				totalResult += result;
				
				for (AbstractMap.SimpleEntry<Integer, Integer> entry : rolls) {
					Integer key = entry.getKey();
					Integer value = entry.getValue();
					charMatrix.get(key).set(value, 'X');
					//System.out.println("Key: " + key + ", Value: " + value);
				}
				System.out.println("Inn Result: " + result + "rolls.Size:" + rolls.size());
				rolls.clear();
			} while(result != 0);
			
			System.out.println("Result: "+ totalResult);
			
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
		
    }
	
	private static long CalculateRollsAccess(List<List<Character>> charMatrix)
	{
		long result = 0;
		for(int i = 0; i < charMatrix.size(); i++)
		{
			for (int j = 0; j < charMatrix.get(i).size(); j++){
				Character current = charMatrix.get(i).get(j);
				if(current != '@') continue;
				
				int nearByAt = getNearByAt(charMatrix, i, j);
				if(nearByAt < 4) {
					rolls.add(new AbstractMap.SimpleEntry<>(i, j));
					result++;
				}
			}
		}
		return result;
	}
	
	private static int getNearByAt(List<List<Character>> charMatrix, int row, int column)
	{
		int nearByAt = 0;
		int maxRows = charMatrix.size()-1;
		int maxColumns = charMatrix.get(0).size()-1;
		
		if(column+1 <= maxColumns && charMatrix.get(row).get(column+1) == '@'){
			//rolls.put(row, column+1);
			nearByAt++;
		}
		if(column-1 >= 0 && charMatrix.get(row).get(column-1) == '@'){
			//rolls.put(row, column-1);
			nearByAt++;
		}
		
		if(row-1 >= 0){
			if(column-1 >= 0 && charMatrix.get(row-1).get(column-1) == '@'){
				//rolls.put(row-1, column-1);
				nearByAt++;
			}
			if(charMatrix.get(row-1).get(column) == '@'){
				//rolls.put(row-1, column);
				nearByAt++;
			}
			if(column+1 <= maxColumns && charMatrix.get(row-1).get(column+1) == '@'){
				//rolls.put(row-1, column+1);
				nearByAt++;	
			}
		}
		
		if(row+1 <= maxRows){
			if(column-1 >= 0 && charMatrix.get(row+1).get(column-1) == '@'){
				//rolls.put(row+1, column-1);
				nearByAt++;
			}
			if(charMatrix.get(row+1).get(column) == '@'){
				//rolls.put(row+1, column);
				nearByAt++;
			}
			if(column+1 <= maxColumns && charMatrix.get(row+1).get(column+1) == '@'){
				//rolls.put(row+1, column+1);
				nearByAt++;	
			}
		}
		
		return nearByAt;
	}
}
