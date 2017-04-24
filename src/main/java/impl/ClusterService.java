package impl;

import face.Cluster;

import java.util.ArrayList;
import java.util.List;

class ClusterService {

    private List<Cluster> clusters = new ArrayList<>();


    public ClusterService() {
    }

    public void handleNewLine(String logMsg){
        if (clusters.size() == 0) {
            clusters.add(ClusterFactory.getCluster(logMsg));
        } else {
            boolean matched = false;
            for (Cluster cluster : clusters) {
                if (cluster.match(logMsg)) {
                    matched = true;
                    break;
                }
            }
            if (!matched) {
                clusters.add(ClusterFactory.getCluster(logMsg));
            }
        }
    }

    public String getReport(){
        StringBuilder stringBuilder = new StringBuilder();
        for (Cluster cluster : clusters) {
            stringBuilder.append("====\n");
            List<String> logs = cluster.getLogs();
            for (String log : logs) {
                stringBuilder.append(log).append("\n");
            }
            stringBuilder.append("The changing word was: ").append(cluster.getWords());
        }
        return stringBuilder.toString();
    }

}
