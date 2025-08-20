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
    static int T;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		for (int i=1;i<T+1;i++) {
			int [] count = new int[4];
			int [] cost = new int[4];
			int [] plan = new int[12];
			int [] dp = new int[12];
			for (int j=0;j<4;j++) {
				cost[j] = sc.nextInt();
			}
			for (int j=0;j<12;j++) {
				plan[j] = sc.nextInt();
			}
			dp[0]= cost[0]*plan[0]>cost[1] ? cost[1]:cost[0]*plan[0];
			for (int j=1;j<12;j++) {
				if (cost[1] > cost[0]*plan[j]) {
					dp[j]=cost[0]*plan[j]+dp[j-1];
				}
				else {
					dp[j]=cost[1]+dp[j-1];
				}
				dp[2] = Math.min(dp[2], cost[2]);
				if (j>=3) {
					dp[j] = Math.min(dp[j], dp[j-3]+cost[2]);
				}
			}
			dp[11] = dp[11] > cost[3] ? cost[3]:dp[11];

			System.out.println("#"+i+" "+dp[11]);
		}
	}
}