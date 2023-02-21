/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sample.dao;

import java.time.LocalDate;

/**
 *
 * @author kietl
 */
public class DateDAO {
    public static String getDate() {
        LocalDate date = LocalDate.now();
        String day = String.valueOf(date.getDayOfMonth());
        String month = String.valueOf(date.getMonthValue());
        String year = String.valueOf(date.getYear());
        return year + "-" + month + "-" + day;
    }
}
