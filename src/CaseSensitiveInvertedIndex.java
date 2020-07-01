/** Singleton class which represents a non-case insensitive inverted index */
public class CaseSensitiveInvertedIndex extends AbstractInvertedIndex {

    // Static fields
    private static CaseSensitiveInvertedIndex instance = null;  // Instance object for the singleton


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
            instance = new CaseSensitiveInvertedIndex();
            System.out.println("New CaseSensitive index is created");
        } else {
            System.out.println("You already have a CaseSensitive index");
        }
        return instance;
    }


    // Methods
    @Override
    protected String getFormattedString(String str) {
        return str;
    }
}
