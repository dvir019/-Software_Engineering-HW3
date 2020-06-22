import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

public abstract class AbstractInvertedIndex {
    // Fields
    HashMap<String, TreeSet<String>> map;

    public AbstractInvertedIndex(){
        map = new HashMap<>();
    }


    // Methods
    public void buildInvertedIndex(File[] files) {
        for (File file : files){
            List<String> lines = Utils.readLines(file);
            String fileName = file.getName();
            for (String line : lines)

        }
    }

    protected abstract void addLineToMap(String fileName, String line);
}
