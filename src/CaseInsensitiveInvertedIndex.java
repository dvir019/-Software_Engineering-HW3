import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CaseInsensitiveInvertedIndex extends AbstractInvertedIndex {
    @Override
    protected List<String> readLines(File file) {  // TODO maybe move to AbstractInvertedIndex, and add isLowercase boolean parameter
        List<String> fileLines = Utils.readLines(file);
        List<String> lowercaseFileLines = new ArrayList<>();
        for (String line : fileLines) {
            if (!isTagLine(line)) {
                lowercaseFileLines.add(line.toLowerCase());
            }
        }
        return lowercaseFileLines;
    }
}
