import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите выражение: ");
        String input = scanner.nextLine();
        String[] parts = input.split(" ");
        String operation = parts[1].trim();
        boolean isRoman = false;

        int a = parseNumber(parts[0]);
        int b = parseNumber(parts[2]);
        if (a == -1 || b == -1) {
            System.err.println("Некорректное число.");
            return;
        }
        

        if (isRoman(parts[0]) && isRoman(parts[2])) {
            isRoman = true;
        } else if ((!isRoman(parts[0]) && isRoman(parts[2])) || (isRoman(parts[0]) && !isRoman(parts[2]))) {
            System.err.println("Нельзя производить вычисления с разными форматами чисел.");
            return;
        }

        int result;
        if (operation.equals("+")) {
            result = a + b;
        } else if (operation.equals("-")) {
            result = a - b;
        } else if (operation.equals("*")) {
            result = a * b;
        } else if (operation.equals("/")) {
            result = a / b;
        } else {
            System.err.println("Неверная операция.");
            return;
        }

        if (isRoman) {
            System.out.println(toRoman(result));
        } else {
            System.out.println(result);
        }
    }

    private static boolean isRoman(String s) {
        s = s.toUpperCase();
        return s.equals("I") || s.equals("II") || s.equals("III") || s.equals("IV") || s.equals("V") ||
                s.equals("VI") || s.equals("VII") || s.equals("VIII") || s.equals("IX") || s.equals("X");
    }

    private static int parseNumber(String s) {
        s = s.toUpperCase();
        if (isRoman(s)) {
            int result = 0;
            int prev = 0;
            for (int i = 0; i < s.length(); i++) {
                int current = toArabic(s.charAt(i));
                if (prev < current) {
                    result -= prev;
                } else {
                    result += prev;
                }
                prev = current;
            }
            result += prev;
            return result;
        } else {
            try {
                int n = Integer.parseInt(s);
                if (n >= 1 && n <= 10) {
                    return n;
                } else {
                    return -1;
                }
            } catch (NumberFormatException e) {
                return -1;
            }
        }
    }

    private static int toArabic(char roman) {
        switch (roman) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            default: return 0;
        }
    }

    private static String toRoman(int n) {
        String[] romanNumerals = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        if (n <= 0 || n > 10) {
            return "Некорректный результат.";
        }
        return romanNumerals[n-1];
    }
}