import java.math.BigDecimal;
import java.util.*;

public class Calculate{
public static void main(String[] args) {
        Calculate calculate = new Calculate();
        Double aDouble = calculate.stringCalculation("2+(20.24-30)*2");
        System.out.println(aDouble);
    }

    //×Ö·û´®¼ÆËã
    public Double stringCalculation(String line) {
        Stack<Double> numStack = new Stack<>();
        Stack<Character> opeStack = new Stack<>();
        Map<Character, Integer> priority = new HashMap<>();
        priority.put('%', 10);
        priority.put('*', 10);
        priority.put('/', 10);
        priority.put('+', 5);
        priority.put('-', 5);
        priority.put('(', 2);
        priority.put(')', 2);
        priority.put('#', 0);
        line = line + '#';
        char[] chars = line.toCharArray();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] <= '9' && chars[i] >= '0' || chars[i] == '.') {
                stringBuffer.append(chars[i]);
            } else {
                if (stringBuffer.length() != 0) {
                    String s = stringBuffer.toString();
                    double z = Double.parseDouble(s);
                    numStack.push(z);
                    stringBuffer.setLength(0);
                }
                if (chars[i] == '(') {
                    opeStack.push(chars[i]);
                    continue;
                }
                if (opeStack.size() == 0) {
                    opeStack.push(chars[i]);
                } else {
                    char top = opeStack.pop();
                    if (top == '(' && chars[i] == ')') {
                        continue;
                    }
                    if (priority.get(top) < priority.get(chars[i])) {
                        opeStack.push(top);
                        opeStack.push(chars[i]);
                    } else {
                        Double x, y, ans;
                        y = numStack.pop();
                        x = numStack.pop();
                        ans = operate(x, y, top);
                        numStack.push(ans);
                        i--;
                    }
                }
            }
        }
        return numStack.pop();
    }

    public Double operate(Double x, Double y, char top) {
        Double ans = 0.0;
        switch (top) {
            case '%':
                ans = x % y;
                break;
            case '*':
                ans = x * y;
                break;
            case '/':
                ans = x / y;
                break;
            case '+':
                ans = x + y;
                break;
            case '-':
                ans = x - y;
                break;
        }
        return ans;
    }
}