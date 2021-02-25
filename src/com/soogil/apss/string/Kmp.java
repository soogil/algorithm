package com.soogil.apss.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * 주어진 짚더미 문자열 H와 바늘 문자열 N을 부분 문자열로 포함하는지 확인하고 포함한다면 N과 일치하는 부분 문자열의
 * 시작 위치를 찾응 문제를 문자열 검색 문제라 한다.
 * H = avadakedavar, N = aked라 할때 전체문자열에 부분문자열이 몇번 포함되는지 알수있는 프로그램을 작성하세요.
 *
 * 예제 입출력
 * avadakedavar aked
 * ---------------------
 * 1
 * */

public class Kmp {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String h,n;

        StringTokenizer st = new StringTokenizer(bufferedReader.readLine(), " ");
        h = st.nextToken();
        n = st.nextToken();

        kmpAlgorithm(h, n);
    }

    //수정 중
    private static void kmpAlgorithm(String h, String n) {
        int matchStringCount = 0;
        int hIndex = 0, nIndex = 0;
        int hLength = h.length(), nLength = n.length();
        int[] pi = getPI(n);

        while (hIndex < hLength) {
            for (nIndex = 0; nIndex < nLength; nIndex++) {
                if(hIndex >= hLength) break;
                if(h.charAt(hIndex) == n.charAt(nIndex)) {
                    if(nIndex == nLength - 1) {
                        matchStringCount++;
                    }
                    hIndex++;
                } else {
                    int nextIndex = pi[nIndex] + 1;
                    hIndex += nextIndex;
                    nIndex = 0;
                }
            }
        }

        System.out.println(matchStringCount);
    }

    private static int[] getPI(String pattern) {
        int cIndex = 0; // compare index
        int[] pi = new int[pattern.length()];

        for (int index = 1; index < pattern.length(); index++) {
            while (cIndex > 0 && pattern.charAt(index) != pattern.charAt(cIndex)) cIndex = pi[cIndex-1];
            if (pattern.charAt(index) == pattern.charAt(cIndex)) pi[index] = ++cIndex;
        }

        return pi;
    }
}
