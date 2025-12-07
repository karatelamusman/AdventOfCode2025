import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AOC2 {

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

				String[] idRanges = line.split(",");
				
				for (String range : idRanges)
				{
					range = range.trim();
					if (range.isEmpty()) continue;
					String[] strRange = range.split("-");
					long start = Long.parseLong(strRange[0]);
					long end = Long.parseLong(strRange[1]);
					//System.out.println("range: "+ range + ", start:" + start + ", end:" + end);
					for(long i = start; i <= end; i++)
					{
						String id = String.valueOf(i);
						
						/*if(id.length() % 2 != 0)
						{
							continue;
						}
						int mid = id.length() / 2;
						
						String firstPart = id.substring(0, mid);
						String secondPart = id.substring(mid);
						//System.out.println(" id: "+ id + "\t firstPart:"+firstPart+"\t secondPart:"+secondPart);
						if(firstPart.equals(secondPart))
						{
							result += i;
						}*/
						String doubled = id + id;
						String trimmed = doubled.substring(1, doubled.length() - 1);
						boolean repeated = trimmed.contains(id);
						if(repeated){
							result += i;
						}
					}
					//System.out.print("\n");
				}
            }
			
			System.out.println("Result: "+ result);

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
