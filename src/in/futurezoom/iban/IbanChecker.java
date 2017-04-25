package in.futurezoom.iban;

import java.math.BigInteger;

import static java.lang.Character.UnicodeBlock.of;

/**
 * Created by rubinovk on 25.04.17.
 */
public class IbanChecker {

    // A == 65
    private static int offset = 55;

    /**
     * Check that the total IBAN length is correct as per the country. If not, the IBAN is invalid
     * 1. Move the four initial characters to the end of the string
     * 2. Replace each letter in the string with two digits, thereby expanding the string, where
     * A = 10, B = 11, ..., Z = 35
     * 3. Interpret the string as a decimal integer and compute the remainder of that number on
     * division by 97
     * 4. If the remainder is 1, the check digit test is passed and the IBAN might be valid.
     *
     * @param iban
     * @return
     */
    public boolean check(String iban) {

        System.out.println("Check: " + iban);

        if (iban.length() < 15 || iban.length() > 34) {
            return false;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(iban.substring(4));
        sb.append(iban.substring(0, 4));

        System.out.println("Move: " + sb.toString());

        for (int i = 0; i < sb.length(); i++) {
            Character c = sb.charAt(i);
            if (!Character.isDigit((int) c)) {
                if (Character.isLetter((int) c) && Character.UnicodeBlock.BASIC_LATIN == of(c)) {
                    sb.replace(i, i + 1, String.valueOf((int) c - offset));
                } else {
                    // invalid IBAN code, contains something other than digits and letters
                    return false;
                }
            }
        }

        System.out.println("Interpret:" + sb.toString());

        BigInteger integerValue = new BigInteger(sb.toString());
        return integerValue.remainder(BigInteger.valueOf(97)).equals(BigInteger.valueOf(1));
    }
}
