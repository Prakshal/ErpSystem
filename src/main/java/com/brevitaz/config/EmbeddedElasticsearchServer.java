package com.brevitaz.config;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.cluster.ClusterName;
import org.elasticsearch.common.network.NetworkModule;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.elasticsearch.node.Node;
import org.elasticsearch.node.NodeValidationException;

import java.io.File;
import java.io.IOException;

public class EmbeddedElasticsearchServer {
    private static final String DEFAULT_HOME_DIRECTORY = "target/elasticsearch-home";
    private final Node node;
    private String homeDirectory = null;

    public EmbeddedElasticsearchServer(String defaultHomeDirectory) throws IOException {
        FileUtils.deleteDirectory(new File(homeDirectory));

        this.homeDirectory = homeDirectory;

        Settings.Builder elasticsearchSettings = Settings.builder()
                .put(Node.NODE_NAME_SETTING.getKey(), "testNode")
                .put(NetworkModule.TRANSPORT_TYPE_KEY, "local")
                .put(ClusterName.CLUSTER_NAME_SETTING.getKey(), "testCluster")
                .put(Environment.PATH_HOME_SETTING.getKey(), homeDirectory)
                .put(NetworkModule.HTTP_ENABLED.getKey(), false)
                .put("discovery.zen.ping_timeout", 0); // make startup faster

        this.node = new Node(elasticsearchSettings.build());
    }

    public void start() {
        try {
            this.node.start();
        } catch (NodeValidationException e) {
            e.printStackTrace();
        }
    }


    public RestHighLevelClient getClient() {
        return (RestHighLevelClient) node.client();
    }

    public void shutdown() {
        try {
            node.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileUtils.deleteDirectory(new File(homeDirectory));
        } catch (IOException e) {
            throw new RuntimeException("Could not delete home directory of embedded elasticsearch server", e);
        }
    }
}
