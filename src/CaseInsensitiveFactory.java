/** Represents a factory of a non-case sensitive inverted index */
public class CaseInsensitiveFactory extends AbstractInvertedIndexFactory {
    @Override
    public AbstractInvertedIndex createInvertedIndex() {
        return CaseInsensitiveInvertedIndex.getInstance();
    }
}
