import java.util.Scanner;

public class Calculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        try {
            String result = Calculator.calculation.evaluate(input);
            System.out.println("Результат: " + result);
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());

        }
    }

    public static class calculation {
        private static String evaluate(String input) throws Exception {


                String[] parts = input.split(" ");
                String firstString = parts[0];
                String operator = parts[1];
                String secondString = parts[2];


            numberValidation validation = new numberValidation();
            numberConvertion convertion = new numberConvertion();

            if (parts.length != 3) {
                throw new Exception("Неверный формат ввода. Используйте формат: операнд оператор операнд.");
            }

            boolean isRoman = validation.isRoman(firstString) && validation.isRoman(secondString);
            boolean isArabic = validation.isArabic(firstString) && validation.isArabic(secondString);

            if (isRoman && isArabic) {
                throw new Exception("Используйте только целые арабские или только римские цифры.");
            }

            int firstOperand;
            if (isRoman) {
                firstOperand = convertion.romanToArabic(firstString);
            } else {
                firstOperand = Integer.parseInt(firstString);
            }

            int secondOperand;
            if (isRoman) {
                secondOperand = convertion.romanToArabic(secondString);
            } else {
                secondOperand = Integer.parseInt(secondString);
            }

            if (firstOperand < 1 || firstOperand > 10 || secondOperand < 1 || secondOperand > 10) {
                throw new Exception("Числа должны быть от 1 до 10 включительно.");
            }

            int result = mathOperation.performOperation(firstOperand, secondOperand, operator);

            if (isRoman && result < 1) {
                throw new Exception("Результат римской операции не может быть меньше еденицы.");
            }

            return isRoman
                    ? convertion.arabicToRoman(result)
                    : String.valueOf(result);
        }
    }

    public static class numberValidation {
        private static boolean isArabic(String value) {
            try {
                int number = Integer.parseInt(value);
                return number >= 1 && number <= 10;
            } catch (Exception e) {
                return false;
            }
        }

        boolean isRoman(String value) {
            try {
                numberConvertion.romanToArabic(value);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
    }

    public static class numberConvertion {
        private static int romanToArabic(String roman) throws Exception {
            switch (roman) {
                case "I":
                    return 1;
                case "II":
                    return 2;
                case "III":
                    return 3;
                case "IV":
                    return 4;
                case "V":
                    return 5;
                case "VI":
                    return 6;
                case "VII":
                    return 7;
                case "VIII":
                    return 8;
                case "IX":
                    return 9;
                case "X":
                    return 10;
                default:
                    throw new Exception("Неверное римское число.");
            }
        }

        private static String arabicToRoman(int number) {
            String[] units = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
            String[] tens = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC", "C"};
            return tens[number / 10] + units[number % 10];


        }
    }

    public static class mathOperation {
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
}
