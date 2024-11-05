import java.util.Scanner;

public class Calculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        try {
            String result = calculate(input);
            System.out.println("Результат: " + result);
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());

        }
    }

    private static String calculate(String input) throws Exception {

        String[] parts = input.split(" ");
        String num1Str = parts[0];
        String operator = parts[1];
        String num2Str = parts[2];
        if (parts.length != 3) {
            throw new Exception("Неверный формат ввода. Используйте формат: операнд оператор операнд.");
        }


        boolean isRoman = isRoman(num1Str) && isRoman(num2Str);
        boolean isArabic = isArabic(num1Str) && isArabic(num2Str);

        if (isRoman && isArabic) {
            throw new Exception("Используйте только целые арабские или только римские цифры.");
        }

        int num1;
        if (isRoman) {
            num1 = romanToArabic(num1Str);
        } else {
            num1 = Integer.parseInt(num1Str);
        }

        int num2;
        if (isRoman) {
            num2 = romanToArabic(num2Str);
        } else {
            num2 = Integer.parseInt(num2Str);
        }

        if (num1 < 0 || num1 > 10 || num2 < 0 || num2 > 10) {
            throw new Exception("Числа должны быть от 1 до 10 включительно.");
        }

        int result = performOperation(num1, num2, operator);

        if (isRoman && result < 1) {
            throw new Exception("Результат римской операции не может быть меньше еденицы.");
        }

        return isRoman
                ? arabicToRoman(result)
                : String.valueOf(result);
    }

    private static boolean isArabic(String value) {
        try {
            int number = Integer.parseInt(value);
            return number >= 1 && number <= 10;
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean isRoman(String value) {
        try {
            romanToArabic(value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static int romanToArabic(String roman) throws Exception {
        switch (roman) {
            case "I": return 1;
            case "II": return 2;
            case "III": return 3;
            case "IV": return 4;
            case "V": return 5;
            case "VI": return 6;
            case "VII": return 7;
            case "VIII": return 8;
            case "IX": return 9;
            case "X": return 10;
            default: throw new Exception("Неверное римское число.");
        }
    }

    private static String arabicToRoman(int number) {
        String[] units = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        String[] tens = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC", "C"};
        return tens[number / 10] + units[number % 10];


    }

    private static int performOperation(int num1, int num2, String operator) throws Exception {
        switch (operator) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                try {
                    if (num2 == 0) {
                        throw new Exception("Деление на ноль невозможно.");
                    }
                } catch (Exception e) {
                    throw new RuntimeException("Деление на ноль невозможно.");
                }
                return num1 / num2;
            default:
                throw new Exception("Неверный оператор. Используйте +, -, * или /.");
        }
    }
}