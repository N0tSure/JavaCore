package com.artemsirosh.tij.strings;

import java.net.InetAddress;
import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.MatchResult;

/**
 * Created by cresh on 29.07.16.
 */
public class ThreatAnalyzer {
    private static String threatData =
            "58.27.82.161@02/10/2005\n" +
                    "204.45.234.40@02/11/2005\n" +
                    "58.27.82.161@02/11/2005\n" +
                    "58.27.82.161@02/12/2005\n" +
                    "58.27.82.161@02/12/2005\n" +
                    "[Next log section with different data format]";

    public static void main(String[] args) throws ParseException {
        Scanner scanner = new Scanner(threatData);
        String patten = "(\\d+[.]\\d+[.]\\d+[.]\\d+)@(\\d{2}/\\d{2}/\\d{4})";
        while (scanner.hasNext(patten)) {
            scanner.next(patten);
            MatchResult result = scanner.match();
            URI address = URI.create(result.group(1));
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date date = format.parse(result.group(2));
            System.out.printf("Thread on %1$tm/%1$td/%1$ty from %2$s\n",date,address.toString());
        }
    }

}
