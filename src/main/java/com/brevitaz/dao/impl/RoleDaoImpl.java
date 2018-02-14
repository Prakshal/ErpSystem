package com.brevitaz.dao.impl;

import com.brevitaz.config.ESConfig;
import com.brevitaz.dao.RoleDao;
import com.brevitaz.model.Role;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {

    private static final String TYPE_NAME="doc";
    private static final String INDEX_NAME="role";

    @Autowired
    private ESConfig esConfig;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public boolean create(Role role) throws IOException {
        IndexRequest request = new IndexRequest(
                INDEX_NAME,
                TYPE_NAME,
                role.getId());
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        String json = objectMapper.writeValueAsString(role);
        request.source(json, XContentType.JSON);
        IndexResponse response = esConfig.getEsClient().index(request);
        if (response.status()== RestStatus.OK)
        {
            return true;
        }
        else
        {
            return false;
        }

    }

    @Override
    public List<Role> get() throws IOException {
        List<Role> roles = new ArrayList<>();
        SearchRequest request = new SearchRequest(INDEX_NAME);
        request.types(TYPE_NAME);
        SearchResponse searchResponse = esConfig.getEsClient().search(request);
        SearchHit[] hits = searchResponse.getHits().getHits();
        Role role;
        for (SearchHit hit : hits) {
            role = objectMapper.readValue(hit.getSourceAsString(), Role.class);
            roles.add(role);
        }
        return roles;
    }

    @Override
    public boolean delete(String id) throws IOException {

        DeleteRequest request = new DeleteRequest(
                INDEX_NAME,
                TYPE_NAME,
                id);
        DeleteResponse response = esConfig.getEsClient().delete(request);
        if(response.status()==RestStatus.NOT_FOUND)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}

