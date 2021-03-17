package zarpat.works;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

public class Anagrams {

    public static void main(String[] args) throws Exception {
        Anagrams anagram = new Anagrams();

        FileWriter fileWriter = new FileWriter("C:\\Programowanie\\Java\\Anagrams\\src\\zarpat\\works\\results.txt");
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        List<String> list;
        List<String> anagrams;

        list = anagram.loadFile();

        if(list.size()==0){
            System.out.println("NO WORDS TO LOAD!!");
        } else{
            System.out.println(String.format("Succesfully loaded %,d unique words ", list.size()));
            anagrams = anagram.findAnagrams(list);
            System.out.println(String.format("Succesfully foung %,d anagrams ", anagrams.size() ));

            try {
                for (String s : anagrams) {
                    System.out.println(s);
                    bufferedWriter.write(s);
                    bufferedWriter.newLine();
                }
            } catch (Exception e) {
                System.out.println("SAVING FILE ERROR HAS OCCURED");
                e.printStackTrace();
                bufferedWriter.close();
            }
        }
    }



    public List<String> loadFile() throws Exception {
        List<String> wordsList = new ArrayList<String>();
        Set<String> wordsSet = new HashSet<String>();

        BufferedReader bufferedReader = new BufferedReader(new FileReader("C:\\Programowanie\\Java\\Anagrams\\src\\zarpat\\works\\words.txt"));
        try {
            String line;
            while ((line= bufferedReader.readLine())!=null) {
                if(!line.equals("")){
                    wordsSet.add(line);
                }
            }
            bufferedReader.close();
            wordsList.addAll(wordsSet);
        } catch (Exception e){
            System.out.println("Error while reading file!");
        }
        return wordsList;
    }

    public List<String> findAnagrams(List<String> wordsList){

        Date start = new Date();
        String s = "Finding Anagrams";
        long totalComparisons = 0;
        List<String> list  = new ArrayList<String>();
        System.out.print(s);

        for(int i=0; i < wordsList.size(); i++){
            for(int j=i+1; j <wordsList.size(); j++ ){
                String str1 = wordsList.get(i).toString();
                String str2 = wordsList.get(j).toString();

                totalComparisons++;

                if(compareWords(str1,str2)) {
                    if(i%20==0) {
                        System.out.print(".");
                    }
                    list.add(str1 +" - "+ str2);
                }
            }
        }

        System.out.println(String.format("\n\nComparisons done %,d", totalComparisons));
        Date end = new Date();
        long time = (end.getTime()-start.getTime()) / 1000;  //we get secunds there
        System.out.println(String.format("It has taken %,d seconds to compare all words", time));
        return list;
    }

    public boolean compareWords(String word1, String word2){


        char[] chars1 = word1.toLowerCase().toCharArray();
        char[] chars2 = word2.toLowerCase().toCharArray();

        Arrays.sort(chars1);
        Arrays.sort(chars2);

        String sortedChars1 = String.valueOf(chars1);
        String sortedChars2 = String.valueOf(chars2);

        return sortedChars1.equals(sortedChars2);
    }
}

