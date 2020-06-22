import java.io.File;
import java.util.TreeSet;

/**
 * The main user class.
 */
public class DocumentRetrieval {

    /**
     * Pass two arguments: 1. The path of the directory of documents, 2. The path of the boolean query file
     *
     * @param args
     */
    public static void main(String[] args) {

//        AbstractInvertedIndexFactory caseInsensitiveFactory = new CaseInsensitiveFactory();
//        AbstractInvertedIndexFactory caseSensitiveFactory = new CaseSensitiveFactory();
//        AbstractInvertedIndex caseInsensitiveIndex = caseInsensitiveFactory.createInvertedIndex();
//        AbstractInvertedIndex caseInsensitiveIndexTwo = caseInsensitiveFactory.createInvertedIndex();
//        AbstractInvertedIndex caseSensitiveIndex = caseSensitiveFactory.createInvertedIndex();
//        AbstractInvertedIndex caseSensitiveIndexTwo = caseSensitiveFactory.createInvertedIndex();
//
//        caseInsensitiveIndex.buildInvertedIndex((new File(args[0])).listFiles());
//        caseSensitiveIndex.buildInvertedIndex((new File(args[0])).listFiles());
//
//        for (String query : Utils.readLines(new File(args[1]))) {
//            System.out.println("######################################");
//            System.out.println("Query: " + query);
//            System.out.println("----NonCaseSensitiveIndex----");
//            Utils.printList(caseInsensitiveIndex.runQuery(query));
//            System.out.println("----CaseSensitiveIndex----");
//            Utils.printList(caseSensitiveIndex.runQuery(query));
//        }

        TreeSet<String> t1 = new TreeSet<>();
        TreeSet<String> t2 = new TreeSet<>();
        TreeSet<String> t = new TreeSet<>();

        t1.add("a");
        t1.add("b");
        t1.add("z");
        t1.add("x");
        t1.add("c");

        t2.add("a");
        t2.add("x");
        t2.add("kj");

        t.addAll(t1);
        t.removeAll(t2);

        for (String s : t)
            System.out.print(s+",");
        System.out.println();
        for (String s : t1)
            System.out.print(s+",");
        System.out.println();
        for (String s : t2)
            System.out.print(s+",");
    }
}
