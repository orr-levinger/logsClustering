package impl;

import com.google.common.io.Files;
import face.Cluster;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 310237164 on 24/04/2017.
 */
public class ClusteringTest {

    @Test
    public void clusteringTest() throws IOException {
        ClusterService clusterService = new ClusterService();
        File file = SampleFileGenerator.generateSampleFile("sample.txt").toFile();
        List<String> lines = Files.readLines(file, Charset.forName("utf-8"));
        lines.forEach(clusterService::handleNewLine);
        Files.write(clusterService.getReport().getBytes(),new File("result.txt"));
    }


}
