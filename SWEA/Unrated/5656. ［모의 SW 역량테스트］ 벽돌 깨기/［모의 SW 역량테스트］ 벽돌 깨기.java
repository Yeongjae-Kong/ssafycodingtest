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
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;
/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution
{
	static int N, W, H;
    static int minBrick;
    static int[][] Board;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int t = 1; t <= T; t++) {
            N = sc.nextInt();
            W = sc.nextInt();
            H = sc.nextInt();
            Board = new int[H][W];
            minBrick = Integer.MAX_VALUE;
            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    Board[i][j] = sc.nextInt();
                }
            }
            dfs(Board, 0);
            System.out.println("#" + t + " " + minBrick);
        }
    }

    // DFS (깊이 우선 탐색, 중복 순열)
    public static void dfs(int[][] board, int n) {
        if (n == N) {
            int cnt = 0;
            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    if (board[i][j] != 0) cnt++;
                }
            }
            if (cnt < minBrick) {
                minBrick = cnt;
            }
            return;
        }

        for (int c = 0; c < W; c++) {
            int r = 0;
            while (r < H && board[r][c] == 0) {
                r++;
            }

            int[][] nextBoard = new int[H][W];
            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    nextBoard[i][j] = board[i][j];
                }
            }

            if (r < H) {
                recursiveDestroy(nextBoard, r, c);
                arrange(nextBoard);
            }
            
            dfs(nextBoard, n + 1);
        }
    }

    public static void recursiveDestroy(int[][] board, int r, int c) {
        if (r < 0 || r >= H || c < 0 || c >= W || board[r][c] == 0) {
            return;
        }
        
        int power = board[r][c];
        board[r][c] = 0;

        if (power > 1) {
            for (int d = 0; d < 4; d++) {
                for (int p = 1; p < power; p++) {
                    int nextR = r + dr[d] * p;
                    int nextC = c + dc[d] * p;
                    if (nextR >= 0 && nextR < H && nextC >= 0 && nextC < W && board[nextR][nextC] != 0) {
                        recursiveDestroy(board, nextR, nextC);
                    }
                }
            }
        }
    }
    
    public static void arrange(int[][] board) {
        for (int c = 0; c < W; c++) {
            for (int r = H - 2; r >= 0; r--) {
                if (board[r][c] != 0) {
                    int currentRow = r;
                    while (currentRow < H - 1 && board[currentRow + 1][c] == 0) {
                        board[currentRow + 1][c] = board[currentRow][c];
                        board[currentRow][c] = 0;
                        currentRow++;
                    }
                }
            }
        }
    }
}