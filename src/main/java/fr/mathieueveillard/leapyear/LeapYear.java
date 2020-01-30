package fr.mathieueveillard.leapyear;

public class LeapYear {
    public static boolean isMultipleOf(int modulo, int year) {
        return year % modulo == 0;
    }

    public static boolean isLeapYear(int year) {
        if(isMultipleOf(400, year)) {
            return true;
        }
        if(isMultipleOf(100, year)) {
            return false;
        }
        if(isMultipleOf(4, year)) {
            return true;
        }
        return  false;
    }
}
