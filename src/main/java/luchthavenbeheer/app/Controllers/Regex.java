package luchthavenbeheer.app.Controllers;

/**
 * Created by Jente on 20/11/2016.
 */
public class Regex
{
    public enum Regexs{
        Name("\"/^[a-z ,.'-]+$/i\""),
        AccountNr("[a-zA-Z]{2}[0-9]{2}[a-zA-Z0-9]{4}[0-9]{7}([a-zA-Z0-9]?){0,16}"),
        FourDigitNr("\\d{4}");

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