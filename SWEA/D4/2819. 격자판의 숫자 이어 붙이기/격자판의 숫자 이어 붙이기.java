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

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
import java.util.*;
class Solution
{
	static int[][] lst;
    static Set<String> dup_set;  // Java는 배열을 그대로 set에 넣을 수 없으니 문자열/리스트 변환
    static int[][] d = {{0,1},{1,0},{0,-1},{-1,0}};
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        
        for (int test_case = 0; test_case < t; test_case++) {
            lst = new int[4][4];
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    lst[i][j] = sc.nextInt();
                }
            }
            
            dup_set = new HashSet<>();
            
            for (int y = 0; y < 4; y++) {
                for (int x = 0; x < 4; x++) {
                    List<Integer> path = new ArrayList<>();
                    path.add(lst[y][x]);
                    dfs(x, y, path);
                }
            }
            System.out.println("#" + (test_case+1) + " " + dup_set.size());
        }
        
        sc.close();
    }
    
    static void dfs(int x, int y, List<Integer> path) {
        if (path.size() == 7) {
            // 리스트를 문자열로 변환해서 set에 저장
            dup_set.add(path.toString());
            return;
        }
        
        for (int[] dir : d) {
            int nx = x + dir[0];
            int ny = y + dir[1];
            
            if (0 <= nx && nx < 4 && 0 <= ny && ny < 4) {
                path.add(lst[ny][nx]);
                dfs(nx, ny, path);
                path.remove(path.size() - 1); // 백트래킹
            }
        }
    }
}