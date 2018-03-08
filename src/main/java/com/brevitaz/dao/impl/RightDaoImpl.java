package com.brevitaz.dao.impl;

import com.brevitaz.dao.RightDao;
import com.brevitaz.model.Right;
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
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RightDaoImpl implements RightDao {

    public static final String INDEX_NAME = "right";
    public static final String TYPE_NAME = "doc";

    @Autowired
    private RestHighLevelClient client;

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public boolean insert(Right right){
        IndexRequest request = new IndexRequest(
                INDEX_NAME,
                TYPE_NAME,
                right.getId());
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            try {
                String json = objectMapper.writeValueAsString(right);
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
    public List<Right> getAll() {
        List<Right> rights = new ArrayList<>();
        SearchRequest request = new SearchRequest(INDEX_NAME);
        request.types(TYPE_NAME);
        try {
            SearchResponse response = client.search(request);
            SearchHit[] hits = response.getHits().getHits();
            Right right;
            for (SearchHit hit : hits) {
                right = objectMapper.readValue(hit.getSourceAsString(), Right.class);
                rights.add(right);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return rights;
    }

    @Override
    public boolean update(Right right, String id){
        UpdateRequest request = new UpdateRequest(
                INDEX_NAME,
                TYPE_NAME,
                id
        );
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        String json = null;
        try {
            json = objectMapper.writeValueAsString(right);
            request.doc(json, XContentType.JSON);
            UpdateResponse response = client.update(request);
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
    public boolean delete(String id) {
        DeleteRequest request = new DeleteRequest(
                INDEX_NAME,
                TYPE_NAME,
                id
        );
        try {
            DeleteResponse response = client.delete(request);
            if (response.status() == RestStatus.NOT_FOUND)
                return true;
            else
                return false;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Right getById(String id) {
        GetRequest request = new GetRequest(
                INDEX_NAME,
                TYPE_NAME,
                id
        );
        try {
            GetResponse response = client.get(request);
            Right right = objectMapper.readValue(response.getSourceAsString(), Right.class);
            if (response.isExists())
                return right;
            else
                return null;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
