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
import java.io.FileNotFoundException;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution
{
	static int N;
	static int [] operator;
	static int [] op_arr;
	static int [] arr;
	static int min_ans;
	static int max_ans;
	public static void main(String[] args) throws FileNotFoundException {
//		System.setIn(new FileInputStream("src/input.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int i=1;i<T+1;i++) {
			N = sc.nextInt();
			operator = new int[4];
			arr = new int[N];
			operator[0] = sc.nextInt();
			operator[1] = sc.nextInt();
			operator[2] = sc.nextInt();
			operator[3] = sc.nextInt();
			op_arr = new int[N-1];
			min_ans = 100000000;
			max_ans = -100000000;
			for (int j=0;j<N;j++) {
				arr[j] = sc.nextInt();
			}
			check(0);
			int answer = max_ans - min_ans;
			System.out.println("#"+i+" "+answer);
		}
	}
	static void check(int idx) {
		if (idx == N-1) {
			int ans = arr[0];
			for (int i=0;i<N-1;i++) {
				switch (op_arr[i]) {
				case 0:
					ans += arr[i+1];
					break;
				case 1:
					ans -= arr[i+1];
					break;
				case 2:
					ans *= arr[i+1];
					break;
				case 3:
					ans /= arr[i+1];
					break;
				}
			}
			if (ans > max_ans) {
				max_ans = ans;
			}
			if (ans < min_ans) {
				min_ans = ans;
			}
			return;
		}
		for (int i=0;i<4;i++) {
			if (operator[i] == 0) continue;
			operator[i] -= 1;
			op_arr[idx] = i;
			check(idx+1);
			operator[i] += 1;
		}
	}
}