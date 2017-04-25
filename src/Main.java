import in.futurezoom.iban.IbanChecker;

/**
 * Created by rubinovk on 25.04.17.
 */
public class Main {

    public static void main(String[] args) {
        IbanChecker checker = new IbanChecker();
        String ibans[] =
                {"CH9300762011623852957", "GB04BARC20474473160944", "DE79850503003100180568",
                        "FR7630004003200001019471656"};
        for (String iban : ibans) {
            System.out.println(checker.check(iban));
        }

        System.out.println();

        String invalid_ibans[] =
                {"CH93400762011623852957", "ИB04BARC20474473160944", "DE79",
                        "FR7630004003200001019471656FR7630004003200001019471656"};
        for (String iban : invalid_ibans) {
            System.out.println(checker.check(iban));
        }

    }
}
