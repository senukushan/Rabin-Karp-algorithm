 import java.io.*; 
import java.util.*; 
 
public class RabinKarpHashGenerator { 
    private static final int BASE = 256; // Base value for ASCII characters 
    private static final int PRIME_MOD = 101; // A small prime modulus for hashing 
 
    public static void main(String[] args) { 
        // Specify the input file containing the paragraph text 
        String inputFile = "";        // Specify the output file where the hash values will be saved 
        String outputFile = "hValPara3.txt"; 
 
        try { 
            // Read text from the input file 
            String paragraphText = readFile(inputFile); 
 
            // Process the paragraph text to extract words 
            List<String> words = extractWordsFromText(paragraphText); 
 
            // Generate hash values and write them to the output file 
            generateHashFile(words, outputFile); 
            System.out.println("Hash values saved to " + outputFile); 
        } catch (IOException e) { 
            System.err.println("An error occurred: " + e.getMessage()); 
        } 
    } 
 
    // Function to read text from a file 
    private static String readFile(String filePath) throws IOException { 
        StringBuilder content = new StringBuilder(); 
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) { 
            String line; 
            while ((line = br.readLine()) != null) { 
                content.append(line).append(" "); 
            } 
        } 
        return content.toString(); 
    } 
 
    // Function to extract words from the paragraph text 
    private static List<String> extractWordsFromText(String text) { 
        List<String> words = new ArrayList<>(); 
        // Split the text by whitespace and clean each word 
        for (String word : text.split("\\s+")) { 
            // Remove non-alphabet characters 
            word = word.replaceAll("[^A-Za-z]", ""); 
            if (!word.isEmpty()) { 
                words.add(word); 
            } 
        } 
        return words; 
    } 
 
    // Function to calculate hash values and save results in the output file 
    private static void generateHashFile(List<String> words, String outputFile) throws IOException { 
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) { 
            bw.write("Index, ith Word, Length of ith Word, Hash Value for ith Word\n"); // Column headers 
            int index = 1; 
            for (String word : words) { 
                int hashValue = calculateHash(word); 
                bw.write(index + ", " + word + ", " + word.length() + ", " + hashValue + "\n"); 
                index++; 
            } 
        } 
    } 
 
    // Function to calculate the hash for a given word using a basic rolling hash function 
    private static int calculateHash(String str) { 
        int hash = 0; 
        for (int i = 0; i < str.length(); i++) { 
            hash = (hash * BASE + str.charAt(i)) % PRIME_MOD; 
        } 
        return hash; 
    } 
}
