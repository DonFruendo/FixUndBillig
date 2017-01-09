package fixundbillig.sendungsverwaltung.data.utils;

import java.util.Date;

/**
 * @author Tobias Freund
 * @since 03.01.2017
 */
public class Logger {

    public static void debug(Object o) {
        printToConsole("DEBUG", o);
    }

    public static void info(Object o) {
        printToConsole("INFO ", o);
    }

    public static void err(Object o) {
        printToConsole("ERROR", o);
    }

    private static void printToConsole(String level, Object o) {
        Date d = new Date();
        System.out.println("[" + d + "] ["+ level +"] " + o);
    }
}
