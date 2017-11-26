package actions;

/**
 * An enumeration to represent different client activities.
 * !!! Required for 12/2: Create, Read, Update, Delete. !!!
 * @author Matthew Phillips
 */
public enum WstAction {

    WELCOME("Welcome to WA State Trails Database"),

    CREATE("Add trail"),

    READ("Get trail info"),

    UPDATE("Modify trail"),

    DELETE("Remove trail");

    /**
     * Get this string using WstAction.[enum].toString()
     */
    private final String literal;

    private WstAction(final String literal) {
        this.literal = literal;
    }

    public String value() {
        return literal;
    }
}
