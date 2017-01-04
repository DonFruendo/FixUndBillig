package fixundbillig.sendungsverwaltung.utils;

import java.util.Date;

/**
 * @author Tobias Freund
 * @since 03.01.2017
 */
public class Logger {


    public static void log(Object o) {
        printToConsole("LOG  ", o);
    }

    public static void err(Object o) {
        printToConsole("ERROR", o);
    }

    public static void printToConsole(String level, Object o) {
        Date d = new Date();
        System.out.println("[" + d + "] ["+ level +"] " + o);
    }
}
