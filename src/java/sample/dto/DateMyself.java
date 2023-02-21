/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sample.dto;

/**
 *
 * @author kietl
 */
public class DateMyself {
    private int year;
    private int month;
    private int day;

    public DateMyself(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    

    public DateMyself() {
        
    }
    
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
    
    public void setDate(String date) {
        String[] dateSplit = date.split("-");
        if (dateSplit.length != 0) {
            this.year = Integer.parseInt(dateSplit[0]);
            this.month = Integer.parseInt(dateSplit[1]);
            this.day = Integer.parseInt(dateSplit[2]);
        }
    }
    
    public int checks(DateMyself date) {
        if (this.year < date.year) {
            return -1;
        } else if (this.year == date.year){
            if (this.month < date.month) {
                return -1;
            } else if (this.month == date.month) {
                if (this.day < date.day) {
                    return -1;
                } else if (this.day == date.day) {
                    return 0;
                } else {
                    return 1;
                }
            } else {
                return 1;
            }
        } else {
            return 1;
        }
    }

    @Override
    public String toString() {
        return this.year + "-" + this.month + "-" + this.day;
    }
    
    
}
