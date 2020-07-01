public class CaseSensitiveFactory extends AbstractInvertedIndexFactory{
    @Override
    public AbstractInvertedIndex createInvertedIndex() {
        return CaseSensitiveInvertedIndex.getInstance();
    }
}
