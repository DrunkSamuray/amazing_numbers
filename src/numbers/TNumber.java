package numbers;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

class TNumber {
    long value;

    boolean isNatural;
    boolean isOdd;
    boolean isBuzz;
    boolean isDuck;
    boolean isPalindromic;
    boolean isGapful;
    boolean isSpy;
    boolean isSquare;
    boolean isSunny;
    boolean isJumping;
    boolean isHappy;
    boolean isSad;

    String explanation;

    //Value divided in digits
    ArrayList<Long> vDigits;

    TNumber(long value) {
        this.value = value;
        vDigits = splitIntoDigits(value);

        setIsNatural();
        setIsBuzz();
        setIsOdd();
        setIsDuck();
        setIsPalindromic();
        setIsGapful();
        setIsSpy();
        setIsSquare();
        setIsSunny();
        setIsJumping();
        setIsHappy();
        setIsSad();
    }

    void printStateSingle() {
        DecimalFormat df = new DecimalFormat("###,###,###");
        String strValue = df.format(value);

        System.out.println("\nProperties of " + strValue);
        System.out.println("\t\tbuzz: " + isBuzz);
        System.out.println("\t\tduck: " + isDuck);
        System.out.println(" palindromic: " + isPalindromic);
        System.out.println("\t  gapful: " + isGapful);
        System.out.println("\t\t spy: " + isSpy);
        System.out.println("\t  square: " + isSquare);
        System.out.println("\t   sunny: " + isSunny);
        System.out.println("\t\teven: " + !isOdd);
        System.out.println("\t\t odd: " + isOdd);
        System.out.println("\tjumping: " + isJumping);
        System.out.println("\t\thappy: " + isHappy);
        System.out.println("\t\t\tsad: " + isSad);
    }

    void printStateDouble() {
        String strStateOutput = "";

        if (isBuzz)
            strStateOutput += "buzz, ";
        if (isGapful)
            strStateOutput += "gapful, ";
        if (isSpy)
            strStateOutput += "spy, ";
        if (!isOdd)
            strStateOutput += "even, ";
        if (isOdd)
            strStateOutput += "odd, ";
        if (isDuck)
            strStateOutput += "duck, ";
        if (isPalindromic)
            strStateOutput += "palindromic, ";
        if (isSquare)
            strStateOutput += "square, ";
        if (isSunny)
            strStateOutput += "sunny, ";
        if (isJumping)
            strStateOutput += "jumping, ";
        if (isHappy)
            strStateOutput += "happy, ";
        if (isSad)
            strStateOutput += "sad, ";


        if (!strStateOutput.equals("")) {
            strStateOutput = strStateOutput.substring(0, strStateOutput.length() - 2);
        }

        System.out.print("\n\t\t\t " + value + " is " + strStateOutput);
    }

    boolean printStateTriple(String property) {
        boolean allow = false;

        switch (property.toUpperCase()) {
            case "EVEN": if (!isOdd) allow = true;
            break;

            case "ODD": if (isOdd) allow = true;
            break;

            case "BUZZ": if (isBuzz) allow = true;
                break;

            case "DUCK": if (isDuck) allow = true;
                break;

            case "PALINDROMIC": if (isPalindromic) allow = true;
                break;

            case "GAPFUL": if (isGapful) allow = true;
                break;

            case "SPY": if (isSpy) allow = true;
                break;

            case "SQUARE": if (isSquare) allow = true;
                break;

            case "SUNNY": if (isSunny) allow = true;
                break;

            case "JUMPING": if (isJumping) allow = true;
                break;
        }

        if (allow) {
            printStateDouble();
            return true;
        }
        return false;
    }

