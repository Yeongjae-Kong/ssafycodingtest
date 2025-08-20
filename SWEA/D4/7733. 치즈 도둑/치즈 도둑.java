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
import java.util.ArrayDeque;
import java.util.Queue;
/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution
{
	static int t;
	static int n;
	static int [][] arr;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		t = sc.nextInt();
		for (int T=0;T<t;T++) {
			n = sc.nextInt();
			arr = new int[n][n];
			int ans = 0;
			for (int i=0;i<n;i++) {
				for (int j=0;j<n;j++) {
					arr[i][j] = sc.nextInt();
				}
			}
			for (int day=0;day<101;day++) {
				for (int i=0;i<n;i++) {
					for (int j=0;j<n;j++) {
						if (arr[i][j]==day) {
							arr[i][j] = 0;
						}
					}
				}
				int temp_ans = 0;
				boolean [][] visited = new boolean[n][n];
				for (int i=0;i<n;i++) {
					for (int j=0;j<n;j++) {
						if (!visited[i][j] && arr[i][j] != 0) {
							bfs(i, j, visited);
							temp_ans+=1;
						}
					}
				}
				if (ans < temp_ans) {
					ans = temp_ans;
				}
			}
			System.out.println("#"+(T+1)+" "+ans);
		}
	}
		
	private static void bfs(int y, int x, boolean[][] visited) {
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {y, x});
		
		while (!queue.isEmpty()) {
			int[] current = queue.poll();
			int cur_y = current[0];
			int cur_x = current[1];
			for (int i=0; i<4; i++) {
				int nx = cur_x+dx[i];
				int ny = cur_y+dy[i];
				if (ny >= 0 && ny < n && nx >= 0 && nx < n && !visited[ny][nx] && arr[ny][nx] != 0) {
					visited[ny][nx] = true;
					queue.offer(new int[] {ny, nx});
				}
			}
		}
	}
}