import java.util.Scanner;

public class Main {
	static int t;
	static int n, k;
	static int [] v;
	static int [] c;
	static int [] dp;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		k = sc.nextInt();
		dp = new int[k+1];
		v = new int[n];
		c = new int[n];
		for (int i=0;i<n;i++) {
			v[i] = sc.nextInt();
			c[i] = sc.nextInt();
		}
		for (int i=0;i<n;i++) {
			for (int j=k;j>=v[i];j--) {
				dp[j] = Math.max(dp[j], dp[j-v[i]]+c[i]);
			}
		}
		System.out.println(dp[k]);
	}
}