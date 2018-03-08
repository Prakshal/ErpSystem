package com.brevitaz.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.settings.Settings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.allegro.tech.embeddedelasticsearch.EmbeddedElastic;
import pl.allegro.tech.embeddedelasticsearch.PopularProperties;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Configuration
public class ESConfig {
    @Value("${elasticsearch.port}")
    private Integer portNumber;

    @Value("${elasticsearch.host}")
    private String host;

    @Value("${elasticsearch.scheme}")
    private String scheme;

    @Value("${elasticsearch.clusterName}")
    private String clusterName;

    private RestHighLevelClient client;
    private EmbeddedElastic embeddedElastic;

    public void setEsClient(RestHighLevelClient client) {
        this.client = client;
    }
    public ESConfig() {

    }

    @Bean
    public RestHighLevelClient client() throws Exception {

        if(client==null)
        {

            client = new RestHighLevelClient(
                    RestClient.builder(new HttpHost(host, portNumber, scheme)).build());

        }
        return client;
    }

    @PostConstruct
    public void startES() throws Exception {
              /*Settings settings = Settings.builder()
                .put(PopularProperties.CLUSTER_NAME, clusterName)
                .build();*/
        try {
            embeddedElastic = EmbeddedElastic.builder()
                    .withElasticVersion("5.6.0")
//                    .withEsJavaOpts("-Xms1g -Xmx1g")
//                    .withSetting(PopularProperties.TRANSPORT_TCP_PORT, 9300)
                    .withSetting(PopularProperties.HTTP_PORT, portNumber)
                    .withSetting(PopularProperties.CLUSTER_NAME, clusterName)
                    .withStartTimeout(60, TimeUnit.SECONDS)
                    .build()
                    .start();
            System.out.println("Es started");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}