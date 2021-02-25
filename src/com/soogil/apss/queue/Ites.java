package com.soogil.apss.queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 *
 * 수환이는 외계에서 날아오는 전파를 연구하는 범세게 대규포 프로젝트, ITES@home에 참가하고 있습니다.
 * 외계에서 날아오는 전파는 전처리를 거쳐 각 숫자가 [1, 10000] 범위 안에 들어가는 자연수 수열로 주어지는데,
 * 이 전파가 과연 단순한 노이즈인지 아니면 의미있는 패턴을 가지고 있는지를 파악하고 싶습니다. 수환이는 전파의 부분 수열 중에
 * 합이 K 인것이 유독 많다는 사실을 눈치 챘습니다. 부분 수열이란 연속된 수열의 일부를 말합니다.
 * 예를 들어 수열 [1, 4, 2, 1, 4, 3, 1, 6]에서 합이 7인 부분 수열은 다음과 같이 다섯개 입니다.
 * {1,4,2}, {4,2,1}, {2,1,4}, {4,3}, {1,6} 이 부분 수열들은 서로 겹쳐도 된다는 데 유의합시다.
 * K가 외계인에게 의미 있는 숫자일까요? 수환이의 가설을 확인하기 위해 길이 N인 신호 기록이 주어질 때 합이 K인 부분 수열이
 * 몇 개나 있는지 계산하는 프로그램을 작성하세요.
 *
 * 입력의 크기가 큰 관계로, 이 문제에서는 신호 기록을 입력받는 대신 다음과 같은 식을 통해 프로그램 내에서 직접 생성합니다.
 *
 * 입력 생성
 * A[0] = 1983
 * A[i] = (A[i-1] * 214013 + 2531011) mod 2^32
 * 이때 i(1<=i<=N)번째 입력 신호는 A[i-1] mod 10000 + 1 입니다. 이 규칙에 따르면 첫 5개의 신호는
 * 각 1984, 8791, 4770, 7665, 3188 입니다.
 *
 * 예제 입출력
 * 3
 * 8791 20
 * 5265 5000
 * 3578452 5000000
 * ---------------------
 * 1
 * 4
 * 1049
 * */

public class Ites {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(bufferedReader.readLine());
        int inputCount = 0;
        int[] k = new int[testCase], n = new int[testCase];

        while (inputCount < testCase) {
            StringTokenizer st = new StringTokenizer(bufferedReader.readLine(), " ");
            k[inputCount] = Integer.parseInt(st.nextToken());
            n[inputCount] = Integer.parseInt(st.nextToken());
            inputCount++;
        }

        for(int count = 0; count < testCase; count++) {
            checkSignal(k[count], n[count]);
        }
    }

    private static void checkSignal(int k, int n) {
        Queue<Long> queue = new LinkedList<Long>();

        Signal signal = new Signal();
        queue.add(signal.nextSignal());

        int count = 0, result = 0;
        long sum = queue.element();

        while (count < n) {
            if (sum < k) {
                long next = signal.nextSignal();
                sum += next;
                queue.add(next);
                continue;
            } else {
                if(sum == k) result++;
                sum -= queue.poll();
            }
            count++;
        }
        System.out.println(result);
    }
}

class Signal {

    Signal() {
        signal = 1983;
    }

    long signal;

    public long nextSignal() {
        long input = signal % 10000 + 1;
        signal = (signal * 214013 + 2531011) % Math.round(Math.pow(2,32));

        return input;
    }
}