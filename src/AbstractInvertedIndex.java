import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;
import java.util.TreeSet;

public abstract class AbstractInvertedIndex {
    // Fields
    HashMap<String, TreeSet<String>> map;

    // Constants
    //private static final String[] tags = {"<DOC>", };  // TODO finish the array
    private static final String QUERY_AND = "and";
    private static final String QUERY_OR = "or";
    private static final String QUERY_NOT = "not";

    public AbstractInvertedIndex() {
        map = new HashMap<>();
    }


    // Methods
    public void buildInvertedIndex(File[] files) {
        for (File file : files) {
            List<String> lines = readLines(file);
            String fileName = file.getName();
            for (String line : lines)  // TODO Check for tag files, and ignore them
                if (!isTagLine(line))
                    addLineToMap(fileName, line);

        }
    }

    public TreeSet<String> runQuery(String query) {
        String[] words = Utils.splitBySpace(query);
        Stack<TreeSet<String>> stack = new Stack<>();
        for (String word : words) {
            TreeSet<String> treeSetToStack;
            switch (word.toLowerCase()) {
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
                    treeSetToStack = queryWord(word);
                    break;
            }
            stack.push(treeSetToStack);
        }
        return stack.pop();  // TODO check if the queries are legal
    }

    protected abstract List<String> readLines(File file);

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

    protected boolean isTagLine(String line) {  // TODO Add checks
        return line.startsWith("<");
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

    private TreeSet<String> queryWord(String word) {
        TreeSet<String> t = map.get(this instanceof CaseSensitiveInvertedIndex ? word : word.toLowerCase());
        //System.out.println(""+ word + " " + (t == null));
        return new TreeSet<>(t);
    }
}
