The Rabin-Karp algorithm is useful for locating textual segments that match a set of characters, called 
the pattern. It hashes the pattern and compares it with the text’s substrings. Rather than performing a 
direct comparison of each substring with the target pattern, the Rabin-Karp algorithm computes a 
numeric value or hash for the target pattern and all its substrings having the same length in the text. 
The algorithm does character-by-character comparisons only after a hash value match. 
 
This is why in practical terms Rabin-Karp remains very useful when one needs to find several 
pattern’s occurrences in the single text or if one is searching for a single pattern within the large text 
the hash facilitates easier, faster searching than simple string comparisons.



The Rabin-Karp algorithm computes a hash value for the substring based on some rolling hash 
function. With a rolling hash function, the hash of a substring can be quickly updated as the substring 
window slides over the text. We need to avoid recalculating the hash from scratch for every new 
substring more than once; we want to only adjust the hash by removing effect of outgoing character, 
and adding effect of incoming character. 
   
   Example: Calculating the Hash Value for "an" 
 
1. Initialize Hash Value: Start with an initial hash value of 0. 
       2. Iterate through each character: 
• For each character in the string, update the hash value using the formula 
                                            hash = (hash × BASE + ASCII value of character) mod PRIME_MOD 
  
• Here, BASE is 256, and PRIME_MOD is 101. 
 
3. Calculate the Hash for Each Character: 
   - Let's go through each character of the string "an" to see how the hash is computed. 
 
    Step-by-Step Calculation for "an": 
 
➢ Initialize hash = 0. 
 
➢ Process the first character `a`: 
➢ ASCII value of `a` is 97. - Update hash: 
          hash = (0 × 256 + 97) mod 101 = 97 mod 101 = 97 
 
➢ Process the second character `n`: 
               ASCII value of `n` is 110. 
 
➢ Update hash: 
    hash = (97 × 256 + 110) mod 101 = 24942 mod 101 = 96 
 
   So, the rolling hash value of the string "an" is 96. 
 
General Steps for Calculating the Rolling Hash for Any String 
 
1. Set Initial Values: 
   -Set hash = 0. 
2. Loop Through Each Character: 
• For each character, compute its ASCII value. 
• Update the hash value using: 
          hash = (hash × BASE + ASCII value of character) mod PRIME_MOD 
 
3. Output the Final Hash Value: 
   - The final value of hash after processing all characters in the string is the rolling hash.
