package liao.thomas.javadev.designpattern.principles;

/**
 * Created by liaodongming on 2017/1/4.
 *   Keep It Simple Stupid
 *
 *  Both versions may be seen as simpler than the other depending on the view taken
 *  It depends which method is better. Just an example.
 *
 *  http://principles-wiki.net/principles:keep_it_simple_stupid
 *
 *  avoid inheritance, polymorphism, dynamic binding and other complicated OOP
 *  avoid low-level optimization of algorithms(Assembler, bit-operations, pointers)
 *
 */
public class KISS {

    /**
     *  uses less language features,
     *   it's clear how it works by just seeing the method
     * @param dayOfWeek
     * @return
     */
    public String weekday1(int dayOfWeek) {
        switch (dayOfWeek) {
            case 1: return "Monday";
            case 2: return "Tuesday";
            case 3: return "Wednesday";
            case 4: return "Thursday";
            case 5: return "Friday";
            case 6: return "Saturday";
            case 7: return "Sunday";
            default: throw new IllegalArgumentException("dayOfWeek must be in range 1..7");
        }
    }

    /**
     *  less statements and less execution branches
     *  more details to think about when refer to weekday1()
     * @param dayOfWeek
     * @return
     */
    public String weekday2(int dayOfWeek) {
        if ((dayOfWeek < 1) || (dayOfWeek > 7))
            throw new IllegalArgumentException("dayOfWeek must be in range 1..7");

        final String[] weekdays = {
                "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

        return weekdays[dayOfWeek-1];
    }
}
