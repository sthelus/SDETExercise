import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: sid
 * Assigment: In the programming language of your choice create a class with a method to return the length and
 * longest words in a sentence. For example, “The cow jumped over the moon.” should return 6 and “jumped”.
 *
 * Thoughts: So there's two major approaches I can think of to this exercise. Each has its own pros and cons
 *
 * 1) Read in all of the text, tokenize/split the strings on the whitespace character. That way we get all the words.
 * We can put the words in a list and iterate through it until we get the longest word.
 *
 * Pros: We get easy access to all the words and in the event that we hit one of the edge cases we can anticipate: words
 * tied for longest, the longest word appearing more than once, etc. This might make it easier to access words of interest
 * and report them. Possibly easier to implement/write.
 *
 * Cons: This approach could be more space efficient. We'd be storing all the words into memory. There's no foreknowledge
 * of the data being read and it could be a huge text file. In that case, we'd be storing a lot of words that we don't care about
 * and they'd only exist to take up space. It might also take longer to execute this task than the other approach because
 * we're taking time to add each word to the list one by one and then loop over them all.
 *
 * 2) Use the java scanner class with the whitespace delimiter to read input in one word at a time. We can store the first
 * word in a variable, store the length, and then when we read a longer than our current word, we overwrite the variable
 * with the new longest word. That way, by the time we've read the entire thing, we should have the longest word stored.
 *
 * Pros: Doesn't use a ton of space, especially compared to the first approach. Solves the problem in one pass of the text
 * rather than two passes (going through the text once and then iterating over a list of the text). We still have the the issue
 * of keeping track of words tied for the longest, the longest word appearing more than once, etc.
 *
 * Cons: Not as simple/easy to write in my opinion. My approach will use a list to keep track of the words tied for the longest.
 * I feel there's probably a better/more space efficient way. This approach relies on the scanner, which might not be ideal
 * for the "production-ready" ask in the assignment. Rewriting the scanner class sounds a little like reinventing the wheel
 * for no good reason. So maybe there's a better approach for this specific assignment.
 *
 * Assumptions: Due to time constraints, the big assumption I'm going to make is that if the longest word appears more than
 * once, we don't care about keeping count. This next assumption, while also big, I feel is warranted. We're going to assume
 * that if the input is bad, that is, there is a non-alphabet character in the text, we're going to assume that it is ok to drop
 * all the non-alphabet characters from the text and proceed as normal. And then the last big assumption is that the text can
 * be inserted into the method directly. The assignment doesn't specify that we have to read it in from a file or from standard
 * input. We can easily add in that functionality, but making the program compatible to do file reading, standard input reading,
 * and more, AND to be smart enough to know which one to pick if multiple are given, or to just do them all if given, would take more time.
 *
 * Smaller assumptions: We don't care about casing. We check the input to make sure it's not empty. Lastly, and this might be
 * the biggest of the 'smaller' assumptions, but we're going to assume that we don't need to reference a dictionary to make sure
 * all the words are valid english words. Even if it's gibberish, we'll report it anyway.
 */

public class ExerciseApproach2 {

    private List<String> wordInText = new ArrayList();

    public List<String> getWordInText() {
        return wordInText;
    }

    public void setWordInText(List<String> wordInText) {
        this.wordInText = wordInText;
    }

    public static Set<String> getLongestWordAndSize(String inputText) {
        //The assignment specifies that the method is to return the word and length, not write it to console, we we'll return
        //a list of size 1, with both.

        List<String> wordsOfSameLength = new ArrayList();
        List<String> finalResults = new ArrayList<>();

        Matcher lackOfLettersMatcher = Pattern.compile("^[^A-Za-z]+$").matcher(inputText);
        Matcher lackOfWhitespaceMatcher = Pattern.compile("^[^\\s]+$").matcher(inputText);

        if(inputText.equals("")){
            System.out.println("The input is empty. Exiting");
            return null;
        }

        if(lackOfLettersMatcher.find()){
            System.out.println("There are no alphabetical characters in the input. Exiting.");
            return null;
        }

        if(lackOfWhitespaceMatcher.find()){
            System.out.println("The Input is only one word. Exiting.");
            return null;
        }
        //The regex here is to drop all the non-alphabet characters and populate the list
        Scanner scanner = new Scanner(inputText.replaceAll("[^A-Za-z\\s]", "")).useDelimiter(" ");

        String longestWord = "";
        int longestWordLength = 0;

        while(scanner.hasNext()){
            String word = scanner.next();

            if(word.length()==longestWord.length()){
                wordsOfSameLength.add(word);
                longestWord = word;
                longestWordLength = word.length();
            }

            if(word.length()>longestWord.length()){
                wordsOfSameLength.clear();
                wordsOfSameLength.add(word);
                longestWord = word;
                longestWordLength = word.length();
            }
        }
        //we do all this set mumbojumbo so that we don't have to return many copies of the same word if the input had
        //the same word repeated many times.
        System.out.println("Longest word(s) is/are: "+wordsOfSameLength+". Length is: "+longestWordLength);
        Set<String> results = new HashSet<>();
        results.addAll(wordsOfSameLength);
        results.add(String.valueOf(wordsOfSameLength.get(0).length()));
        System.out.println(results);
        return results;
    }

    public static void main(String args[]){
        getLongestWordAndSize("Frick and Frack in frickin’ frackin’");
    }
}
