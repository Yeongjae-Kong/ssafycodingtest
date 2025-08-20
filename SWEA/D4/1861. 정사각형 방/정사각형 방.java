/////////////////////////////////////////////////////////////////////////////////////////////
// 기본 제공코드는 임의 수정해도 관계 없습니다. 단, 입출력 포맷 주의
// 아래 표준 입출력 예제 필요시 참고하세요.
// 표준 입력 예제
// int a;
// double b;
// char g;
// String var;
// long AB;
// a = sc.nextInt();                           // int 변수 1개 입력받는 예제
// b = sc.nextDouble();                        // double 변수 1개 입력받는 예제
// g = sc.nextByte();                          // char 변수 1개 입력받는 예제
// var = sc.next();                            // 문자열 1개 입력받는 예제
// AB = sc.nextLong();                         // long 변수 1개 입력받는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
// 표준 출력 예제
// int a = 0;                            
// double b = 1.0;               
// char g = 'b';
// String var = "ABCDEFG";
// long AB = 12345678901234567L;
//System.out.println(a);                       // int 변수 1개 출력하는 예제
//System.out.println(b); 		       						 // double 변수 1개 출력하는 예제
//System.out.println(g);		       						 // char 변수 1개 출력하는 예제
//System.out.println(var);		       				   // 문자열 1개 출력하는 예제
//System.out.println(AB);		       				     // long 변수 1개 출력하는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
import java.util.Scanner;
import java.io.FileInputStream;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution
{
	static int t;
	static int n;
	static int [][] arr;
	static int [] dx = {0, 1, 0, -1};
	static int [] dy = {1, 0, -1, 0};
	static int [][] visited;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		t = sc.nextInt();
		for (int T=0;T<t;T++) {
			n = sc.nextInt();
			arr = new int[n][n];
			visited = new int[n][n];
			for (int i=0;i<n;i++) {
				for (int j=0;j<n;j++) {
					arr[i][j] = sc.nextInt();
				}
			}
			int max_len = 0;
			int min_start_num = Integer.MAX_VALUE;

			for (int i=0;i<n;i++) {
				for (int j=0;j<n;j++) {
					int current_len = dfs(i, j);

					if (current_len > max_len) {
						max_len = current_len;
						min_start_num = arr[i][j];
					} else if (current_len == max_len) {
						if (arr[i][j] < min_start_num) {
							min_start_num = arr[i][j];
						}
					}
				}
			}
			System.out.println("#" + (T+1) + " " + min_start_num + " " + max_len);
		}
	}
	static int dfs(int y, int x) {
		if (visited[y][x] != 0) {
			return visited[y][x];
		}
		visited[y][x] = 1;
		for (int d = 0; d < 4; d++) {
			int ny = y + dy[d];
			int nx = x + dx[d];
			if (ny >= 0 && ny < n && nx >= 0 && nx < n && arr[ny][nx] == arr[y][x] + 1) {
				visited[y][x] = Math.max(visited[y][x], 1 + dfs(ny, nx)); 
			}
		}
		return visited[y][x];
	}
}