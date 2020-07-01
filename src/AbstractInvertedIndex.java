import java.io.File;
import java.util.*;

public abstract class AbstractInvertedIndex {
    // Fields
    HashMap<String, TreeSet<String>> map;


    // Constants
    private static final List<String> tags = Arrays.asList("<DOC>", "<DOCNO>", "<TEXT>", "</TEXT>", "</DOC>");
    protected static final String QUERY_AND = "AND";
    protected static final String QUERY_OR = "OR";
    protected static final String QUERY_NOT = "NOT";


    // Constructor
    protected AbstractInvertedIndex() {
        map = new HashMap<>();
    }


    // Abstract Methods
    /**
     * Formats a given string based on the type of the inverted index
     * <p>
     * Given a string, the method formats it to match to type of the inverted index.
     * For example, the format corresponds to the insensitive inverted index is a lowe cased string.
     * <p>
     * Note: The operators are not being formatted.
     *
     * @param str The string to format
     * @return The formatted string
     */
    protected abstract String getFormattedString(String str);


    // Methods
    /**
     * Builds the inverted index using the given files array
     * <p>
     * The method iterates over all of the words in all of the files, and adds each
     * word to the inverted index.
     *
     * @param files The files array to build the inverted index from
     */
    public void buildInvertedIndex(File[] files) {
        for (File file : files) {
            List<String> lines = readLines(file);
            String fileName = file.getName();
            for (String line : lines)
                if (!isTagLineOrEmpty(line))
                    addLineToMap(fileName, line);
        }
    }

    /**
     * Runs a given query
     * <p>
     * The method iterates over all of the words in the query, executes the operators,
     * and returns the result of the query.
     * <p>
     * Note: The query is given in reverse polish form, and assumed to be valid.
     *
     * @param query The query to execute
     * @return The result of the query
     */
    public TreeSet<String> runQuery(String query) {
        String[] words = Utils.splitBySpace(query);
        Stack<TreeSet<String>> stack = new Stack<>();
        for (String word : words) {
            TreeSet<String> treeSetToStack;
            String formattedWord = getFormattedString(word);
            switch (formattedWord) {
                case QUERY_AND:
                    treeSetToStack = queryAnd(stack);
                    break;
                case QUERY_OR:
                    treeSetToStack = queryOr(stack);
                    break;
                case QUERY_NOT:
                    treeSetToStack = queryNot(stack);
                    break;
                default:
                    treeSetToStack = queryWord(formattedWord);
                    break;
            }
            stack.push(treeSetToStack);
        }
        return stack.pop();
    }

    /**
     * Reads the line of a given file, formats them returns them as a list
     * <p>
     * The method iterates over all of the lines in the file, formats,
     * adds the non-tag ones to a list, and returns the list.
     *
     * @param file The file to read from
     * @return List of formatted lines from the file
     */
    private List<String> readLines(File file) {
        List<String> fileLines = Utils.readLines(file);
        List<String> formattedFileLines = new ArrayList<>();
        for (String line : fileLines) {
            if (!isTagLineOrEmpty(line)) {
                formattedFileLines.add(getFormattedString(line));
            }
        }
        return formattedFileLines;
    }

    /**
     * Adds the words of given line from a given file to the hashmap
     *
     * @param fileName The name of the file
     * @param line     The line to add its words
     */
    private void addLineToMap(String fileName, String line) {
        String[] words = Utils.splitBySpace(line);
        for (String word : words)
            addWordToMap(fileName, word);
    }

    /**
     * Adds the given word of a given file to the hashmap
     *
     * @param fileName The name of the file
     * @param word     The word to add
     */
    private void addWordToMap(String fileName, String word) {
        if (!map.containsKey(word)) {
            TreeSet<String> valuesFiles = new TreeSet<>();
            map.put(word, valuesFiles);
        }
        map.get(word).add(fileName);
    }

    /**
     * Checks whether or not a given line is a tag line or empty line
     *
     * @param line The line to check
     * @return true if it is a tag line or empty line, and false if it doesn't
     */
    protected boolean isTagLineOrEmpty(String line) {
        String[] words = Utils.splitBySpace(line);
        if (words.length == 0)
            return true;
        String firstWord = words[0];
        return tags.contains(firstWord);
    }

    /**
     * Performs the AND operator of the query
     * <p>
     * The methods pops the last two treeSets from the given stack, finds their intersection,
     * and returns the result.
     *
     * @param stack The query's stack
     * @return The result of the AND operator
     */
    private TreeSet<String> queryAnd(Stack<TreeSet<String>> stack) {
        TreeSet<String> firstTreeSet = stack.pop();
        TreeSet<String> secondTreeSet = stack.pop();
        TreeSet<String> returnTree = new TreeSet<>(secondTreeSet);
        returnTree.retainAll(firstTreeSet);
        return returnTree;
    }

    /**
     * Performs the OR operator of the query
     * <p>
     * The methods pops the last two treeSets from the given stack, unites them,
     * and returns the result.
     *
     * @param stack The query's stack
     * @return The result of the OR operator
     */
    private TreeSet<String> queryOr(Stack<TreeSet<String>> stack) {
        TreeSet<String> firstTreeSet = stack.pop();
        TreeSet<String> secondTreeSet = stack.pop();
        TreeSet<String> returnTree = new TreeSet<>(secondTreeSet);
        returnTree.addAll(firstTreeSet);
        return returnTree;
    }

    /**
     * Performs the NOT operator of the query
     * <p>
     * The methods pops the last two treeSets from the given stack,
     * exclude the first one from the second, and returns the result.
     *
     * @param stack The query's stack
     * @return The result of the NOT operator
     */
    private TreeSet<String> queryNot(Stack<TreeSet<String>> stack) {
        TreeSet<String> firstTreeSet = stack.pop();
        TreeSet<String> secondTreeSet = stack.pop();
        TreeSet<String> returnTree = new TreeSet<>(secondTreeSet);
        returnTree.removeAll(firstTreeSet);
        return returnTree;
    }

    /**
     * Gets the treeSet corresponds to a given word
     * <p>
     * Note: Assuming that the word exists in the hashmap
     *
     * @param formattedWord The word from the query
     * @return The treeSet corresponds to the word
     */
    private TreeSet<String> queryWord(String formattedWord) {
        TreeSet<String> t = map.get(formattedWord);
        return new TreeSet<>(t);
    }
}
