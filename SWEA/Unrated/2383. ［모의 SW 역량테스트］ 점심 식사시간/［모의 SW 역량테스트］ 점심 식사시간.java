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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
    static int[][] stairs, personDistances;
    static List<int[]> people;
    static int minTime;
    static int[] assignedStair;
    static int[] stairDepths;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= t; tc++) {
            int n = Integer.parseInt(br.readLine());
            stairs = new int[2][2];
            stairDepths = new int[2];
            int stairIndex = 0;
            minTime = Integer.MAX_VALUE;
            people = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    int value = Integer.parseInt(st.nextToken());
                    if (value == 1) {
                        people.add(new int[]{i, j});
                    } else if (value > 1) {
                        stairDepths[stairIndex] = value;
                        stairs[stairIndex][0] = i;
                        stairs[stairIndex++][1] = j;
                    }
                }
            }

            personDistances = new int[people.size()][2];
            for (int i = 0; i < people.size(); i++) {
                int[] personLocation = people.get(i);
                // The distance is manhattan distance + 1 minute to start descending the stairs.
                personDistances[i][0] = Math.abs(stairs[0][0] - personLocation[0]) + Math.abs(stairs[0][1] - personLocation[1]) + 1;
                personDistances[i][1] = Math.abs(stairs[1][0] - personLocation[0]) + Math.abs(stairs[1][1] - personLocation[1]) + 1;
            }

            assignedStair = new int[people.size()];
            assignPeopleToStairs(0);

            sb.append("#" + tc + " " + minTime + "\n");
        }
        System.out.print(sb);
    }

    /**
     * Recursively assigns each person to one of the two stairs.
     *
     * @param personIndex The index of the person to assign.
     */
    static void assignPeopleToStairs(int personIndex) {
        if (personIndex == people.size()) {
            calculateTime();
            return;
        }

        // Assign to stair 0
        assignedStair[personIndex] = 0;
        assignPeopleToStairs(personIndex + 1);

        // Assign to stair 1
        assignedStair[personIndex] = 1;
        assignPeopleToStairs(personIndex + 1);
    }

    /**
     * Calculates the total time for the current stair assignment.
     */
    static void calculateTime() {
        // Priority queues to hold the arrival times of people at each stair
        PriorityQueue<Integer> stair0Arrivals = new PriorityQueue<>();
        PriorityQueue<Integer> stair1Arrivals = new PriorityQueue<>();

        for (int i = 0; i < people.size(); i++) {
            if (assignedStair[i] == 0) {
                stair0Arrivals.add(personDistances[i][0]);
            } else {
                stair1Arrivals.add(personDistances[i][1]);
            }
        }

        int timeForStair0 = getTimeForStair(stair0Arrivals, stairDepths[0]);
        int timeForStair1 = getTimeForStair(stair1Arrivals, stairDepths[1]);

        int totalTime = Math.max(timeForStair0, timeForStair1);
        minTime = Math.min(minTime, totalTime);
    }

    /**
     * Calculates the time it takes for all people to descend a single stair.
     *
     * @param arrivalTimes A priority queue of people's arrival times.
     * @param depth The depth of the stair.
     * @return The time required for all people to descend.
     */
    static int getTimeForStair(PriorityQueue<Integer> arrivalTimes, int depth) {
        if (arrivalTimes.isEmpty()) {
            return 0;
        }

        List<Integer> descendingTimes = new LinkedList<>();
        int time = 0;

        while (!arrivalTimes.isEmpty() || !descendingTimes.isEmpty()) {
            time++;

            // Check if people can finish descending
            for (int i = descendingTimes.size() - 1; i >= 0; i--) {
                if (descendingTimes.get(i) == time) {
                    descendingTimes.remove(i);
                }
            }

            // Add new people to the stair if there's space
            while (!arrivalTimes.isEmpty() && arrivalTimes.peek() <= time && descendingTimes.size() < 3) {
                arrivalTimes.poll();
                descendingTimes.add(time + depth);
            }
        }
        return time;
    }
}