    boolean printStateQuadro(String property1, String property2) {
        boolean allow1 = false;
        boolean allow2 = false;

        switch (property1.toUpperCase()) {
            case "EVEN": if (!isOdd) allow1 = true;
                break;

            case "ODD": if (isOdd) allow1 = true;
                break;

            case "BUZZ": if (isBuzz) allow1 = true;
                break;

            case "DUCK": if (isDuck) allow1 = true;
                break;

            case "PALINDROMIC": if (isPalindromic) allow1 = true;
                break;

            case "GAPFUL": if (isGapful) allow1 = true;
                break;

            case "SPY": if (isSpy) allow1 = true;
                break;

            case "SQUARE": if (isSquare) allow1 = true;
                break;

            case "SUNNY": if (isSunny) allow1 = true;
                break;

            case "JUMPING": if (isJumping) allow1 = true;
                break;
        }

        switch (property2.toUpperCase()) {
            case "EVEN": if (!isOdd) allow2 = true;
                break;

            case "ODD": if (isOdd) allow2 = true;
                break;

            case "BUZZ": if (isBuzz) allow2 = true;
                break;

            case "DUCK": if (isDuck) allow2 = true;
                break;

            case "PALINDROMIC": if (isPalindromic) allow2 = true;
                break;

            case "GAPFUL": if (isGapful) allow2 = true;
                break;

            case "SPY": if (isSpy) allow2 = true;
                break;

            case "SQUARE": if (isSquare) allow2 = true;
                break;

            case "SUNNY": if (isSunny) allow2 = true;
                break;

            case "JUMPING": if (isJumping) allow2 = true;
                break;
        }

        if (allow1 && allow2) {
            printStateDouble();
            return true;
        }
        return false;
    }


    //Принимает решение, можно ли печатать номер, исходя из переданных свойств
    boolean printStateWprops(String[] props, int startIndex) {
        //System.out.println("debug: properties: " + Arrays.toString(props));
        boolean allow = false;


        //count plus props
        int plusPropsCounter = 0;

        for (int i = startIndex; i < props.length; i++) {
            if (props[i].charAt(0) != '-')
                plusPropsCounter++;
        }

        String[] newPlusProps;

        //make a new array
        if (plusPropsCounter == 0) {
            allow = true;
        } else {
            newPlusProps = new String[plusPropsCounter];

            for (int i = startIndex, k = 0; i < props.length; i++) {
                if (props[i].charAt(0) != '-')
                    newPlusProps[k++] = props[i];
            }

            //System.out.println("debug: plusProperties[]: " + Arrays.toString(newPlusProps));

            for (int i = 0; i < newPlusProps.length; i++) {
                allow = false;

                switch (newPlusProps[i]) {
                    case "EVEN" ->        {if (!isOdd)        allow = true; }
                    case "ODD" ->         {if (isOdd)         allow = true; }
                    case "BUZZ" ->        {if (isBuzz)        allow = true; }
                    case "DUCK" ->        {if (isDuck)        allow = true; }
                    case "PALINDROMIC" -> {if (isPalindromic) allow = true; }
                    case "GAPFUL" ->      {if (isGapful)      allow = true; }
                    case "SPY" ->         {if (isSpy)         allow = true; }
                    case "SQUARE" ->      {if (isSquare)      allow = true; }
                    case "SUNNY" ->       {if (isSunny)       allow = true; }
                    case "JUMPING" ->     {if (isJumping)     allow = true; }
                    case "HAPPY" ->       {if (isHappy)       allow = true; }
                    case "SAD" ->         {if (isSad)         allow = true; }
                }

                if (!allow) break;
            }
        }

        //rest of function
        boolean allowMinus = allowMinusProps(props, startIndex);

        if (allow && allowMinus) {
            printStateDouble();
        }
        return allow && allowMinus;
    }


    //По умолчанию она все разрешает, запрещая только опцию с минусом
    boolean allowMinusProps(String[] props, int startIndex) {
        boolean allow = true;

        for (int i = startIndex; i < props.length; i++) {
            switch (props[i]) {
                case "-EVEN" ->        {if (!isOdd) allow = false; }
                case "-ODD" ->         {if (isOdd) allow = false;  }
                case "-BUZZ" ->        {if (isBuzz) allow = false;}
                case "-DUCK" ->        {if (isDuck) allow = false; }
                case "-PALINDROMIC" -> {if (isPalindromic) allow = false;}
                case "-GAPFUL" ->      {if (isGapful) allow = false; }
                case "-SPY" ->         {if (isSpy) allow = false; }
                case "-SQUARE" ->      {if (isSquare) allow = false;}
                case "-SUNNY" ->       {if (isSunny) allow = false; }
                case "-JUMPING" ->     {if (isJumping) allow = false; }
                case "-HAPPY" ->       {if (isHappy) allow = false; }
                case "-SAD" ->         {if (isSad) allow = false; }
            }
        }

        return allow;
    }

