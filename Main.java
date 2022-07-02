import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.print("Input: ");
        System.out.print("Output: " + calc(in.nextLine()));
        in.close();
    }
    public static String calc(String input){
        String[] operators = input.split(" "); //режим по пробелам
        int first, second, result = 0;
        boolean itRom;
        char sign;

        if (isCorrectForm(operators)) {
            if (isNumeric(operators[0]) && isNumeric(operators[2])){
                first   = Integer.parseInt(operators[0]);
                second  = Integer.parseInt(operators[2]);
                itRom = false;
                }
            else if (!(isNumeric(operators[0])) && !(isNumeric(operators[2]))){
                first   = getRomanToArabic(operators[0]);
                second  = getRomanToArabic(operators[2]);
                itRom = true;
                }
            else throw new RuntimeException("Оба числа должны быть целыми и в одной системе счисления.");
        }else throw new RuntimeException("Неправильный формат данных.");


        sign = operators[1].charAt(0);
        if ((first >0 && first <=10) && (second >0 && second <=10)) {
            switch (sign) {
                case '+':
                    result = first + second;
                    break;
                case '-':
                    result = first - second;
                    break;
                case '*':
                    result = first * second;
                    break;
                case '/':
                    result = first / second;
                    break;
            }
        }else throw new RuntimeException("Неправильный диапазонн входных данных.");
        if (itRom)  return getArabicToRoman(result);
            else return String.valueOf(result);
    }
    private static boolean isCorrectForm(String[] form){  //проверяем количество переменных и соответсвие знака
        if ((form.length == 3 && form[0] !="") && (form[1].length()== 1 && "+-*/".contains(form[1])))
            return true;
        else return false;
    }

    private static boolean isNumeric(String numeric) {
        int intValue;
        try {
            intValue = Integer.parseInt(numeric);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    private enum RomanOne {
        I, II, III, IV, V, VI, VII, VIII, IX, X
    }
    private static String getArabicToRoman(int n){
        String roman;
        String[] romansOne = {"","I","II","III","IV","V","VI","VII","VIII","IX","X"};   //едицицы
        String[] romansTen = {"X","XX","XXX","XL","L","LX","LXX","LXXX","XC"};          //десятки
        if (n<=0) throw new RuntimeException("В римских числах нет 0 и отрицательных значений");
        else if (n> 0   && n<10)  roman = romansOne[(n)];
        else if (n>= 10  && n<100) roman = romansTen[(n/10) - 1 ] + romansOne[(n%10)];
        else if (n == 100) roman = "C"; //сто
        else  throw new RuntimeException("Превышено значение римских чисел.");
        return roman;
    }
    private static int getRomanToArabic(String operator){
        int rom;
        try {
            rom = RomanOne.valueOf(operator).ordinal() + 1;
            if(rom < 1) throw new RuntimeException("В римской системе нет отрицательных чисел.");
            else return rom;
        } catch (IllegalArgumentException e){
            throw new RuntimeException("Неверно введены числа.");
        }
    }
}


