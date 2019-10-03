package pl.wojtasik.myBudget.Mappers;

import pl.wojtasik.myBudget.database.TableCoreDefinition;
import pl.wojtasik.myBudget.exception.DateParsingException;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

public class DateMapper {

    public static Optional<Date> dateFiller(String date) throws DateParsingException {

        if (date.length() <= 5) {
            if (date.length() <= 1) {
                date = "0" + date;
            }
            if (date.length() <= 2) {
                date = String.format("%02d", Calendar.getInstance().get(Calendar.MONTH) + 1) + "-" + date;
            }
            date = (Calendar.getInstance().get(Calendar.YEAR)) + "-" + date;
            return dateParser(date);
        } else {
            return Optional.empty();
        }

    }

    private static Optional<Date> dateParser(String date) throws DateParsingException {
        SimpleDateFormat formatter = new SimpleDateFormat(TableCoreDefinition.DATE_FORMAT);
        try {
            Date parsedDate = formatter.parse(date);
            return Optional.of(parsedDate);
        } catch (ParseException e) {
            return Optional.empty();
        }
    }

    public static String getStringFromDate(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat(TableCoreDefinition.DATE_FORMAT);
        return formatter.format(date);
    }
    public static Date getDateFromString(String dateString)  {
        try {
            Optional<Date> parsedDate = dateParser(dateString);
            return parsedDate.orElseThrow(() -> new DateParsingException("Unable to get date"));
        } catch (DateParsingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
