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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int N;
    static Point[] worms;
    static boolean[] isStart;
    static long minVectorSize;

    static class Point {
        long x, y;
        Point(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            worms = new Point[N];
            isStart = new boolean[N];
            minVectorSize = Long.MAX_VALUE;

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                worms[i] = new Point(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));
            }

            // N마리 중 N/2마리를 시작점으로 선택하는 모든 조합을 탐색
            combination(0, 0);

            sb.append("#").append(tc).append(" ").append(minVectorSize).append("\n");
        }
        System.out.print(sb);
    }

    // N개의 지렁이 중 N/2개를 선택하는 조합
    public static void combination(int index, int count) {
        // N/2개를 모두 선택했다면 벡터 합을 계산
        if (count == N / 2) {
            long sumX = 0;
            long sumY = 0;

            for (int i = 0; i < N; i++) {
                if (isStart[i]) {
                    // 시작점의 좌표는 뺀다
                    sumX -= worms[i].x;
                    sumY -= worms[i].y;
                } else {
                    // 도착점의 좌표는 더한다
                    sumX += worms[i].x;
                    sumY += worms[i].y;
                }
            }
            
            // 벡터 크기 계산
            long currentVectorSize = sumX * sumX + sumY * sumY;
            minVectorSize = Math.min(minVectorSize, currentVectorSize);
            return;
        }

        // 모든 지렁이를 확인했다면 종료
        if (index == N) {
            return;
        }

        // 현재 지렁이를 시작점으로 선택
        isStart[index] = true;
        combination(index + 1, count + 1);

        // 현재 지렁이를 시작점으로 선택하지 않음 (도착점)
        isStart[index] = false;
        combination(index + 1, count);
    }
}