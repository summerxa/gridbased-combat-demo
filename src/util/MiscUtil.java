package util;

/**
 * Dumping all the miscellaneous utility methods here that don't fit anywhere else.
 */
public class MiscUtil {
    /**
     * Rounds num away from zero.
     */
    public static int awayFromZero(double num) {
        if (num < 0) {
            return (int)Math.floor(num);
        }
        return (int)Math.ceil(num);
    }
}
