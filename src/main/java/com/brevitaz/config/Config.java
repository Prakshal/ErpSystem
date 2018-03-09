package com.brevitaz.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Value("${elasticsearch.port}")
    private Integer portNumber;

    @Value("${elasticsearch.host}")
    private String host;

    @Value("${elasticsearch.scheme}")
    private String scheme;

    private RestHighLevelClient client;
    private ObjectMapper objectMapper;

    @Bean
    public RestHighLevelClient client() {
        if (client == null) {
            this.client = new RestHighLevelClient(
                    RestClient.builder(new HttpHost(host, portNumber, scheme)).build());
        }
        return client;
    }

    public void setEsClient(RestHighLevelClient esClient) {
        this.client = esClient;
    }
    public Config() {

    }

    @Bean
    public ObjectMapper objectMapper()
    {
        if(objectMapper == null)
        {
            objectMapper = new ObjectMapper();
        }
        return objectMapper;
    }
}