package com.soogil.apss.stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;

/**
 * 대학교 수학과의 류화영 교수는 이번에 다 쓴 논문을 훓어보던 중, 수식에 포함된 괄호들이 제대로 짝이 맞지 않는다는
 * 사실을 발견했습니다. 완벽주의자인 류 교수는 컴ㅍ터 프로그램을 이용해 논문에 포함된 모든 수식들의 괄호가 쌍이 잘 맞는지
 * 확인하기로 했습니다. 류교수는 (,{,[ 세종류의 괄호를 사용했습니다. ( 괄호로 열리면 )괄호로 닫혀야 "짝이 맞는다" 라고 표현합니다.
 * 짝이 맞는 프로그램을 작성해 류교수를 도와줍시다.
 *
 * 예제 입출력
 * 3
 * ()()
 * ({[}])
 * ({}[(){}])}
 * ---------------------
 * YES
 * NO
 * YES
 * */

public class bracket2 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(bufferedReader.readLine());
        int inputCount = 0;

        ArrayList<Stack<String>> bracketList = new ArrayList<>(testCase);


        while (inputCount < testCase) {
            String[] input = new String(bufferedReader.readLine()).split("");
            Stack<String > stack = new Stack<String>();
            for (String s : input) {
                stack.push(s);
            }

            bracketList.add(stack);
            inputCount++;
        }

        for(int count = 0; count < testCase; count++) {
            checkBracket(bracketList.get(count));
        }
    }

    private static void checkBracket(Stack<String> bracket) {
        boolean findBracket = false;

        String openBracket = bracket.pop();
        while (bracket.size() != 0) {

            String closeBracket = bracket.pop();
            if(openBracket.equals("}") && closeBracket.equals("{")) findBracket = true;
            else if(openBracket.equals("]") && closeBracket.equals("[")) findBracket = true;
            else if(openBracket.equals(")") && closeBracket.equals("(")) findBracket = true;

            if(findBracket) break;
            else openBracket = closeBracket;
        }

        System.out.println(findBracket ? "YES" : "NO");
    }
}
