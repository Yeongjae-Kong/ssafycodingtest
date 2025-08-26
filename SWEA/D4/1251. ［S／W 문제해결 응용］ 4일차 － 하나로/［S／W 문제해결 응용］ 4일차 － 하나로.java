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
import java.util.Arrays;
import java.util.StringTokenizer;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution
{
	// 가중치를 long 타입으로
    static class Edge implements Comparable<Edge> {
        int from, to;
        long weight;

        public Edge(int from, int to, long weight) {
            super();
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            // 가중치 기준 오름차순 정렬
            return Long.compare(this.weight, o.weight);
        }
    }

    static Edge[] edgeList;
    static int[] parents;
    static int N;

    public static void make() {
        parents = new int[N];
        for (int i = 0; i < N; i++) {
            parents[i] = i;
        }
    }

    private static int find(int a) {
        if (a == parents[a]) return a;
        return parents[a] = find(parents[a]);
    }

    private static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        if (aRoot == bRoot) return false;
        
        parents[bRoot] = aRoot; // union 방식은 어느 쪽이든 상관 x
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        
        int T = Integer.parseInt(in.readLine());

        for (int tc = 1; tc <= T; tc++) {

            N = Integer.parseInt(in.readLine()); // 섬의 개수 N
            
            int[] xPos = new int[N];
            int[] yPos = new int[N];

            st = new StringTokenizer(in.readLine(), " ");
            for (int i = 0; i < N; i++) {
                xPos[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(in.readLine(), " ");
            for (int i = 0; i < N; i++) {
                yPos[i] = Integer.parseInt(st.nextToken());
            }
            
            double E = Double.parseDouble(in.readLine());

            // --- 모든 섬 간의 간선 정보 생성 ---
            // N개의 섬에서 2개를 뽑는 조합의 수만큼 간선 가능
            int numEdges = N * (N - 1) / 2;
            edgeList = new Edge[numEdges];
            
            int idx = 0;
            for (int i = 0; i < N; i++) {
                for (int j = i + 1; j < N; j++) {
                    // 두 섬 간의 거리(L)의 제곱(L^2) 계산
                    long dx = Math.abs(xPos[i] - xPos[j]);
                    long dy = Math.abs(yPos[i] - yPos[j]);
                    long distSq = dx * dx + dy * dy;
                    
                    edgeList[idx++] = new Edge(i, j, distSq);
                }
            }
            
            // --- 크루스칼 알고리즘 ---
            // 간선을 가중치(L^2) 기준으로 오름차순 정렬 및 초기화
            Arrays.sort(edgeList);
            make();
            long resultL2 = 0; // 최소 신장 트리를 구성하는 L^2의 합 (overflow 방지)
            int cnt = 0; // 선택된 간선의 수
            
            // 가중치가 낮은 간선부터 순회하며 MST 구성
            for (Edge edge : edgeList) {
                if (union(edge.from, edge.to)) { // 사이클이 발생하지 않으면
                    resultL2 += edge.weight; // 해당 간선의 L^2 값을 더함
                    if (++cnt == N - 1) break; // N-1개의 간선을 선택하면 종료
                }
            }
            
            long TotalCost = Math.round(resultL2 * E);
            
            System.out.println("#" + tc + " " + TotalCost);
        }
    }
}