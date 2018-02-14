package com.brevitaz.dao.impl;

import com.brevitaz.config.ESConfig;
import com.brevitaz.dao.RightDao;
import com.brevitaz.model.Right;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
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
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RightDaoImpl implements RightDao {

    public static final String INDEX_NAME = "right";
    public static final String TYPE_NAME = "doc";

    @Autowired
    private ESConfig esConfig;

    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public boolean insert(Right right) throws IOException {
        IndexRequest request = new IndexRequest(
                INDEX_NAME,
                TYPE_NAME,
                right.getId());
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            String json = objectMapper.writeValueAsString(right);
            request.source(json, XContentType.JSON);
            IndexResponse response = esConfig.getEsClient().index(request);
            if (response.status() == RestStatus.CREATED)
                return true;
            else
                return false;
    }

    @Override
    public List<Right> getAll() throws IOException {
        List<Right> rights = new ArrayList<>();
        SearchRequest request = new SearchRequest(INDEX_NAME);
        request.types(TYPE_NAME);
            SearchResponse response = esConfig.getEsClient().search(request);
            SearchHit[] hits = response.getHits().getHits();
            Right right;
            for (SearchHit hit : hits)
            {
                right=objectMapper.readValue(hit.getSourceAsString(),Right.class);
                rights.add(right);
            }
        return rights;
    }

    @Override
    public boolean update(Right right, String id) throws IOException {
        UpdateRequest request = new UpdateRequest(
                INDEX_NAME,
                TYPE_NAME,
                id
        );
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        String json = null;
            json = objectMapper.writeValueAsString(right);
            request.doc(json, XContentType.JSON);
            UpdateResponse response = esConfig.getEsClient().update(request);
            if (response.status() == RestStatus.OK)
                return true;
            else
                return false;
    }

    @Override
    public boolean delete(String id) throws IOException {
        DeleteRequest request = new DeleteRequest(
                INDEX_NAME,
                TYPE_NAME,
                id
        );
        DeleteResponse response = esConfig.getEsClient().delete(request);
        if (response.status()==RestStatus.OK)
            return true;
        else
            return false;
    }

    @Override
    public Right getById(String id) throws IOException {
        GetRequest request = new GetRequest(
                INDEX_NAME,
                TYPE_NAME,
                id
        );
        GetResponse response = esConfig.getEsClient().get(request);
        Right right = objectMapper.readValue(response.getSourceAsString(),Right.class);
        if(response.isExists())
            return right;
        else
            return null;
    }
}
