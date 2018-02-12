package com.brevitaz.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ESConfig {

    private RestHighLevelClient esClient;

    public RestHighLevelClient getEsClient() {
        if (esClient == null) {
            this.esClient = new RestHighLevelClient(
                    RestClient.builder(new HttpHost("localhost", 9200, "http")));
        }
        return esClient;
    }
    public void setEsClient(RestHighLevelClient esClient) {
        this.esClient = esClient;
    }
    public ESConfig() {

    }
    /*public void setup() {
        System.out.println("Configuration");

    }*/
}
