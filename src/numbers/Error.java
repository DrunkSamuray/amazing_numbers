package numbers;

import static numbers.UserInput.printRequests;

enum ErrType {
    ERR_NO,
    ERR_1,
    ERR_2,
    ERR_3,
    ERR_4,
    ERR_5
}

public class Error {
    static final String PROPERTIES_STR = Properties.getPropertiesString();
    static final String AVAILABLE_PROPS = "Available properties: [" + PROPERTIES_STR + "]";

    static final String ERR_1ST_NOT_NATURAL_NOT_ZERO = "The first parameter should be a natural number or zero.";
    static final String ERR_2ND_NOT_NATURAL = "The second parameter should be a natural number.";
    static final String ERR_3_PROPERTY_WRONG = "The property [%s] is wrong.\n" + AVAILABLE_PROPS;
    static       String ERR_4_PropertiesWrong = "The properties [%s] are wrong.\n" + AVAILABLE_PROPS;

    static final String ERR_5_MUTUALLY_EXCLUSIVE_PROPS = "The request contains mutually exclusive properties: [%s, %s]\n" +
            "There are no numbers with these properties.";


    static State checkInput1Err(String field) {
        long longField;

        if (field.equals("0"))
            return State.EXIT;

        if (field.equals("")) {
            printRequests();
            return State.START_OVER;
        }

        if (!Character.isDigit(field.charAt(0))) {
            System.out.println("\n" + ERR_1ST_NOT_NATURAL_NOT_ZERO);
            return State.START_OVER;
        }

        longField = Long.parseLong(field);

        if (longField < 0) {
            System.out.println("\n" + ERR_1ST_NOT_NATURAL_NOT_ZERO);
            return State.START_OVER;
        }

        return State.IN_PROGRESS;
    }

    static State checkInput2Err(String field) {
        long longField = Long.parseLong(field);

        if (longField <= 0) {
            System.out.println("\n" + Error.ERR_2ND_NOT_NATURAL);
            return State.START_OVER;
        }

        return State.IN_PROGRESS;
    }

    static ErrType checkInputPropErr(String field) {
        if (!Properties.isOk(field)) {
            return ErrType.ERR_3;
        }

        return ErrType.ERR_NO;
    }

    static ErrType checkExclusiveProps(String field1, String field2) {

        if (field1.equals("EVEN") && field2.equals("ODD"))
            return ErrType.ERR_5;
        if (field1.equals("ODD") && field2.equals("EVEN"))
            return ErrType.ERR_5;
        if (field1.equals("-EVEN") && field2.equals("-ODD"))
            return ErrType.ERR_5;
        if (field1.equals("-ODD") && field2.equals("-EVEN"))
            return ErrType.ERR_5;

        if (field1.equals("DUCK") && field2.equals("SPY"))
            return ErrType.ERR_5;
        if (field1.equals("SPY") && field2.equals("DUCK"))
            return ErrType.ERR_5;
        if (field1.equals("-DUCK") && field2.equals("-SPY"))
            return ErrType.ERR_5;
        if (field1.equals("-SPY") && field2.equals("-DUCK"))
            return ErrType.ERR_5;

        if (field1.equals("SUNNY") && field2.equals("SQUARE"))
            return ErrType.ERR_5;
        if (field1.equals("SQUARE") && field2.equals("SUNNY"))
            return ErrType.ERR_5;
        if (field1.equals("-SUNNY") && field2.equals("-SQUARE"))
            return ErrType.ERR_5;
        if (field1.equals("-SQUARE") && field2.equals("-SUNNY"))
            return ErrType.ERR_5;

        if (field1.equals("SAD") && field2.equals("HAPPY"))
            return ErrType.ERR_5;
        if (field1.equals("HAPPY") && field2.equals("SAD"))
            return ErrType.ERR_5;
        if (field1.equals("-SAD") && field2.equals("-HAPPY"))
            return ErrType.ERR_5;
        if (field1.equals("-HAPPY") && field2.equals("-SAD"))
            return ErrType.ERR_5;

        if (field1.equals("-" + field2) || field2.equals("-" + field1))
            return ErrType.ERR_5;

        return ErrType.ERR_NO;
    }

    static void emitSinglePropWrong(String field) {
        String strErr = String.format(ERR_3_PROPERTY_WRONG, field.toUpperCase());
        System.out.println("\n" + strErr);
    }

    static void emitErr4(String field1, String field2) {
        String strErr = String.format(ERR_4_PropertiesWrong, field1.toUpperCase(), field2.toUpperCase());
        System.out.println("\n" + strErr);
    }

    //emit mutually exclusive props error
    static void emitErrMutExProps(String field1, String field2) {
        String strErr = String.format(ERR_5_MUTUALLY_EXCLUSIVE_PROPS, field1.toUpperCase(), field2.toUpperCase());
        System.out.println("\n" + strErr);
    }

    static void emitPropsWrong(String wrongProps) {
        String strErr = String.format(ERR_4_PropertiesWrong, wrongProps);
        System.out.println("\n" + strErr);
    }
}
