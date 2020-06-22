public class CaseSensitiveFactory extends AbstractInvertedIndexFactory{
    @Override
    public AbstractInvertedIndex createInvertedIndex() {
        if (invertedIndex == null) {
            invertedIndex = new CaseSensitiveInvertedIndex();
            System.out.println("New CaseSensitive index is created");
        } else {
            System.out.println("You already have a CaseSensitive index");
        }
        return invertedIndex;
    }
}
