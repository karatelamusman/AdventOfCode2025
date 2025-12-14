import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class AOC5 {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java <inputfile>");
            return;
        }

        String fileName = args[0];
		List<String> freshRange = new ArrayList<>();
		List<Long> ingredients = new ArrayList<>();
		boolean reachedSpace = false;

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()){
					reachedSpace = true;
					continue;		
				}
				
				if(reachedSpace){
					//ingredients
					//ingredients.add(Long.parseLong(line));
				} else {
					//fresh ranges
					freshRange.add(line);
				}
            }

			//long result = PartOne(freshRange, ingredients); 
			//System.out.println("Result: "+ result);
			
			long result = ParTwo1(freshRange); 
			System.out.println("Result: "+ result);
			
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
		
    }
	
	public static long PartOne(List<String> freshRange, List<Long> ingredients){
		long result = 0; 
		for(Long ingredient : ingredients)
		{
			for (String range : freshRange){
				String[] str = range.split("-");
				long min = Long.parseLong(str[0]);
				long max = Long.parseLong(str[1]);
				if(ingredient == min || ingredient == max
				|| (ingredient > min && ingredient < max)){
					result++;
					break;
				}
				
			}
		}
		return result;
	}
	
	public static long ParTwo(List<String> freshRange){
		long result = 0;
		List<AbstractMap.SimpleEntry<Long, Long>> ranges = new ArrayList<>();
		for (String range : freshRange){
			String[] str = range.split("-");
			long min = Long.parseLong(str[0]);
			long max = Long.parseLong(str[1]);
			ranges.add(new AbstractMap.SimpleEntry<Long, Long>(min, max));
		}
		
		ranges.sort((entry1, entry2) -> entry1.getKey().compareTo(entry2.getKey()));
		
	
		for(int i = 0; i < ranges.size(); i++){
			AbstractMap.SimpleEntry<Long, Long> currentRange = ranges.get(i);
			System.out.println("Key:"+ currentRange.getKey() + "::Value:"+ currentRange.getValue() + "::J::");
			if(i == 0) {
				result += currentRange.getValue() - currentRange.getKey() + 1;
				System.out.print("\t\t Result:" + result + "\n");
				continue;
			}
			
			for(int j = i - 1; j >= 0; j--){
				AbstractMap.SimpleEntry<Long, Long> loopRange = ranges.get(j);
				System.out.print(j);
				//System.out.print("\t lKey: " + currentRange.getKey() + "," + loopRange.getValue() + " ::IsTrue:"+ (currentRange.getKey().longValue() == loopRange.getValue().longValue()) +  "\n");
				if(currentRange.getKey().longValue() > loopRange.getValue().longValue()) {
					long a = currentRange.getValue().longValue() - currentRange.getKey().longValue() + 1;
					if(a > 0) result += a;
					System.out.print("\t\t 1 Result:" + result + "");
					break;
				} else if (currentRange.getKey().longValue() == loopRange.getKey().longValue()) {
					long a = currentRange.getValue().longValue() - (loopRange.getValue().longValue() + 1) + 1;
					if(a > 0) result += a;
					System.out.print("\t\t 2 Result:" + result + "");
					break;
				} else if (currentRange.getKey().longValue() == loopRange.getValue().longValue()) {
					long a = currentRange.getValue().longValue() - (currentRange.getKey().longValue() + 1) + 1;
					if(a > 0) result += a;
					System.out.print("\t\t 3 Result:" + result + "");
					break;
				} else if (currentRange.getKey().longValue() > loopRange.getKey().longValue() && currentRange.getKey().longValue() < loopRange.getValue().longValue()){
					long a = currentRange.getValue().longValue() - (loopRange.getValue().longValue() + 1) + 1;
					if(a > 0) result += a;
					System.out.print("\t\t 4 Result:" + result + "");
					break;
				} else {
					System.out.println("else: Key"+ currentRange.getKey() + "::Value:"+ currentRange.getValue());
				}	
				
			}
			System.out.print("\n");
		}
		return result;
	}
	
	public static long ParTwo1(List<String> freshRange) {

		List<AbstractMap.SimpleEntry<Long, Long>> ranges = new ArrayList<>();

		for (String range : freshRange) {
			String[] str = range.split("-");
			long min = Long.parseLong(str[0]);
			long max = Long.parseLong(str[1]);
			ranges.add(new AbstractMap.SimpleEntry<>(min, max));
		}

		// sort by start
		ranges.sort((a, b) -> a.getKey().compareTo(b.getKey()));

		long result = 0;

		// initialize with first range
		long lastStart = ranges.get(0).getKey();
		long lastEnd   = ranges.get(0).getValue();

		for (int i = 1; i < ranges.size(); i++) {

			long curStart = ranges.get(i).getKey();
			long curEnd   = ranges.get(i).getValue();

			// overlap or touching
			if (curStart <= lastEnd + 1) {
				lastEnd = Math.max(lastEnd, curEnd);
			} else {
				// no overlap → finalize previous range
				result += (lastEnd - lastStart + 1);
				lastStart = curStart;
				lastEnd = curEnd;
			}
		}

		// add last range
		result += (lastEnd - lastStart + 1);

		return result;
	}

	
	
}
