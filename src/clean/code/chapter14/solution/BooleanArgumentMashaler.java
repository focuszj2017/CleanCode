package clean.code.chapter14.solution;

import java.util.Iterator;

public class BooleanArgumentMashaler implements ArgumentMarshaler {

    private boolean booleanValue = false;

    public BooleanArgumentMashaler() {
    }

    @Override
    public void set(Iterator<String> currentArgument) throws ArgsException {
        booleanValue = true;
    }

    public static boolean getValue(ArgumentMarshaler marshaler) {
        if (marshaler != null && marshaler instanceof BooleanArgumentMashaler) {
            return ((BooleanArgumentMashaler) marshaler).booleanValue;
        }
        return false;
    }
}
