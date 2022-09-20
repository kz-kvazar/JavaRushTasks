package com.javarush.task.task17.task1721;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/* 
Транзакционность
*/

public class Solution {
    public static List<String> allLines = new ArrayList<String>();
    public static List<String> forRemoveLines = new ArrayList<String>();

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String s1 = bufferedReader.readLine();
        String s2 = bufferedReader.readLine();
        BufferedReader sr1 = new BufferedReader(new FileReader(s1));
        BufferedReader sr2 = new BufferedReader(new FileReader(s2));
        while ((s1 = sr1.readLine()) != null) {
            allLines.add(s1);
        }
        while ((s2 = sr2.readLine()) != null) {
            forRemoveLines.add(s2);
        }
        bufferedReader.close();
        sr1.close();
        sr2.close();
        Solution solution = new Solution();
        solution.joinData();

    }

    public void joinData() throws CorruptedDataException {
        if (new HashSet<>(allLines).containsAll(forRemoveLines)) {
            allLines.removeAll(forRemoveLines);
        } else {
            allLines.clear();
            throw new CorruptedDataException();
        }
    }
}
