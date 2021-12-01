package pack4_task;

public enum TokenType {
    moo,
    mOo,
    moO,
    mOO,
    Moo,
    MOo,
    MoO,
    MOO,
    OOO,
    MMM,
    OOM,
    oom;

    public static TokenType getByIndex(int index) {
        return values()[index];
    }

    public static TokenType convertStrToToken(String input) {
        try {
            return valueOf(input);
        } catch(IllegalArgumentException e) {
            return null;
        }
    }
}
