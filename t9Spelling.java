import java.io.*;
import java.util.*;
import java.lang.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        //create a hashmap
        HashMap<Character, String> keypad = new HashMap<Character, String>();
        keypad.put('a', "2");
        keypad.put('b', "22");
        keypad.put('c', "222");
        keypad.put('d', "3");
        keypad.put('e', "33");
        keypad.put('f', "333");
        keypad.put('g', "4");
        keypad.put('h', "44");
        keypad.put('i', "444");
        keypad.put('j', "5");
        keypad.put('k', "55");
        keypad.put('l', "555");
        keypad.put('m', "6");
        keypad.put('n', "66");
        keypad.put('o', "666");
        keypad.put('p', "7");
        keypad.put('q', "77");
        keypad.put('r', "777");
        keypad.put('s', "7777");
        keypad.put('t', "8");
        keypad.put('u', "88");
        keypad.put('v', "888");
        keypad.put('w', "9");
        keypad.put('x', "99");
        keypad.put('y', "999");
        keypad.put('z', "9999");
        keypad.put(' ', "0");
        //br is a bufferedReader class has access to readLine() function
        //first input  will be number of test cases
        int testCases = Integer.parseInt(br.readLine());
        //create the first for loop to loop through the testcases
        for (int i = 0; i < testCases; i++) {
            String test = Integer.toString(i + 1);
            pw.append("Case #");
            pw.append(test);
            pw.append(": ");
            //StringBuilder answer = new StringBuilder("Case #" + test + ": ");
            //create temp to store last output
            char temp = '$';
            String currentWord = br.readLine();
            for (int j = 0; j < currentWord.length(); j++) {
                String output = keypad.get(currentWord.charAt(j));
                if (output.charAt(0) == temp) {
                    //add a space to Stringbuilder
                    pw.append(" ");
                }
                //set temp to output
                pw.append(output);
                temp = output.charAt(0);
            }
            pw.append("\n");
        }
        pw.close();
    }
}


