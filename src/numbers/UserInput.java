package numbers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static numbers.Properties.areBadProps;

enum State {
    IN_PROGRESS,
    START_OVER,
    EXIT
}

class UserInput {
    static Scanner sc = new Scanner(System.in);

    String strInput;
    String[] dividedStrInputs;
    long[] dividedLongInputs;
    int numOfInputs;
    int startPropsIndex = 2;

    UserInput() {
        requestInput();
        computeInputFields();
    }

    void requestInput() {
        strInput = sc.nextLine();
    }

    void computeInputFields() {
        dividedStrInputs = strInput.split(" ");
        numOfInputs = dividedStrInputs.length;
        dividedLongInputs = new long[numOfInputs];
    }

    State process() {
        switch (numOfInputs) {
            case 1: return process1field();
            case 2: return process2field();
            default: return processProperties();
        }
    }

    State processProperties() {
        State state = process2field();

        switch (state) {
            case START_OVER: return State.START_OVER;
            case EXIT: return State.EXIT;
        }

        ArrayList<String> wrongProps = new ArrayList<>();

        //make props UPPERCASE
        for (int i = startPropsIndex; i < dividedStrInputs.length; i++) {
            dividedStrInputs[i] = dividedStrInputs[i].toUpperCase();
        }

        //check input 3 err for all props
        boolean[] arePropsOk = new boolean[dividedStrInputs.length];
        Arrays.fill(arePropsOk, true);

        for (int i = startPropsIndex; i < dividedStrInputs.length; i++) {
            String strInput = dividedStrInputs[i].toUpperCase();
            ErrType err = Error.checkInputPropErr(strInput);
            if (err == ErrType.ERR_3)
                arePropsOk[i] = false;
        }

        //emit errors
        if (areBadProps(arePropsOk, startPropsIndex)) {
            StringBuilder sb = new StringBuilder();
            for (int i = startPropsIndex; i < arePropsOk.length; i++) {
                if (arePropsOk[i] == false) {
                    wrongProps.add(dividedStrInputs[i]);
                    sb.append(dividedStrInputs[i] + ", ");
                }
            }

            if (wrongProps.size() == 1) {
                Error.emitSinglePropWrong(wrongProps.get(0));
                return State.START_OVER;
            }

            String strErr = sb.toString().substring(0, sb.length() - 2);
            Error.emitPropsWrong(strErr);
            return State.START_OVER;
        }

        //check for mutually exclusive properties
        for (int i = startPropsIndex; i < dividedStrInputs.length; i++) {
            for (int j = i; j < dividedStrInputs.length - 1; j++) {
                String strField1 = dividedStrInputs[i];
                String strField2 = dividedStrInputs[j+1];
                if (Error.checkExclusiveProps(strField1, strField2) == ErrType.ERR_5) {
                    //ahtung
                    Error.emitErrMutExProps(strField1, strField2);
                    return State.START_OVER;
                }
            }
        }

        return State.IN_PROGRESS;
    }

    //process 1-field input
    State process1field() {
        State state = Error.checkInput1Err(dividedStrInputs[0]);

        switch (state) {
            case START_OVER: return State.START_OVER;
            case EXIT: return State.EXIT;
        }

        dividedLongInputs[0] = Long.parseLong(dividedStrInputs[0]);

        return State.IN_PROGRESS;
    }

    //process 2-fields input
    State process2field() {
        //field 1
        State state = process1field();

        switch (state) {
            case START_OVER: return State.START_OVER;
            case EXIT: return State.EXIT;
        }

        //field 2
        state = Error.checkInput2Err(dividedStrInputs[1]);

        switch (state) {
            case START_OVER: return State.START_OVER;
            case EXIT: return State.EXIT;
        }

        dividedLongInputs[1] = Long.parseLong(dividedStrInputs[1]);

        return State.IN_PROGRESS;
    }

    //process 3-fields input
    State process3field() {
        State state = process2field();

        switch (state) {
            case START_OVER: return State.START_OVER;
            case EXIT: return State.EXIT;
        }

        String strInput3 = dividedStrInputs[2].toUpperCase();

        ErrType err = Error.checkInputPropErr(strInput3);

        switch (err) {
            case ERR_3:
                Error.emitSinglePropWrong(strInput3);
                return State.START_OVER;
        }

        return State.IN_PROGRESS;
    }

    State process4field() {
        State state = process2field();

        switch (state) {
            case START_OVER: return State.START_OVER;
            case EXIT: return State.EXIT;
        }

        String strInput3 = dividedStrInputs[2].toUpperCase();
        ErrType err = Error.checkInputPropErr(strInput3);
        boolean isOkInput3 = true;

        //switch state?
        switch (err) {
            case ERR_3 -> isOkInput3 = false;
        }

        String strInput4 = dividedStrInputs[3].toUpperCase();
        err = Error.checkInputPropErr(strInput4);
        boolean isOkInput4 = true;

        if (err == ErrType.ERR_3)
            isOkInput4 = false;

        if (!isOkInput3 && !isOkInput4) {
            Error.emitErr4(strInput3, strInput4);
            return State.START_OVER;
        } else {
            if (!isOkInput3) {
                Error.emitSinglePropWrong(strInput3);
                return State.START_OVER;
            } else if (!isOkInput4) {
                Error.emitSinglePropWrong(strInput4);
                return State.START_OVER;
            }
        }

        //start mutually exclusive props
        err = Error.checkExclusiveProps(strInput3, strInput4);

        switch (err) {
            case ERR_5:
                Error.emitErrMutExProps(strInput3, strInput4);
                return State.START_OVER;
        }

        return State.IN_PROGRESS;
    }

    static void printRequests() {
        System.out.println("\nSupported requests:");
        System.out.println("- enter a natural number to know its properties;");
        System.out.println("- enter two natural numbers to obtain the properties of the list:");
        System.out.println("  * the first parameter represents a starting number;");
        System.out.println("  * the second parameter shows how many consecutive numbers are to be printed;");
        System.out.println("- two natural numbers and properties to search for;");
        System.out.println("- a property preceded by minus must not be present in numbers;");
        System.out.println("- separate the parameters with one space;");
        System.out.println("- enter 0 to exit.");
    }
}
