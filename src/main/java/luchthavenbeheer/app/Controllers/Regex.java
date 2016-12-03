package luchthavenbeheer.app.Controllers;

import javafx.scene.control.TextField;

import java.util.regex.Pattern;

public class Regex
{
    public enum Regexs{
        Name("^[A-Z][-a-zA-Z]+$"),
        AccountNr("[a-zA-Z]{2}[0-9]{2}[a-zA-Z0-9]{4}[0-9]{7}([a-zA-Z0-9]?){0,16}"),
        FourDigitNr("\\d{4}"),
        OneDigitNrNotNul("^[1-9]"),
        OneDigitNr("^[0-9]"),
        Integer("^-?[0-9]+$");

        private final String regex;

        private Regexs(final String regex) {
            this.regex = regex;
        }

        @Override
        public String toString() {
            return regex;
        }
    }

    public Boolean CheckFieldValues(Regex.Regexs reg, TextField txt)
    {
        if(Pattern.matches(reg.toString(), txt.getText()))
        {
            return true;
        }
        return false;
    }
}
