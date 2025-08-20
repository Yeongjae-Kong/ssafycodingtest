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
    static int t;
    static int h, w;
    static String [][] arr;
    static int n;
    static String [] shoot;
	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        t = sc.nextInt();

        for (int T = 0; T < t; T++) {
            h = sc.nextInt();
            w = sc.nextInt();
            String temp = ""; // 전차의 방향만 저장
            
            // 전차의 위치를 추적할 변수 추가
            int tankY = -1;
            int tankX = -1;

            sc.nextLine(); // nextInt() 이후에 남아있는 개행문자를 처리

            arr = new String[h][w];
            for (int i = 0; i < h; i++) {
                String line = sc.nextLine();
                for (int j = 0; j < w; j++) {
                    String cell = String.valueOf(line.charAt(j));
                    arr[i][j] = cell;

                    if (cell.equals("^") || cell.equals("v") || cell.equals("<") || cell.equals(">")) {
                        tankY = i;
                        tankX = j;
                        temp = cell;
                    }
                }
            }

            n = sc.nextInt();
            sc.nextLine(); // nextInt() 이후 개행문자 처리
            String s = sc.nextLine();

            shoot = new String[n];
            for (int i = 0; i < n; i++) {
                shoot[i] = String.valueOf(s.charAt(i));
            }

            for (int i = 0; i < n; i++) {
                String cmd = shoot[i];

                if (cmd.equals("U")) {
                    temp = "^";
                    arr[tankY][tankX] = temp;
                    int nextY = tankY - 1;
                    if (nextY >= 0 && arr[nextY][tankX].equals(".")) {
                        arr[tankY][tankX] = ".";
                        tankY = nextY;
                        arr[tankY][tankX] = temp;
                    }
                } else if (cmd.equals("D")) {
                    temp = "v";
                    arr[tankY][tankX] = temp;
                    int nextY = tankY + 1;
                    if (nextY < h && arr[nextY][tankX].equals(".")) {
                        arr[tankY][tankX] = ".";
                        tankY = nextY;
                        arr[tankY][tankX] = temp;
                    }
                } else if (cmd.equals("L")) {
                    temp = "<";
                    arr[tankY][tankX] = temp;
                    int nextX = tankX - 1;
                    if (nextX >= 0 && arr[tankY][nextX].equals(".")) {
                        arr[tankY][tankX] = ".";
                        tankX = nextX;
                        arr[tankY][tankX] = temp;
                    }
                } else if (cmd.equals("R")) {
                    temp = ">";
                    arr[tankY][tankX] = temp;
                    int nextX = tankX + 1;
                    if (nextX < w && arr[tankY][nextX].equals(".")) {
                        arr[tankY][tankX] = ".";
                        tankX = nextX;
                        arr[tankY][tankX] = temp;
                    }
                } else if (cmd.equals("S")) {
                    int bulletY = tankY;
                    int bulletX = tankX;

                    while (true) {
                        if (temp.equals("^")) bulletY--;
                        else if (temp.equals("v")) bulletY++;
                        else if (temp.equals("<")) bulletX--;
                        else if (temp.equals(">")) bulletX++;

                        if (bulletY < 0 || bulletY >= h || bulletX < 0 || bulletX >= w) {
                            break;
                        }

                        if (arr[bulletY][bulletX].equals("*")) {
                            arr[bulletY][bulletX] = ".";
                            break;
                        } else if (arr[bulletY][bulletX].equals("#")) {
                            break;
                        }
                    }
                }
            }

            System.out.print("#"+(T+1)+" ");
            for (int i=0; i<h; i++) {
                for (int j=0; j<w; j++) {
                    System.out.print(arr[i][j]);
                }
                System.out.println();
            }
        }
        sc.close();
    }
}