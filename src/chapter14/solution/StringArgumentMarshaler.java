package chapter14.solution;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class StringArgumentMarshaler implements ArgumentMarshaler {
    private String stringValue = null;

    public StringArgumentMarshaler() {
    }

    @Override
    public void set(Iterator<String> currentArgument) throws ArgsException {
        try {
            stringValue = currentArgument.next();
        } catch (NoSuchElementException e) {
            throw new ArgsException(ArgsException.ErrorCode.MISSING_STRING);
        }
    }

    public static String getValue(ArgumentMarshaler marshaler) {
        if (marshaler != null && marshaler instanceof StringArgumentMarshaler) {
            return ((StringArgumentMarshaler) marshaler).stringValue;
        }
        return "";
    }
}
