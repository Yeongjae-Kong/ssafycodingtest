import java.util.Arrays;
import java.util.Scanner;

public class Main{
	static int [][] prefix_row;
	static int [][] prefix_col;
	static int N, M;
	static int [][] arr;
	static int [][] target;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		arr = new int[N][N];
		prefix_row = new int[N][N];
		target = new int[M][4];
		
		for (int i=0;i<N;i++) {
			for (int j=0;j<N;j++) {
				arr[i][j] = sc.nextInt();
			}
		}
		for (int i=0;i<M;i++) {
			for (int j=0;j<4;j++) {
				target[i][j] = sc.nextInt();
			}
		}

		
		for(int i=0;i<N;i++) {
			prefix_row[i][0] = arr[i][0];
			for (int j=1;j<N;j++) {
				prefix_row[i][j] = prefix_row[i][j-1]+arr[i][j];
			}
		}
		for (int i=0;i<M;i++) {
			int ans = 0;
			int y1 = target[i][0]-1;
			int x1 = target[i][1]-1;
			int y2 = target[i][2]-1;
			int x2 = target[i][3]-1;
			for (int j=y1;j<=y2;j++) {
				if (x1==x2) {
					ans += arr[j][x1];
				} else {
					if (x1 != 0) {
						ans += prefix_row[j][x2] - prefix_row[j][x1-1];
					} else {
						ans += prefix_row[j][x2];
					}
				}
			}
			System.out.println(ans);
		}
	}
}
