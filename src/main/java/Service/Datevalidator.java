package Service;

import javafx.util.converter.LocalDateStringConverter;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Datevalidator
{
        private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        public static boolean isValidDate(String date)
        {
            Calendar calendar = new GregorianCalendar(2024, Calendar.FEBRUARY, 1);
            //System.out.println(calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
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


