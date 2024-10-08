package uebung1;
import static org.junit.jupiter.api.Assertions.*;
import org.hbrs.se1.ws24.exercises.uebung1.control.GermanTranslator;
import org.junit.jupiter.api.Test;

public class GermanTranslatorTest {

        @Test
        void aPositiveTest1() {
            GermanTranslator translator = new GermanTranslator();
            String value = translator.translateNumber(1);
            assertEquals(value, "eins");
        }

        @Test
        void moreThanTen() {
            GermanTranslator translator = new GermanTranslator();
            int number = 12; // x>10
            String s = "Übersetzung der Zahl " + number + " nicht möglich (" + translator.version + ")";
            assertEquals(s, translator.translateNumber(number));

        }
        @Test
        void aNegativeTest() {
            GermanTranslator translator = new GermanTranslator();
            int number = -5; // x<0
            String s = "Übersetzung der Zahl " + number + " nicht möglich (" + translator.version + ")";
            assertEquals(s, translator.translateNumber(number));
            number = -555; // x<0
            s = "Übersetzung der Zahl " + number + " nicht möglich (" + translator.version + ")";
            assertEquals(s, translator.translateNumber(number));

        }

    }