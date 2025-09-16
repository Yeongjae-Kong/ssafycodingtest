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
	static final long MOD = 1234567891L;

    static long modPow(long a, long e) {
        long res = 1L;
        a %= MOD;
        while (e > 0) {
            if ((e & 1L) == 1L) res = (res * a) % MOD;
            a = (a * a) % MOD;
            e >>= 1;
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        int[] ns = new int[T];
        int[] rs = new int[T];
        int maxN = 0;

        for (int i = 0; i < T; i++) {
            int n = sc.nextInt();
            int r = sc.nextInt();
            ns[i] = n; rs[i] = r;
            if (n > maxN) maxN = n;
        }

        // 1) factorial 전처리
        long[] fact = new long[maxN + 1];
        fact[0] = 1L;
        for (int i = 1; i <= maxN; i++) {
            fact[i] = (fact[i - 1] * i) % MOD;
        }

        // 2) inverse factorial 전처리 (pow 한 번만)
        long[] invFact = new long[maxN + 1];
        invFact[maxN] = modPow(fact[maxN], MOD - 2);
        for (int i = maxN; i >= 1; i--) {
            invFact[i - 1] = (invFact[i] * i) % MOD;
        }

        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            int n = ns[t], r = rs[t];
            if (r < 0 || r > n) {
                sb.append('#').append(t + 1).append(' ').append(0).append('\n');
                continue;
            }
            if (r > n - r) r = n - r; // 대칭성

            long ans = fact[n];
            ans = (ans * ((invFact[r] * invFact[n - r]) % MOD)) % MOD;

            sb.append('#').append(t + 1).append(' ').append(ans).append('\n');
        }

        System.out.print(sb.toString());
        sc.close();
    }
}