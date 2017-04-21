import com.google.common.collect.Lists;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Created by Or on 21/04/2017.
 */
public class LogsGenerator {

    public static void main(String[] args) {

        int size = 10;

        ArrayList<Pattern> patterns = getPatterns();



        List<Cluster>clusters = new ArrayList<>();


        for (int i = 0; i < size; i++) {
            String logMsg = getLogMsg(patterns);

            if(clusters.size()==0){
                clusters.add(new Cluster(logMsg));
            }else{
                boolean matched = false;
                for (Cluster cluster : clusters) {
                    if(cluster.match(logMsg)){
                        matched = true;
                        break;
                    }
                }
                if(!matched){
                    clusters.add(new Cluster(logMsg));
                }
            }
        }


        for (Cluster cluster : clusters) {
            System.out.println("====");
            Set<String> logs = cluster.getLogs();
            for (String log : logs) {
                System.out.println(log);
            }
            System.out.println("The changing word was: " + cluster.getWords());
        }

    }

    private static String getLogMsg(ArrayList<Pattern> patterns) {
        Random random = new Random();
        int patternIndex = random.nextInt(patterns.size());
        return patterns.get(patternIndex).generatePattern();
    }

    private static ArrayList<Pattern> getPatterns() {
        Pattern p1 = () -> getDate() + " " + generateRandomWords() + " is getting into the car";

        Pattern p2 = () -> getDate() + " " + generateRandomWords() + " is eating at a diner";

        Pattern p3 = () -> getDate() + " someone approaches " + generateRandomWords() + " at the driveway";

        return Lists.newArrayList(p1, p2, p3);
    }


    private static String getDate(){
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public static String generateRandomWords() {
        Random random = new Random();
        char[] word = new char[random.nextInt(5) + 3]; // words of length 3 through 10. (1 and 2 letter words are boring.)
        for (int j = 0; j < word.length; j++) {
            word[j] = (char) ('a' + random.nextInt(26));
        }
        return new String(word);
    }
}
