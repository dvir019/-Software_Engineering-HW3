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

    protected AbstractInvertedIndex() {
        map = new HashMap<>();
    }


    // Methods
    public void buildInvertedIndex(File[] files) {
        for (File file : files) {
            List<String> lines = readLines(file);
            String fileName = file.getName();
            for (String line : lines)  // TODO Check for tag files, and ignore them
                if (!isTagLineOrEmpty(line))
                    addLineToMap(fileName, line);

        }
    }

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
        return stack.pop();  // TODO check if the queries are legal
    }

    protected abstract String getFormattedString(String str);

    private List<String> readLines(File file) {  // TODO maybe move to AbstractInvertedIndex, and add isLowercase boolean parameter
        List<String> fileLines = Utils.readLines(file);
        List<String> lowercaseFileLines = new ArrayList<>();
        for (String line : fileLines) {
            if (!isTagLineOrEmpty(line)) {
                lowercaseFileLines.add(getFormattedString(line));
            }
        }
        return lowercaseFileLines;
    }

    private void addLineToMap(String fileName, String line) {
        String[] words = Utils.splitBySpace(line);
        for (String word : words)
            addWordToMap(fileName, word);
    }

    private void addWordToMap(String fileName, String word) {
        if (!map.containsKey(word)) {
            TreeSet<String> valuesFiles = new TreeSet<>();
            map.put(word, valuesFiles);
        }
        map.get(word).add(fileName);
    }

    protected boolean isTagLineOrEmpty(String line) {  // TODO Add checks
        String[] words = Utils.splitBySpace(line);
        if (words.length == 0)
            return true;
        String firstWord = words[0];
        return tags.contains(firstWord);
    }

    private TreeSet<String> queryAnd(Stack<TreeSet<String>> stack) {
        TreeSet<String> firstTreeSet = stack.pop();
        TreeSet<String> secondTreeSet = stack.pop();
        TreeSet<String> returnTree = new TreeSet<>(firstTreeSet);
        returnTree.retainAll(secondTreeSet);
        return returnTree;
    }

    private TreeSet<String> queryOr(Stack<TreeSet<String>> stack) {
        TreeSet<String> firstTreeSet = stack.pop();
        TreeSet<String> secondTreeSet = stack.pop();
        TreeSet<String> returnTree = new TreeSet<>(firstTreeSet);
        returnTree.addAll(secondTreeSet);
        return returnTree;
    }

    private TreeSet<String> queryNot(Stack<TreeSet<String>> stack) {
        TreeSet<String> firstTreeSet = stack.pop();
        TreeSet<String> secondTreeSet = stack.pop();
        TreeSet<String> returnTree = new TreeSet<>(secondTreeSet);
        returnTree.removeAll(firstTreeSet);
        return returnTree;
    }

    private TreeSet<String> queryWord(String formattedWord) {
        TreeSet<String> t = map.get(formattedWord);
        if (t==null)
            System.out.println("Got null: " + formattedWord);
        return new TreeSet<>(t);
    }
}
