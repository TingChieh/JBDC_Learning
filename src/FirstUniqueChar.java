import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FirstUniqueChar {
    public static char findFirstUniqueChar(String s) {
        Map<Character, Integer> charCountMap = new HashMap<>();

        // 统计字符出现的次数
        for (char c : s.toCharArray()) {
            charCountMap.put(c, charCountMap.getOrDefault(c, 0) + 1);
        }

        // 找到第一个出现次数为1的字符
        for (char c : s.toCharArray()) {
            if (charCountMap.get(c) == 1) {
                return c;
            }
        }

        return ' ';
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String in = input.next();
        char result = findFirstUniqueChar(in);
        if (result != ' ') {
            System.out.println("第一个只出现一次的字符是: " + result);
        } else {
            System.out.println("没有只出现一次的字符");
        }
        input.close();
    }
}
