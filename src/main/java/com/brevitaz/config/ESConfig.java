package com.brevitaz.config;

import javafx.scene.NodeBuilder;
import org.apache.http.HttpHost;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.node.Node;
import org.elasticsearch.node.NodeValidationException;
import org.elasticsearch.test.ESIntegTestCase;
import org.elasticsearch.transport.Netty4Plugin;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

import static java.util.Arrays.asList;
import static org.elasticsearch.test.ESIntegTestCase.client;
//import org.springframework.data.elasticsearch.core.ElasticsearchOperations;

@Configuration
public class ESConfig {
    @Value("${elasticsearch.port}")
    Integer portNumber;

    @Value("${elasticsearch.host}")
    String host;

    @Value("${elasticsearch.scheme}")
    String scheme;

    /*@Value("$(elasticsearch.clustername")
    String clusterName;*/

    private RestHighLevelClient esClient;

    @Bean
    public RestHighLevelClient getEsClient() {
        if (esClient == null) {
            this.esClient = new RestHighLevelClient(
                    RestClient.builder(new HttpHost(host, portNumber, scheme)).build());
        }
        return esClient;
    }

    public void setEsClient(RestHighLevelClient esClient) {
        this.esClient = esClient;
    }
    public ESConfig() {

    }

   /* @Bean
    public Node elasticSearchTestNode() throws NodeValidationException {
        Node node = (
                Settings.builder()
                        .put("http.type", "netty4")
                        .put("http.type", "netty4")
                        .put("http.enabled", "true")
                        .put("path.home", "elasticsearch-data")
                        .build(),
                asList(Netty4Plugin.class));
        node.start();
        return node;
    }*/


}
