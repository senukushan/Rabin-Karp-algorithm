import java.io.*;
import java.util.*;

public class PatternMatcher {

    static class Entry {
        int index;
        String text;
        Entry(int index, String text) {
            this.index = index;
            this.text = text;
        }
    }

    public static List<Entry> parseFile(String filePath, int textColumnIndex) throws IOException {
        List<Entry> entries = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine(); // Skip header
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.trim().split("\\s*,\\s*");
                if (parts.length > textColumnIndex) {
                    int index = Integer.parseInt(parts[0]);
                    String text = parts[textColumnIndex].trim();
                    entries.add(new Entry(index, text));
                }
            }
        }
        return entries;
    }

    public static List<Object[]> rabinKarpMatch(List<Entry> patterns, List<Entry> words) {
        List<Object[]> results = new ArrayList<>();
        for (Entry pattern : patterns) {
            List<String[]> matches = new ArrayList<>();
            for (Entry word : words) {
                String wordText = word.text;
                String patternText = pattern.text;
                for (int i = 0; i <= wordText.length() - patternText.length(); i++) {
                    if (wordText.substring(i, i + patternText.length()).equals(patternText)) {
                        int start = i + 1;
                        int end = i + patternText.length();
                        matches.add(new String[]{wordText, String.valueOf(start), String.valueOf(end)});
                    }
                }
            }
            // Sort matches by word and start position
            matches.sort(Comparator.comparing((String[] arr) -> arr[0]).thenComparing(arr -> Integer.parseInt(arr[1])));
            results.add(new Object[]{pattern.index, pattern.text, matches});
        }
        return results;
    }

    public static void writeOutput(String filePath, List<Object[]> results) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write("Pattern index, jth pattern, Matching words and its location range(s)\n\n");
            for (Object[] result : results) {
                int patternIndex = (int) result[0];
                String patternText = (String) result[1];
                @SuppressWarnings("unchecked")
                List<String[]> matches = (List<String[]>) result[2];
                bw.write(patternIndex + ", " + patternText + ",\n");
                if (!matches.isEmpty()) {
                    for (String[] match : matches) {
                        bw.write("        " + match[0] + " (" + match[1] + "-" + match[2] + ");\n");
                    }
                } else {
                    bw.write("        No matching found\n");
                }
                bw.write("\n");
            }
        }
    }

    public static void main(String[] args) {
        // Update these file paths as needed
        String hvalParaFile = "C:\\Users\\User\\OneDrive\\Desktop\\hvalpara.txt";
        String hvalPattFile = "C:\\Users\\User\\OneDrive\\Desktop\\hValPatt.txt";
        String outputFile = "C:\\Users\\User\\OneDrive\\Desktop\\New folder (4)\\patMatch4.txt";

        try {
            // For both files, the word/pattern is in column 1 (zero-based)
            List<Entry> words = parseFile(hvalParaFile, 1);
            List<Entry> patterns = parseFile(hvalPattFile, 1);
            List<Object[]> results = rabinKarpMatch(patterns, words);
            writeOutput(outputFile, results);
            System.out.println("Pattern matching completed. Results saved to " + outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