    void setIsBuzz() {
        boolean buzzConditionFirst  = value % 7 == 0;
        boolean buzzConditionSecond = value % 10 == 7;

        if (buzzConditionFirst && buzzConditionSecond) {
            isBuzz = true;
            explanation = value + " is divisible by 7 and ends with 7";
        }

        if (buzzConditionFirst || buzzConditionSecond) {
            isBuzz = true;

            if (buzzConditionFirst) {
                explanation = value + " is divisible by 7";
            } else {
                explanation = value + " ends with 7";
            }
        }

        if (!buzzConditionFirst && !buzzConditionSecond) {
            isBuzz = false;
            explanation = value + " is neither divisible by 7 nor does it end with 7";
        }
    }

    void setIsOdd() {
        if (value % 2 != 0)
            isOdd = true;
        else
            isOdd = false;
    }

    void setIsNatural() {
        if (value > 0)
            isNatural = true;
        else
            isNatural = false;
    }

    void setIsDuck() {
        for (long digit : vDigits) {
            if (digit == 0) {
                isDuck = true;
                return;
            }
        }
    }

    void setIsGapful() {
        if (vDigits.size() < 3) {
            isGapful = false;
            return;
        }

        long firstDigit = vDigits.get(vDigits.size() - 1);
        long lastDigit  = vDigits.get(0);

        long divisor = Long.parseLong(String.valueOf(firstDigit) + String.valueOf(lastDigit));

        if (value % divisor == 0)
            isGapful = true;
        else
            isGapful = false;
    }

    void setIsPalindromic() {
        for (int i = 0, j = vDigits.size() - 1; i < vDigits.size() && j >= 0; i++, j--) {
            if (vDigits.get(i) != vDigits.get(j)) {
                isPalindromic = false;
                return;
            }
        }
        isPalindromic = true;
    }

    void setIsSpy() {
        int sum = 0;

        for (int i = 0; i < vDigits.size(); i++) {
            sum += vDigits.get(i);
        }

        int product = 1;

        for (int i = 0; i < vDigits.size(); i++) {
            product *= vDigits.get(i);
        }

        if (sum == product)
            isSpy = true;
        else
            isSpy = false;

        /*
        System.out.println("isSpy debug: ");
        System.out.println("vDigits size: " + vDigits.size());
        System.out.printf("sum = %d, product = %d\n", sum, product);
        System.out.println("sum == product? " + (sum == product));
        System.out.println("vDigits:");
        for (long digit : vDigits)
            System.out.print(digit + " ");
        System.out.printf("\n");
        */
    }

    void setIsSquare() {
        if (isSquare(value))
            isSquare = true;
        else
            isSquare = false;
    }

    void setIsSunny() {
        if (isSquare(value + 1))
            isSunny = true;
        else
            isSunny = false;
    }

    void setIsJumping() {
        if (vDigits.size() == 1) {
            isJumping = true;
            return;
        }

        for (int i = 0; i < vDigits.size() - 1; i++) {
            long diff = Math.abs(vDigits.get(i) - vDigits.get(i+1));
            if (diff != 1) {
                isJumping = false;
                return;
            }
        }
        isJumping = true;
    }

    void setIsHappy() {
        ArrayList<Long> digits = vDigits;

        long sqSum = 0;
        int numOfDigits;

        do {
            sqSum = 0;

            for (int i = digits.size() - 1; i >= 0; i--) {
                sqSum += Math.pow(digits.get(i), 2);
            }

            digits = splitIntoDigits(sqSum);
            numOfDigits = computeNumOfDigits(sqSum);

        } while (numOfDigits > 1);

        if (sqSum == 1) {
            isHappy = true;
        }
    }

    void setIsSad() {
        if (!isHappy)
            isSad = true;
    }

    boolean isSquare(long value) {
        double sqrt = Math.sqrt(value);
        long iPart = (long) sqrt;
        double fPart = sqrt - iPart;
        if (fPart == 0)
            return true;

        return false;
    }

    ArrayList<Long> splitIntoDigits(long result) {
        ArrayList<Long> digits = new ArrayList<>();

        while (result > 0) {
            digits.add(result % 10);
            result /= 10;
        }

        return digits;
    }

    int computeNumOfDigits(long num) {
        if (num == 0) return 1;

        int numOfDigits = 0;

        while (num != 0) {
            num /= 10;
            numOfDigits++;
        }

        return numOfDigits;
    }
}
