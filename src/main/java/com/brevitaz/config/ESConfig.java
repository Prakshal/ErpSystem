package com.brevitaz.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

public class ESConfig {
    public static RestHighLevelClient client;

    private ESConfig() {
    }

    public static RestHighLevelClient getInstance()
    {
        if(client == null)
        {
            client = new RestHighLevelClient(
                    RestClient.builder(
                            new HttpHost("localhost", 9200, "http")));
            return client;
        }
        else
        {
            return client;
        }
    }

}
