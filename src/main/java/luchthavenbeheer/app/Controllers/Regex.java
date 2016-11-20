package luchthavenbeheer.app.Controllers;

public class Regex
{
    public enum Regexs{
        Name("^[A-Z][-a-zA-Z]+$"),
        AccountNr("[a-zA-Z]{2}[0-9]{2}[a-zA-Z0-9]{4}[0-9]{7}([a-zA-Z0-9]?){0,16}"),
        FourDigitNr("\\d{4}"),
        OneDigitNrNotNul("^[1-9]"),
        OneDigitNr("^[0-9]");

        private final String regex;

        private Regexs(final String regex) {
            this.regex = regex;
        }

        @Override
        public String toString() {
            return regex;
        }
    }
}
