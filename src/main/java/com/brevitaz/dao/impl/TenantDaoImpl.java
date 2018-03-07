package com.brevitaz.dao.impl;

import com.brevitaz.dao.TenantDao;
import com.brevitaz.model.Tenant;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.matchQuery;
import static org.elasticsearch.index.query.QueryBuilders.termQuery;

@Repository
public class TenantDaoImpl implements TenantDao
{
    private static final String TYPE_NAME="doc";
    private static final String INDEX_NAME="tenant";

    @Autowired
    private RestHighLevelClient client;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public boolean insert(Tenant tenant){
        IndexRequest request = new IndexRequest(
                INDEX_NAME,
                TYPE_NAME,
                tenant.getId());

        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        try {
            String json = objectMapper.writeValueAsString(tenant);
            request.source(json, XContentType.JSON);
            IndexResponse response = client.index(request);
            if (response.status() == RestStatus.OK)
                return true;

            else
                return false;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Tenant> getAll() {
        List<Tenant> tenants = new ArrayList<>();
        SearchRequest request = new SearchRequest(INDEX_NAME);
        request.types(TYPE_NAME);

        try {
            SearchResponse response = client.search(request);

            SearchHit[] hits = response.getHits().getHits();

            Tenant tenant;

            for (SearchHit hit : hits) {
                tenant = objectMapper.readValue(hit.getSourceAsString(), Tenant.class);
                tenants.add(tenant);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return tenants;
    }

    @Override
    public Tenant getById(String id) {
        GetRequest getRequest = new GetRequest(
                INDEX_NAME,
                TYPE_NAME,
                id);
        try {
            GetResponse response = client.get(getRequest);

            Tenant tenant = objectMapper.readValue(response.getSourceAsString(), Tenant.class);

            if (response.isExists()) {
                return tenant;
            } else {
                return null;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(Tenant tenant, String id){
        UpdateRequest request = new UpdateRequest(
                INDEX_NAME,
                TYPE_NAME,
                id);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        try {
            String json = objectMapper.writeValueAsString(tenant);
            request.doc(json, XContentType.JSON);
            UpdateResponse response = client.update(request);
            System.out.println(response.status());
            if (response.status() == RestStatus.OK) {
                return true;
            } else
                return false;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Tenant> getByName(String name) {
        SearchRequest request = new SearchRequest(INDEX_NAME);
        request.types(TYPE_NAME);

        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        sourceBuilder.query(QueryBuilders.boolQuery().must(matchQuery("name", name)));
        request.source(sourceBuilder);

        SearchResponse response;
        List<Tenant> tenants=new ArrayList<>();
        try {
            response = client.search(request);

            SearchHit[] hits = response.getHits().getHits();

            Tenant tenant;
            for (SearchHit hit : hits) {
                tenant = objectMapper.readValue(hit.getSourceAsString(), Tenant.class);
                tenants.add(tenant);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
            return tenants;
    }

    @Override
    public boolean delete(String id) {
        DeleteRequest request = new DeleteRequest(
                INDEX_NAME,
                TYPE_NAME,
                id);
        try {
            DeleteResponse response = client.delete(request);
            if (response.status() == RestStatus.NOT_FOUND) {
                return true;
            } else
                return false;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }
}