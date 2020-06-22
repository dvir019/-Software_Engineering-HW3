import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

public abstract class AbstractInvertedIndex {
    // Fields
    HashMap<String, TreeSet<String>> map;

    // Constants
    //private static final String[] tags = {"<DOC>", };  // TODO finish the array

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
        return false;
    }
}
