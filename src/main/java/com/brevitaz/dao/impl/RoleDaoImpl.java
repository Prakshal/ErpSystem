package com.brevitaz.dao.impl;

import com.brevitaz.dao.RoleDao;
import com.brevitaz.model.Role;
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
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {

    private static final String TYPE_NAME="doc";
    private static final String INDEX_NAME="role";

    @Autowired
    private RestHighLevelClient client;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public boolean create(Role role){
        IndexRequest request = new IndexRequest(
                INDEX_NAME,
                TYPE_NAME,
                role.getId());
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        try {
            String json = objectMapper.writeValueAsString(role);
            request.source(json, XContentType.JSON);
            IndexResponse response = client.index(request);
            if (response.status() == RestStatus.OK) {
                return true;
            } else {
                return false;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Role> getAll(){
        List<Role> roles = new ArrayList<>();
        SearchRequest request = new SearchRequest(INDEX_NAME);
        request.types(TYPE_NAME);
        try {
            SearchResponse searchResponse = client.search(request);
            SearchHit[] hits = searchResponse.getHits().getHits();
            Role role;
            for (SearchHit hit : hits) {
                role = objectMapper.readValue(hit.getSourceAsString(), Role.class);
                roles.add(role);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return roles;
    }

    @Override
    public Role getById(String id){
        GetRequest request = new GetRequest(
                INDEX_NAME,
                TYPE_NAME,
                id);

        try {

            GetResponse response = client.get(request);
            Role role = objectMapper.readValue(response.getSourceAsString(), Role.class);
            if (response.isExists()) {
                return role;
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
    public boolean delete(String id) {

        DeleteRequest request = new DeleteRequest(
                INDEX_NAME,
                TYPE_NAME,
                id);
        try {
            DeleteResponse response = client.delete(request);
            if (response.status() == RestStatus.NOT_FOUND) {
                return true;
            } else {
                return false;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }
}

