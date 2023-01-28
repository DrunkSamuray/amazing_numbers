package numbers;

public class Properties {
    static final String[] PROPERTIES = new String[] {
            "EVEN",
            "ODD",
            "BUZZ",
            "DUCK",
            "PALINDROMIC",
            "GAPFUL",
            "SPY",
            "SQUARE",
            "SUNNY",
            "JUMPING",
            "HAPPY",
            "SAD"
    };

    static String getPropertiesString() {
        String result = "";
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < PROPERTIES.length; i++) {
            sb.append(PROPERTIES[i]);
            sb.append(", ");
        }

        result = sb.toString().substring(0, sb.length() - 2);
        return result;
    }

    static boolean isOk(String property) {
        int len = PROPERTIES.length;
        for (int i = 0; i < len; i++) {
            if (property.equals(PROPERTIES[i]) || property.equals("-" + PROPERTIES[i]))
                return true;
        }
        return false;
    }

    static boolean areBadProps(boolean[] arePropsOk, int startIndex) {
        for (int i = startIndex; i <arePropsOk.length ; i++) {
            if (arePropsOk[i] == false)
                return true;
        }
        return false;
    }
}
