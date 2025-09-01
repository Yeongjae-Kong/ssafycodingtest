import java.util.Scanner;

public class Main {
	static int n;
	static int [][] arr;
	static int [][] visited;
	public static void main(String[] args) {
		//--------------솔루션 코드를 작성하세요.---------------------------
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		arr = new int[n][n];
		for (int i=0;i<n;i++) {
			for (int j=0; j<n; j++) {
				arr[i][j] = sc.nextInt();
			}
		}
		int m=n*2;
		int ans0 = 0;
		int ans1 = 0;
		visited = new int[n][n];
		while (m>=2) {
			m = m/2;
			for (int i=0;i<n;i+=m) {
				for (int j=0;j<n;j+=m) {
					if (visited[i][j] == 1) continue;
					if (check(i,j,m)==1) {
						for (int k=i;k<i+m;k++) {
							for (int l=j;l<j+m;l++) {
								visited[k][l] = 1;
							}
						}
						ans0++;
					}
					if (check(i,j,m)==2) {
						for (int k=i;k<i+m;k++) {
							for (int l=j;l<j+m;l++) {
								visited[k][l] = 1;
							}
						}
						ans1++;
					}
				}
			}
		}
		System.out.println(ans0);
		System.out.println(ans1);
	}
	private static int check(int i, int j, int n) {
		int num = n*n;
		int check_0 = 0;
		int check_1 = 0;
		for (int k=i;k<i+n;k++) {
			for (int l=j;l<j+n;l++) {
				if (arr[k][l] == 0) check_0++;
				if (arr[k][l] == 1) check_1++;
			}
		}
		if (check_0 == num) return 1;
		if (check_1 == num) return 2;
		return 0;
		
	}

}
