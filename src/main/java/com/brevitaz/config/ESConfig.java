package com.brevitaz.config;

import javafx.scene.NodeBuilder;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.node.Node;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ESConfig {
    @Value("${elasticsearch.port}")
    Integer portNumber;

    @Value("${elasticsearch.host}")
    String host;

    @Value("${elasticsearch.scheme}")
    String scheme;

    private EmbeddedElasticsearchServer eserver;
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


    /*@Override
    public Statement apply(Statement statement, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                eserver = new EmbeddedElasticsearchServer();
                eserver.start();

                esClient = eserver.getClient();
                loader = new ESIndicesLoader(esClient, 1, 1);
                try {
                    base.evaluate(); // execute the unit test
                } finally {
                    eserver.shutdown();
                }
            }
        };
    }*/
}
