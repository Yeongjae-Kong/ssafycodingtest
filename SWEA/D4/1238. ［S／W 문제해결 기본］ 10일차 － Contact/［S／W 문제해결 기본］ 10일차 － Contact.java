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
import java.util.Queue;
import java.util.ArrayDeque;
/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution
{
	static int [][] arr;
	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
         
        int T = 10;
 
        for(int test_case = 1; test_case <= T; test_case++) {
            arr = new int[101][101];
             
            int N = sc.nextInt();
            int start = sc.nextInt();
             
            for(int i = 0; i < N / 2; i++) {
                arr[sc.nextInt()][sc.nextInt()] = 1;
            }
             
            System.out.println("#"+ test_case + " " + BFS(start));
        }
    }
	public static int BFS(int start) {
        int cnt = 0;
        int result = 0;
        int[] visited = new int[101];
        Queue<Integer> q = new ArrayDeque<>();
         
        q.offer(start);
        visited[start] = 1;
         
        while(!q.isEmpty()) {
            int current = q.poll();
             
            for(int i = 1; i < 101; i++) {
                if(arr[current][i] == 1 && visited[i] == 0) {
                    q.offer(i);
                    visited[i] = visited[current] + 1;
                }               
            }
            cnt = visited[current];
        }
         
        for(int i = 1; i < 101; i++) {
            if (visited[i] == cnt)
                result = i;
        }
         
        return result;
	}
}