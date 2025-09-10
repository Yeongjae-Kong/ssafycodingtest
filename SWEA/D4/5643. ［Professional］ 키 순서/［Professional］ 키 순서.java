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
	static int T, N, M;
    static int INF = 10000000;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        T = sc.nextInt();
        for (int t = 1; t <= T; ++t) {
            N = sc.nextInt();
            M = sc.nextInt();
            int[][] distance = new int[N + 1][N + 1];

            for (int i = 1; i <= N; ++i) {
                for (int j = 1; j <= N; ++j) {
                    distance[i][j] = INF;
                }
            }
            for (int i = 1; i <= N; ++i) {
                distance[i][i] = 0;
            }

            for (int i = 0; i < M; ++i) {
                int u = sc.nextInt();
                int v = sc.nextInt();
                // u가 v보다 성적이 높음
                distance[u][v] = 1;
            }

            // 플로이드-워셜
            // k는 중간 경유
            for (int k = 1; k <= N; ++k) {
                // i는 시작
                for (int i = 1; i <= N; ++i) {
                    // j는 도착
                    for (int j = 1; j <= N; ++j) {
                        distance[i][j] = Math.min(distance[i][j], distance[i][k] + distance[k][j]);
                    }
                }
            }

            // 자신의 순위 알 수 있는 학생 수
            int certainRankStudents = 0;
            for (int i = 1; i <= N; ++i) {
                int higherRankCount = 0; // i보다 성적이 높은 학생 수
                int lowerRankCount = 0;  // i보다 성적이 낮은 학생 수

                for (int j = 1; j <= N; ++j) {
                    if (i == j) continue;
                    
                    // j에서 i로 갈 수 있으면 j가 i보다 성적이 높음
                    if (distance[j][i] != INF) {
                        higherRankCount++;
                    }
                    // i에서 j로 갈 수 있으면 i가 j보다 성적이 높음
                    if (distance[i][j] != INF) {
                        lowerRankCount++;
                    }
                }

                // 성적이 높거나 낮은 학생 수의 합이 전체 학생 수(N-1)와 같으면 순위가 확실
                if (higherRankCount + lowerRankCount == N - 1) {
                    certainRankStudents++;
                }
            }

            System.out.printf("#%d %d\n", t, certainRankStudents);
        }
        sc.close();
    }
}