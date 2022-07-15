package com.arturreske;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            System.out.println(calc(input));
        }
    }

    public static String calc(String input) throws Exception {
        try {
            String[] numbers = getArrayNumbers(input);
            String result = "";
            if (input.matches("^(10|[1-9])\\s[\\-\\+\\*\\/]\\s(10|[1-9])$")) {
                int num1 = Integer.parseInt(numbers[0]);
                int num2 = Integer.parseInt(numbers[2]);
                char operator = numbers[1].charAt(0);
                result = arabicCalc(num1, num2, operator);
            } else if (input.matches("^(I{1,3}|IV|VI{0,3}|IX|X)\\s[\\-\\+\\*\\/]\\s(I{1,3}|IV|VI{0,3}|IX|X)$")) {
                int num1 = convertToArabicNumber(numbers[0]);
                int num2 = convertToArabicNumber(numbers[2]);
                char operator = numbers[1].charAt(0);
                int arabicResult = Integer.parseInt(arabicCalc(num1, num2, operator));
                if (arabicResult <= 0) {
                    throw new Exception();
                }
                result = convertToRomanNumber(arabicResult);
            }
            else {
                throw new Exception();
            }
            return result;
        } catch (Exception e) {
            throw new Exception();
        }
    }

    public static String[] getArrayNumbers(String input) throws Exception {
        String[] numbers = input.split(" ");
        if (numbers.length == 3) {
            return numbers;
        } else {
            throw new Exception();
        }
    }

    static String convertToRomanNumber(int number) {
        String[] romanArray = new String[]{"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int[] arabicArray = new int[]{100, 90, 50, 40, 10, 9, 5, 4, 1};
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < arabicArray.length; i++) {
            while ((number - arabicArray[i]) >= 0) {
                number -= arabicArray[i];
                result.append(romanArray[i]);
            }
        }
        return result.toString();
    }

    static int convertToArabicNumber(String number) {
        String[] romanNumbers = new String[]{"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        int result = 0;
        for (int i = 0; i < romanNumbers.length; i++) {
            if (number.equals(romanNumbers[i])) {
                result = i + 1;
            }
        }
        return result;
    }

    static String arabicCalc(int num1, int num2, char operator) {
        int result = 0;
        switch (operator) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                result = num1 / num2;
                break;
        }
        return String.valueOf(result);
    }
}