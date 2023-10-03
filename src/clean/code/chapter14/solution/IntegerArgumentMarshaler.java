package clean.code.chapter14.solution;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static clean.code.chapter14.solution.ArgsException.ErrorCode.*;

public class IntegerArgumentMarshaler implements ArgumentMarshaler {
    private int intValue = 0;

    public IntegerArgumentMarshaler() {
    }

    @Override
    public void set(Iterator<String> currentArgument) throws ArgsException {
        String parameter = null;
        try {
            parameter = currentArgument.next();
            intValue = Integer.parseInt(parameter);
        } catch (NoSuchElementException e) {
            throw new ArgsException(MISSING_INTEGER);
        } catch (NumberFormatException e) {
            throw new ArgsException(INVALID_INTEGER, parameter);
        }
    }

    public static int getValue(ArgumentMarshaler marshaler) {
        if (marshaler != null && marshaler instanceof IntegerArgumentMarshaler) {
            return ((IntegerArgumentMarshaler) marshaler).intValue;
        }
        return 0;
    }
}
