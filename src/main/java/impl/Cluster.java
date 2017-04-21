package impl;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Or on 21/04/2017.
 */
public class Cluster {

    private int index;
    private String[] pattern;
    private Set<String> words = new HashSet<>();
    private Set<String> logs = new HashSet<>();


    public Cluster(String log) {
        this.pattern = log.split(" ");
        this.logs.add(log);
    }

    boolean match(String log){
        String[] words = log.split(" ");
        if(words.length != pattern.length){
            return false;
        }else{
            if(index!=0){
                for (int i = 2; i < words.length; i++) {
                    if(i != index && !words[i].equals(pattern[i])){
                        return false;
                    }
                }
            }else{
                int numberOfDiffs = 0;
                for (int i = 2; i < words.length; i++) {
                    if(!words[i].equals(pattern[i])){
                        index = i;
                        numberOfDiffs++;
                    }
                    if(numberOfDiffs>1){
                        index = 0;
                        return false;
                    }
                }
            }

        }
        if(logs.size()==1){
            this.words.add(pattern[index]);
        }
        logs.add(log);
        this.words.add(words[index]);

        return true;
    }
    int getIndex(){
        return index;
    }
    void setIndex(int index){
        this.index = index;
    }

    public Set<String> getWords() {
        return words;
    }

    public Set<String> getLogs() {
        return logs;
    }
}
