/** Singleton class which represents a case insensitive inverted index */
public class CaseInsensitiveInvertedIndex extends AbstractInvertedIndex {

    // Static fields
    private static CaseInsensitiveInvertedIndex instance = null;  // Instance object for the singleton


    // Static methods
    /**
     * Gets the instance object
     * <p>
     * If the object had not been created yet, the method creates it, and prints a message about the creation.
     * If the object had been created, a message will be printed as well.
     *
     * @return The instance object
     */
    public static AbstractInvertedIndex getInstance() {
        if (instance == null) {
            instance = new CaseInsensitiveInvertedIndex();
            System.out.println("New CaseInsensitive index is created");
        } else {
            System.out.println("You already have a CaseInsensitive index");
        }
        return instance;
    }


    // Methods
    @Override
    protected String getFormattedString(String str) {
        if (str.equals(QUERY_AND) || str.equals(QUERY_OR) || str.equals(QUERY_NOT))
            return str;
        return str.toLowerCase();
    }
}
