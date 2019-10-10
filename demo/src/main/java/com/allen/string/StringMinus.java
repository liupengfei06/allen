/**
 *
 */
package com.allen.string;

import java.util.regex.Pattern;

import static java.util.regex.Pattern.*;

/**
 * @author clydelou
 * 减法运算
 *
 */
public class StringMinus {


    public static boolean f(String s, String t) {
        if (s.length() < t.length()) {
            return false;
        } else if (s.length() == t.length()) {
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) != t.charAt(i)) {
                    if ((s.charAt(i) - t.charAt(i)) > 0) {
                        break;
                    } else if ((s.charAt(i) - t.charAt(i)) < 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static String minus(String s, String t) {
        Pattern pattern = compile("[0-9]+?");
        if (!pattern.matcher(s).matches() || !pattern.matcher(t).matches()) {
            System.out.println("Error");
            return "";
        } else if (s.equals(t)) {
            return "0";
        } else {
            StringBuilder sb = new StringBuilder();
            int length0 = s.length();
            int length1 = t.length();
            String sTemp = s;
            String tTemp = t;
            s = new StringBuilder(s).reverse().toString();
            t = new StringBuilder(t).reverse().toString();
            int min;
            if (length0 > length1 || (length0 == length1 && f(sTemp, tTemp))) {
                min = length1;
                int temp = 0;
                for (int i = 0; i < min; i++) {
                    char x = s.charAt(i);
                    char y = t.charAt(i);
                    int result = x - y + temp;
                    if (result < 0) {
                        result += 10;
                        temp = -1;
                    } else {
                        temp = 0;
                    }
                    int r1 = result % 10;
                    sb.append(r1);
                }
                while (temp != 0 && min < length0) {
                    int result = s.charAt(min) - '0' + temp;
                    if (result < 0) {
                        result += 10;
                        temp = -1;
                    } else {
                        temp = 0;
                    }
                    int r1 = result % 10;
                    sb.append(r1);
                    min++;
                }
                if (min < length0) {
                    String str = s.substring(min, length0);
                    sb.append(str);
                }
                String result = sb.reverse().toString();
                while (result.indexOf("0") == 0) {
                    result = result.substring(1, result.length());
                }
                return result;
            } else {
                sb.append(minus(new StringBuilder(t).reverse().toString(),
                        new StringBuilder(s).reverse().toString()));
                return "-" + sb.toString();
            }
        }
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String s = "123000000000000000000000000000000000";
        String t = "23";
        String m = "99999999999989999999999999999999999";
        String n = "99999999999999999999999999999999999";
        String p = "31";
        String q = "22";
        System.out.println(minus(s, t));
        System.out.println(minus(t, s));
        System.out.println(minus(m, n));
        System.out.println(minus(p, q));
        System.out.println(minus("a", "1"));
    }
}