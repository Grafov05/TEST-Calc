import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.print("Input: ");
        System.out.println(calc(in.nextLine()));
        in.close();
    }
    public static String calc(String input){
        String[] operators = input.split(" "); //режим по пробелам
        // String operators[] = input.split("[+-\\/\\*]+"); // режем по знакам
        //String operands[] = input.split("[+-\\/\\*]+");
        if (isCorrectForm(operators)) {
            if (isNumeric(operators[0]) && isNumeric(operators[2]))
                return calcArabic(operators);
            else if (!(isNumeric(operators[0])) && !(isNumeric(operators[2])))
                return calcRoman(operators);
            else throw new RuntimeException("Оба числа должны быть целыми и в одной системе счисления.");
        }else throw new RuntimeException("Неправильный формат данных.");
    }

    private static boolean isCorrectForm(String[] form){  //проверяем количество переменных и соответсвие знака
       // String sings = "+-*/";      // допустимые арифметические знаки
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
    /*
    *public enum RomanTen {
    *   X, XX, XXX, XL, L, LX, LXX, LXXX, XC, C
    *
    }*/

    private static String getArabicToRoman(int n){
        String roman;
        String[] romansOne = {"","I","II","III","IV","V","VI","VII","VIII","IX","X"};   //едицицы
        String[] romansTen = {"X","XX","XXX","XL","L","LX","LXX","LXXX","XC"};          //десятки
        if (n<=0) roman = "В римских числах нет 0 и отрицательных значений";
        else if (n> 0   && n<10)  roman = romansOne[(n)];
        else if (n>= 10  && n<100) roman = romansTen[(n/10) - 1 ] + romansOne[(n%10)];
        else if (n == 100) roman = "C"; //сто
        else  roman = "Превышено значение римских чисел.";
        return roman;
    }
    private static String calcArabic(String[] operators){
        int ar1,ar2;
        char sign;
        try {
            ar1 = Integer.parseInt(operators[0]);
            sign = operators[1].charAt(0);
            ar2 = Integer.parseInt(operators[2]);

            return "Output: " + getResultCalc(ar1,sign,ar2);
        } catch (NumberFormatException e) {
            throw new RuntimeException("Неверно введены числа.");
        }
    }
    private static String calcRoman(String[] operators){
        int rom1,rom2;
        char sign;
        try {
            rom1 = RomanOne.valueOf(RomanOne.class, operators[0]).ordinal() + 1;
            sign = operators[1].charAt(0);
            rom2 = RomanOne.valueOf(RomanOne.class, operators[2]).ordinal() + 1;

            if(getResultCalc(rom1,sign,rom2) < 1) throw new RuntimeException("В римской системе нет отрицательных чисел.");
                else return "Output: " + getArabicToRoman(getResultCalc(rom1,sign,rom2));

        } catch (IllegalArgumentException e){
            throw new RuntimeException("Неверно введены числа.");
        }
    }

    private static int getResultCalc(int first, char sign, int second){  //проводим вычисление

        int result = 0;
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
        return result;
    }

}


