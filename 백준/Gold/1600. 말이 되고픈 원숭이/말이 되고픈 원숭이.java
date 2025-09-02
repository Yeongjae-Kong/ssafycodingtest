import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int INF = 987654321;
	
	static int[] dr = {0,0,1,-1};
	static int[] dc = {1,-1,0,0};
	
	static int[] kdr = {-2,-2,-1,-1,1,1,2,2};
	static int[] kdc = {-1,1,-2,2,-2,2,-1,1};
	
	static int K, W, H;
	static int[][] A = new int[200][200];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		K = Integer.parseInt(br.readLine());
				
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		for (int i=0;i<H;++i) {
			st = new StringTokenizer(br.readLine());
			for (int j=0;j<W;++j)
				A[i][j] = Integer.parseInt(st.nextToken());
		}
		
		int[][][] D = new int[K+1][H][W];
		Queue<Integer> Q = new ArrayDeque<>();
		Q.add(K);
		Q.add(0);
		Q.add(0);
		D[K][0][0] = 1;
		while (Q.size() > 0) {
			int k = Q.poll();
			int r = Q.poll();
			int c = Q.poll();
			
			for (int i=0;i<4;++i) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				if (nr < 0 || nr >= H || nc < 0 || nc >= W) continue;
				if (A[nr][nc] == 1 || D[k][nr][nc] > 0) continue;
				D[k][nr][nc] = D[k][r][c] + 1;
				Q.add(k);
				Q.add(nr);
				Q.add(nc);
			}
			
			if (k == 0) continue;
			
			for (int i=0;i<8;++i) {
				int nr = r + kdr[i];
				int nc = c + kdc[i];
				if (nr < 0 || nr >= H || nc < 0 || nc >= W) continue;
				if (A[nr][nc] == 1 || D[k-1][nr][nc] > 0) continue;
				D[k-1][nr][nc] = D[k][r][c] + 1;
				Q.add(k-1);
				Q.add(nr);
				Q.add(nc);
			}
			
		}
		
		int ans = INF;
		for (int k=0;k<=K;++k) {
			if (D[k][H-1][W-1] == 0) continue;
			ans = Math.min(ans, D[k][H-1][W-1]);
		}
		
		if (ans == INF) ans = 0;
		
		System.out.println(ans-1);
		
	}
}