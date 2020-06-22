public class CaseInsensitiveFactory extends AbstractInvertedIndexFactory{
    @Override
    public AbstractInvertedIndex createInvertedIndex() {
        return new CaseInsensitiveInvertedIndex();
    }
}
