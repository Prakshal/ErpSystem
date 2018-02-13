package com.brevitaz.dao.impl;

import com.brevitaz.config.ESConfig;
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
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
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
    private ESConfig esConfig;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public boolean insert(Tenant tenant) throws IOException {
        IndexRequest request = new IndexRequest(
                INDEX_NAME,
                TYPE_NAME,
                tenant.getTenantId());

        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        String json = objectMapper.writeValueAsString(tenant);
        request.source(json, XContentType.JSON);
        IndexResponse response =esConfig.getEsClient().index(request);
        if(response.status()== RestStatus.CREATED) {
            return true;
        }
        else
            return false;
    }

    @Override
    public List<Tenant> tenants() throws IOException {
        List<Tenant> tenants = new ArrayList<>();
        SearchRequest request = new SearchRequest(INDEX_NAME);
        request.types(TYPE_NAME);

        SearchResponse response = esConfig.getEsClient().search(request);

        SearchHit[] hits = response.getHits().getHits();

        Tenant tenant;

        for (SearchHit hit : hits)
        {
            tenant = objectMapper.readValue(hit.getSourceAsString(), Tenant.class);
            tenants.add(tenant);
        }
        return tenants;
    }

    @Override
    public Tenant getById(String tenantId) throws IOException {
        GetRequest getRequest = new GetRequest(
                INDEX_NAME,
                TYPE_NAME,
                tenantId);

        GetResponse response = esConfig.getEsClient().get(getRequest);

        Tenant tenant = objectMapper.readValue(response.getSourceAsString(), Tenant.class);

        if (response.isExists()) {
            return tenant;
        }
        else
        {
            return null;
        }
    }

    @Override
    public boolean update(Tenant tenant, String tenantId) throws IOException {
        UpdateRequest request = new UpdateRequest(
                INDEX_NAME,
                TYPE_NAME,
                tenantId);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        String json=objectMapper.writeValueAsString(tenant);
        request.doc(json,XContentType.JSON);
        UpdateResponse response = esConfig.getEsClient().update(request);
        if(response.status()==RestStatus.OK)
        {
            return true;
        }
        else
            return false;
    }

    @Override
    public List<Tenant> getByName(String tenantName) throws IOException {
        SearchRequest request = new SearchRequest(INDEX_NAME);
        request.types(TYPE_NAME);

        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        sourceBuilder.query(QueryBuilders.boolQuery().must(matchQuery("tenantName", tenantName)));


        request.source(sourceBuilder);

        SearchResponse response = null;
        List<Tenant> tenants=new ArrayList<>();

            response = esConfig.getEsClient().search(request);

            SearchHit[] hits = response.getHits().getHits();

            Tenant tenant=null;
            for (SearchHit hit : hits)
            {
                tenant = objectMapper.readValue(hit.getSourceAsString(), Tenant.class);
                tenants.add(tenant);
            }

            return tenants;
    }

    @Override
    public boolean delete(String tenantId) throws IOException {
        DeleteRequest request = new DeleteRequest(
                INDEX_NAME,
                TYPE_NAME,
                tenantId);

        DeleteResponse response = esConfig.getEsClient().delete(request);

        if(response.status()==RestStatus.OK)
        {
            return true;
        }
        else
            return false;
    }
}