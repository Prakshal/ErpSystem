package com.brevitaz.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
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
}
