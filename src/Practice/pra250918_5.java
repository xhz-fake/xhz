package Practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class pra250918_5 {
    static class Record {
        String dateStr;
        String type;
        int price1;
        int price2;
        int dateInt;

        public Record(String dateStr, String type, int price1, int price2) {
            this.dateStr = dateStr;
            this.type = type;
            this.price1 = price1;
            this.price2 = price2;
            this.dateInt = convertDateToInt(dateStr);
        }
    }

    public static int convertDateToInt(String dateStr) {
        String[] parts = dateStr.split("/");
        int year = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int day = Integer.parseInt(parts[2]);
        return year * 10000 + month * 100 + day;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] firstLine = br.readLine().trim().split("\\s+");
        int N = Integer.parseInt(firstLine[0]);
        int M = Integer.parseInt(firstLine[1]);

        List<Record> openList = new ArrayList<>();
        List<Record> closeList = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String line = br.readLine().trim();
            String[] tokens = line.split("\\s+");
            if (tokens.length < 4) {
                continue;
            }
            String dateStr = tokens[0];
            String type = tokens[1];
            int price1 = Integer.parseInt(tokens[2]);
            int price2 = Integer.parseInt(tokens[3]);
            Record record = new Record(dateStr, type, price1, price2);
            if ("open".equals(type)) {
                openList.add(record);
            } else if ("close".equals(type)) {
                closeList.add(record);
            }
        }

        openList.sort(Comparator.comparingInt(r -> r.dateInt));
        closeList.sort(Comparator.comparingInt(r -> r.dateInt));

        for (int i = 0; i < openList.size(); i++) {
            if (i < M - 1) continue;
            int sum1 = 0;
            int sum2 = 0;
            for (int j = i - M + 1; j <= i; j++) {
                Record r = openList.get(j);
                sum1 += r.price1;
                sum2 += r.price2;
            }
            int avg1 = sum1 / M;
            int avg2 = sum2 / M;
            System.out.println(openList.get(i).dateStr + " " + openList.get(i).type + " " + avg1 + " " + avg2);
        }

        for (int i = 0; i < closeList.size(); i++) {
            if (i < M - 1) continue;
            int sum1 = 0;
            int sum2 = 0;
            for (int j = i - M + 1; j <= i; j++) {
                Record r = closeList.get(j);
                sum1 += r.price1;
                sum2 += r.price2;
            }
            int avg1 = sum1 / M;
            int avg2 = sum2 / M;
            System.out.println(closeList.get(i).dateStr + " " + closeList.get(i).type + " " + avg1 + " " + avg2);
        }
    }
}