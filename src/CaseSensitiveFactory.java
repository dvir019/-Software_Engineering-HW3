/** Represents a factory of a case sensitive inverted index */
public class CaseSensitiveFactory extends AbstractInvertedIndexFactory{
    @Override
    public AbstractInvertedIndex createInvertedIndex() {
        return CaseSensitiveInvertedIndex.getInstance();
    }
}
