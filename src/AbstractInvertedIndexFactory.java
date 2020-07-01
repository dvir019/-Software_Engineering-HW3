/**
 * Represents one factory of an inverted index
 * <p>
 * Each type of inverted index factory extends this class, and implements the createInvertedIndex method.
 */
public abstract class AbstractInvertedIndexFactory {

    // Methods

    /**
     * Creates an Inverted index
     *
     * @return The Inverted index that was created
     */
    public abstract AbstractInvertedIndex createInvertedIndex();
}
