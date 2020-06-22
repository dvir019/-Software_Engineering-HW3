public class CaseInsensitiveFactory extends AbstractInvertedIndexFactory {
    @Override
    public AbstractInvertedIndex createInvertedIndex() {
        if (invertedIndex == null) {
            invertedIndex = new CaseInsensitiveInvertedIndex();
            System.out.println("New CaseInsensitive index is created");
        } else {
            System.out.println("You already have a CaseInsensitive index");
        }
        return invertedIndex;
    }
}
