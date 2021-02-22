package apss.list;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 조세푸스는 로마와의 전쟁서에 패해 동굴에 n-1명의 동료와 갇혔다.
 * 동료 병사들은 적에게 잡히느니 차라리 자살하자고 결의했고, n명의 사람들이 모두 원형으로 둘러선 뒤
 * 순서대로 자살 하기로 했습니다. 한사람이 자살하면 그 사람으로부터 시계 방향으로 k번째 살아있는 사람이 자살하는 것입니다.
 * 조세푸스의 책에 따르면 조세푸스와 다른 병사 한명만이 살아남았다고 합니다. 마지막 두명 중 하나가 되기 위해서는 조세푸스는
 * 첫번째 병사로부터 몇자리 떨어진 곳에 있어야 했을까?
 *
 * 예제 입출력
 * 2
 * 6 3
 * 40 3
 * ---------------------
 * 3 5
 * 11 26
 * */

public class josephus {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(bufferedReader.readLine());
        int inputCount = 0;
        int[] total = new int[testCase], k = new int[testCase];

        while (inputCount < testCase) {
            StringTokenizer st = new StringTokenizer(bufferedReader.readLine(), " ");
            total[inputCount] = Integer.parseInt(st.nextToken());
            k[inputCount] = Integer.parseInt(st.nextToken());
            inputCount++;
        }

        for(int count = 0; count < testCase; count++) {
            killOneself(total[count], k[count]);
        }
    }

    private static void killOneself(int total, int k) {
        ArrayList<Integer> totalList = new ArrayList<>();
        int currentIndex = 0;

        for(int value = 1; value < total + 1; value++) totalList.add(value);

        while (totalList.size() > 2) {
            //System.out.printf("(%d,%d,%d) ", currentIndex, totalList.get(currentIndex), totalList.size());
            totalList.remove(currentIndex);

            if(currentIndex >= totalList.size()) currentIndex = 0;

            for(int count = 1; count < k; count++) {
                currentIndex++;

                if(currentIndex >= totalList.size()) currentIndex = 0;
            }
        }

        StringBuffer output = new StringBuffer();
        for (Integer value : totalList) output.append(value.toString()).append(" ");

        System.out.println(output);
    }
}
