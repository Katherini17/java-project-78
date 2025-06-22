package hexlet.code.schemas;

import lombok.Setter;

@Setter
public class StringSchema {
    private boolean isRequired;
    private int minStringLength;
    private String subString;

    public StringSchema() {
        this.isRequired = false;
        this.minStringLength = 0;
        this.subString = "";
    }

    public StringSchema required() {
        setRequired(true);
        return this;
    }

    public StringSchema minLength(int length) {
        setMinStringLength(length);
        if (length != 0) {
            setRequired(true);
        }
        return this;
    }

    public StringSchema contains(String text) {
        if (text != null && !text.isEmpty()) {
            setRequired(true);
            setSubString(text.toLowerCase());
        }

        setSubString(text);
        return this;
    }

    public boolean isValid(String text) {
        if (text == null) {
            return !isRequired;
        }

        if (text.isEmpty() && isRequired) {
            return false;
        }

        return text.length() >= minStringLength
                && text.toLowerCase().contains(subString);
    }
}
