import java.util.Scanner;

public class Change {
    private static int getChange(int m) {
	int[] coins = new int[3];
	coins[0] = 10;
	coins[1] = 5;
	coins[2] = 1;
	int currCoin = 0;
	int count = 0;
	while(m!=0){
		count += m/coins[currCoin];
		m = m%coins[currCoin];
		currCoin++;
	}
        return count;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        System.out.println(getChange(m));
    }
}

