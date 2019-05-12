import java.util.*;

public class LargestNumber {
    private static String largestNumber(String[] a) {
        //write your code here
        String result = "";
        ArrayList<String> listNumber = new ArrayList<String>(Arrays.asList(a));
        while(!listNumber.isEmpty()){
            String max = listNumber.get(0).toString();
            int maxIdx = 0;
            for(int i = 0; i < listNumber.size(); i++) {
                String a1 = listNumber.get(i).toString() + max;
                String b1 = max + listNumber.get(i).toString();
                if(isGreaterOrEqual(a1,b1)) {
                    max = listNumber.get(i).toString();
                    maxIdx = i;
                }
            }
            result+= max;
            listNumber.remove(maxIdx);
        }
        return result;
    }

    private static boolean isGreaterOrEqual(String num1, String num2) {
        // int iNum1 = Integer.parseInt(Character.toString(num1.charAt(0)));
        // int iNum2 = Integer.parseInt(Character.toString(num2.charAt(0)));
        // if(iNum1 >= iNum2) {
        //     return true;
        // }
        // return false;

        int sizeNum1 = num1.length();
        int sizeNum2 = num2.length();

        if(sizeNum1 > sizeNum2){
            //use sizeNum2;
            long iNum1 = Long.parseLong(num1.substring(0,sizeNum2));
            long iNum2 = Long.parseLong(num2);
            if(iNum1 > iNum2) {
                return true;
            } else if (iNum1 < iNum2) {
                return false;
            } else {
                for(int i = sizeNum2; i < sizeNum1; i++) {
                    int nextNumofNum1 = Integer.parseInt(Character.toString(num1.charAt(i)));
                    int firstNumofNum1 = Integer.parseInt(Character.toString(num1.charAt(i%sizeNum2)));
                    if(nextNumofNum1 == firstNumofNum1) {
                        continue;
                    } else if (nextNumofNum1 > firstNumofNum1) {
                        return true;
                    } else {
                        return false;
                    }
                }
                return true;
            }
        } else if ( sizeNum1 < sizeNum2) {
            //use sizeNum1
            long iNum1 = Long.parseLong(num1);
            long iNum2 = Long.parseLong(num2.substring(0,sizeNum1));
            if (iNum1 > iNum2) {
                return true;
            } else if (iNum1 < iNum2) {
                return false;
            } else {
                for(int i = sizeNum1; i < sizeNum2; i++) {
                    int nextNumofNum2 = Integer.parseInt(Character.toString(num2.charAt(i)));
                    int firstNumofNum2 = Integer.parseInt(Character.toString(num2.charAt(i%sizeNum1)));
                    if(nextNumofNum2 == firstNumofNum2) {
                        continue;
                    } else if (nextNumofNum2 > firstNumofNum2) {
                        return false;
                    } else {
                        return true;
                    }
                }
                return false;
            }
        } else {
            long iNum1 = Long.parseLong(num1);
            long iNum2 = Long.parseLong(num2);
            if(iNum1 >= iNum2) {
                return true;
            } else {
                return false;
            }
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        String[] a = new String[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.next();
        }
        System.out.println(largestNumber(a));
    }
}

