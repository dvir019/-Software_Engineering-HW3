import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public abstract class AbstractInvertedIndex {
    // Fields
    HashMap<String, TreeSet<String>> map;

    public AbstractInvertedIndex() {
        map = new HashMap<>();
    }


    // Methods
    public void buildInvertedIndex(File[] files) {
        for (File file : files) {
            List<String> lines = readLines(file);
            String fileName = file.getName();
            for (String line : lines)
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
}
