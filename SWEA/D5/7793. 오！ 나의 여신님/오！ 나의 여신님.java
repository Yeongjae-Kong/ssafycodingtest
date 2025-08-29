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

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution
{
	static int N, M;
    static char[][] map;
    static int[][] suyeonTime;
    static int[][] devilTime;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static Queue<Point> suyeonQueue;
    static Queue<Point> devilQueue;

    static class Point {
        int r, c, time;

        Point(int r, int c, int time) {
            this.r = r;
            this.c = c;
            this.time = time;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int t = 1; t <= T; t++) {
            N = sc.nextInt();
            M = sc.nextInt();
            map = new char[N][M];
            suyeonTime = new int[N][M];
            devilTime = new int[N][M];
            suyeonQueue = new LinkedList<>();
            devilQueue = new LinkedList<>();

            for (int i = 0; i < N; i++) {
                String line = sc.next();
                for (int j = 0; j < M; j++) {
                    map[i][j] = line.charAt(j);
                    suyeonTime[i][j] = -1;
                    devilTime[i][j] = -1;

                    if (map[i][j] == 'S') {
                        suyeonQueue.add(new Point(i, j, 0));
                        suyeonTime[i][j] = 0;
                    } else if (map[i][j] == '*') {
                        devilQueue.add(new Point(i, j, 0));
                        devilTime[i][j] = 0;
                    }
                }
            }

            // 악마 BFS
            while (!devilQueue.isEmpty()) {
                Point p = devilQueue.poll();
                for (int d = 0; d < 4; d++) {
                    int nr = p.r + dr[d];
                    int nc = p.c + dc[d];
                    if (nr >= 0 && nr < N && nc >= 0 && nc < M && devilTime[nr][nc] == -1 && map[nr][nc] != 'X' && map[nr][nc] != 'D') {
                        devilTime[nr][nc] = p.time + 1;
                        devilQueue.add(new Point(nr, nc, p.time + 1));
                    }
                }
            }
            
            // 수연이 BFS
            int minTime = -1;
            while (!suyeonQueue.isEmpty()) {
                Point p = suyeonQueue.poll();
                if (map[p.r][p.c] == 'D') {
                    minTime = p.time;
                    break;
                }
                for (int d = 0; d < 4; d++) {
                    int nr = p.r + dr[d];
                    int nc = p.c + dc[d];
                    if (nr >= 0 && nr < N && nc >= 0 && nc < M && suyeonTime[nr][nc] == -1 && map[nr][nc] != 'X') {
                        // 악마가 도달하기 전이어야 이동 가능
                        if (devilTime[nr][nc] == -1 || devilTime[nr][nc] > p.time + 1) {
                            suyeonTime[nr][nc] = p.time + 1;
                            suyeonQueue.add(new Point(nr, nc, p.time + 1));
                        }
                    }
                }
            }
            
            System.out.print("#" + t + " ");
            if (minTime == -1) {
                System.out.println("GAME OVER");
            } else {
                System.out.println(minTime);
            }
        }
    }
}