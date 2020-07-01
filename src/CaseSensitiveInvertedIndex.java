import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CaseSensitiveInvertedIndex extends AbstractInvertedIndex {

    private static CaseSensitiveInvertedIndex instance = null;

    public static AbstractInvertedIndex getInstance() {
        if (instance == null) {
            instance = new CaseSensitiveInvertedIndex();
            System.out.println("New CaseSensitive index is created");
        } else {
            System.out.println("You already have a CaseSensitive index");
        }
        return instance;
    }

    @Override
    protected List<String> readLines(File file) {  // TODO maybe move to AbstractInvertedIndex, and add isLowercase boolean parameter
        List<String> fileLines = Utils.readLines(file);
        List<String> lowercaseFileLines = new ArrayList<>();
        for (String line : fileLines) {
            if (!isTagLineOrEmpty(line)) {
                lowercaseFileLines.add(line);
            }
        }
        return lowercaseFileLines;
    }
}
