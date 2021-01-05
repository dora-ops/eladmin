import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String str = input.nextLine();
        String[] split = str.split(",");
        int rm = input.nextInt();
        int r = 0;
        String result = "";
        for (int i = 0; i < split.length; i++) {
            String[] q = split[i].split("-");
            boolean flag = true;
            if (q.length == 2) {
                int min = Integer.parseInt(q[0]), max = Integer.parseInt(q[1]);
                int w = rm == max ? max - 1 : max;

                if (w > r) {
                    r = w;
                } else {
                    flag = false;
                }
                String k = null;
                if (rm >= min && rm <= max) {
                    int a = rm - min, b = max - rm;
                    if (a >= 2 && b >= 2) {
                        k = min + "-" + (rm - 1);
                        result = insert(result, flag, k);
                        k = (rm + 1) + "-" + max;
                        result = insert(result, flag, k);
                    } else if (a >= 2 && b == 1) {
                        k = min + "-" + (rm - 1);
                        result = insert(result, flag, k);
                        k = "" + max;
                        result = insert(result, flag, k);
                    } else if (a == 1 && b >= 2) {
                        k = "" + min;
                        result = insert(result, flag, k);
                        k = (rm + 1) + "-" + max;
                        result = insert(result, flag, k);
                    } else if (a == 1 && b == 1) {
                        k = "" + min;
                        result = insert(result, flag, k);
                        k = "" + max;
                        result = insert(result, flag, k);
                    } else if (a == 0) {
                        k = (min + 1) + "-" + max;
                        result = insert(result, flag, k);
                    } else if (b == 0) {
                        k = min + "-" + (max - 1);
                        result = insert(result, flag, k);
                    }
                } else {
                    result = insert(result, flag, split[i]);
                }
            } else if (Integer.parseInt(split[i]) != rm) {
                int w = Integer.parseInt(split[i]);
                if (w > r) {
                    r = w;
                    result = insert(result, true, split[i]);
                } else {
                    result = insert(result, false, split[i]);
                }
            }
        }
        String[] s = result.split(",");
        for (int i = 0; i < s.length; i++) {

        }
        System.out.println(result);
    }



    private static String insert(String result, boolean flag, String k) {
        if (result.length() == 0) {
            result = k;
        } else {
            if (flag) {
                result += "," + k;
            } else {
                result = k + "," + result;
            }
        }
        return result;
    }


}
