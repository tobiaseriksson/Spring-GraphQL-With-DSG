package se.tsoft.spring.graphql.dsg.demo;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class GenerateData {


    public static String pickRandom(List<String> lst) {
        Random random = new Random();
        return lst.get(random.nextInt(lst.size()));
    }


    public static void main(String[] args) {
        String txt = Utils.readFileFromResources("firstnames_boys.txt");
        txt = txt + "\n" + Utils.readFileFromResources("firstnames_girls.txt");
        List<String> rows =
                        Arrays.stream(txt.split("\n")).filter( row -> row.trim().length() > 0 ).collect(Collectors.toList());
        System.out.println(rows);
        System.out.println(rows.size());
        System.out.println("Name : "+pickRandom(rows));
    }
}
