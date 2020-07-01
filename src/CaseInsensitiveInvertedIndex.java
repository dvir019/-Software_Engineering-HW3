import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CaseInsensitiveInvertedIndex extends AbstractInvertedIndex {

    private static CaseInsensitiveInvertedIndex instance = null;

    public static AbstractInvertedIndex getInstance(){
        if (instance == null){
            instance = new CaseInsensitiveInvertedIndex();
            System.out.println("New CaseInsensitive index is created");
        } else {
            System.out.println("You already have a CaseInsensitive index");
        }
        return instance;
    }

    @Override
    protected List<String> readLines(File file) {  // TODO maybe move to AbstractInvertedIndex, and add isLowercase boolean parameter
        List<String> fileLines = Utils.readLines(file);
        List<String> lowercaseFileLines = new ArrayList<>();
        for (String line : fileLines) {
            if (!isTagLineOrEmpty(line)) {
                lowercaseFileLines.add(line.toLowerCase());
            }
        }
        return lowercaseFileLines;
    }

    @Override
    protected String getFormattedString(String str) {
        if (str.equals(QUERY_AND) || str.equals(QUERY_OR) || str.equals(QUERY_NOT))
            return str;
        return str.toLowerCase();
    }
}
