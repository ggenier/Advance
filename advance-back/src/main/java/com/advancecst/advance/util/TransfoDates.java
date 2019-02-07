package com.advancecst.advance.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class TransfoDates {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");

    public static LocalDate string2LocalDate(String date) {
        if (date != null && date.length() > 0) {
            return LocalDate.parse(date, formatter);
        } else {
            return null;
        }
    }

    public static Long delaiEntreDeuxDates(LocalDate dateDebut, LocalDate dateFin) {
        if (dateDebut != null && dateFin != null) {
            return ChronoUnit.DAYS.between(dateDebut, dateFin);
        } else {
            return null;
        }
    }

}