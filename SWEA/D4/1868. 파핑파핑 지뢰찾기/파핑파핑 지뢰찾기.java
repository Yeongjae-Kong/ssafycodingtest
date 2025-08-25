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
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution {
    static int N;
    static char[][] arr;
    static int[][] delta = {
        {-1, 0}, {1, 0}, {0, -1}, {0, 1},
        {-1, -1}, {-1, 1}, {1, -1}, {1, 1}
    };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            N = sc.nextInt();
            arr = new char[N][N];
            for (int i = 0; i < N; i++) {
                String line = sc.next();
                for (int j = 0; j < N; j++) {
                    arr[i][j] = line.charAt(j);
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (arr[i][j] == '.') {
                        change_bfs(i, j);
                    }
                }
            }

            int cnt = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (arr[i][j] == '0') {
                        cnt++;
                        count_check(i, j);
                    }
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (arr[i][j] != '*') {
                        cnt++;
                    }
                }
            }
            System.out.printf("#%d %d%n", tc, cnt);
        }
        sc.close();
    }

    public static void change_bfs(int ni, int nj) {
        int tmp = 0;
        for (int[] d : delta) {
            int dx = ni + d[0];
            int dy = nj + d[1];
            if (dx >= 0 && dx < N && dy >= 0 && dy < N && arr[dx][dy] == '*') {
                tmp++;
            }
        }
        arr[ni][nj] = (char) (tmp + '0');
    }

    public static void count_check(int nr, int nc) {
        Queue<int[]> Q = new LinkedList<>();
        Q.offer(new int[]{nr, nc});
        arr[nr][nc] = '*';

        while (!Q.isEmpty()) {
            int[] current = Q.poll();
            int ni = current[0];
            int nj = current[1];

            for (int[] d : delta) {
                int dx = ni + d[0];
                int dy = nj + d[1];
                if (dx >= 0 && dx < N && dy >= 0 && dy < N) {
                    if (arr[dx][dy] == '0') {
                        arr[dx][dy] = '*';
                        Q.offer(new int[]{dx, dy});
                    } else if (arr[dx][dy] != '*') {
                        arr[dx][dy] = '*';
                    }
                }
            }
        }
    }
}