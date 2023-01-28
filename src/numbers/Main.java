package numbers;


import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        amazingNumbers();
    }

    public static void amazingNumbers() {
        System.out.println("Welcome to Amazing Numbers!");
        UserInput.printRequests();

        UserInput uInput;
        TNumber bz;
        State uInputState;

        while (true) {
            System.out.print("\nEnter a request: > ");
            uInput = new UserInput();
            uInputState = uInput.process();



            if (uInputState == State.EXIT)
                break;

            if (uInputState == State.START_OVER)
                continue;

            if (uInput.numOfInputs == 1) {
                bz = new TNumber(uInput.dividedLongInputs[0]);
                bz.printStateSingle();
            } else if (uInput.numOfInputs == 2) {
                long firstIntInput = uInput.dividedLongInputs[0];
                long secondIntInput = uInput.dividedLongInputs[1];

                for (long i = firstIntInput; i < firstIntInput + secondIntInput; i++) {
                    bz = new TNumber(i);
                    bz.printStateDouble();
                }
                System.out.println();
            } else {

                long firstIntInput = uInput.dividedLongInputs[0];
                long secondIntInput = uInput.dividedLongInputs[1];
                int matchCount = 0;

                for (long i = firstIntInput; matchCount < secondIntInput; i++) {
                    bz = new TNumber(i);
                    boolean match;

                    match = bz.printStateWprops(uInput.dividedStrInputs, uInput.startPropsIndex);

                    if (match)
                        matchCount++;
                }
                System.out.println();
            }
        }

        System.out.println("Goodbye!");
    }
}


