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
    protected String getFormattedString(String str) {
        return str;
    }
}
