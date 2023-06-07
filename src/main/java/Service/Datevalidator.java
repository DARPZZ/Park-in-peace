package Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Datevalidator
{
        private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        public static boolean isValidDate(String date) {
            if (date == null || date.isEmpty()) {
                return false;
            }

            try {
                LocalDate.parse(date, formatter);
                return true;
            } catch (DateTimeParseException e) {
                return false;
            }
        }
}
