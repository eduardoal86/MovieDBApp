package edualves.com.moviedbapp.utils;

/**
 * Created by edualves on 23/06/17.
 */

public class Utils {

    public static String countCharsForSpace(String msg, int wordsSpace) {

        StringBuilder stringBuilder = new StringBuilder(msg);

        int i = 0;
        while ((i = stringBuilder.indexOf(" ", i + wordsSpace)) != -1) {

            stringBuilder.replace(i, i + 1, "\n");
        }

        return stringBuilder.toString();
    }
}
