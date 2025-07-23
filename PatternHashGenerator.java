import java.io.*; 
import java.util.*; 
 
public class PatternHashGenerator { 
    private static final int BASE = 256; // Base value for ASCII characters 
    private static final int PRIME_MOD = 101; // A small prime modulus for hashing 
 
    public static void main(String[] args) {    // Define the path to the input file containing patterns (Update the path as needed) 
        String inputFile = ""; // Update with actual path on your system 
        String outputFile = "hValPatt3.txt"; 
 
        try { 
            // Load patterns from the file 
            List<String> patterns = loadPatternsFromFile(inputFile); 
             
            // Generate hash values and write them to the output file 
            generateHashFile(patterns, outputFile); 
            System.out.println("Hash values saved to " + outputFile); 
        } catch (IOException e) { 
            System.err.println("An error occurred: " + e.getMessage()); 
        } 
    } 
 
    // Function to load patterns from a file, assuming each pattern is on a new line 
    private static List<String> loadPatternsFromFile(String inputFile) throws IOException { 
        List<String> patterns = new ArrayList<>(); 
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) { 
            String line; 
            while ((line = br.readLine()) != null) { 
                if (!line.trim().isEmpty()) { // Skip empty lines 
                    patterns.add(line.trim()); 
                } 
            } 
        } 
        return patterns; 
    } 
 
    // Function to calculate hash values and save results in the output file 
    private static void generateHashFile(List<String> patterns, String outputFile) throws IOException 
{ 
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) { 
            bw.write("Index, jth Pattern, Hash Value for jth Pattern\n"); // Column headers 
            int index = 1; 
            for (String pattern : patterns) { 
                int hashValue = calculateHash(pattern); 
                System.out.println(index + ", " + pattern + ", " + hashValue); // Print each result 
                bw.write(index + ", " + pattern + ", " + hashValue + "\n"); 
                index++; 
            } 
        } 
    } 
 
    // Function to calculate the hash for a given pattern using a basic rolling hash function 
    private static int calculateHash(String str) { 
        int hash = 0; 
        for (int i = 0; i < str.length(); i++) { 
            hash = (hash * BASE + str.charAt(i)) % PRIME_MOD; // Update hash for each character 
        } 
        return hash; 
    } 
}
