package com.brevitaz.dao.impl;

import com.brevitaz.dao.EmployeeDao;
import com.brevitaz.model.Employee;
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
import java.util.Base64;
import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.matchQuery;

@Repository()
public class EmplyeeDaoImpl implements EmployeeDao
{

    private static final String TYPE_NAME="doc";
    private static final String INDEX_NAME="employee";


    @Autowired
    private RestHighLevelClient client;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public boolean insert(Employee employee){
        IndexRequest request = new IndexRequest(
                INDEX_NAME,
                TYPE_NAME,
                employee.getId());
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        try {
            employee.setPassword(Base64.getEncoder().encodeToString(employee.getPassword().getBytes()));
            String json = objectMapper.writeValueAsString(employee);
            request.source(json, XContentType.JSON);
            IndexResponse response = client.index(request);
            if(response.status()== RestStatus.OK) {
                return true;
            }
            else
            {
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
    public List<Employee> getAll(){
        List<Employee> employees = new ArrayList<>();
        SearchRequest request = new SearchRequest(INDEX_NAME);
        request.types(TYPE_NAME);

        try {
            SearchResponse response = client.search(request);
            SearchHit[] hits = response.getHits().getHits();
            Employee employee;

            for (SearchHit hit : hits) {
                employee = objectMapper.readValue(hit.getSourceAsString(), Employee.class);
                employees.add(employee);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return employees;
    }

    @Override
    public boolean update(Employee employee, String id){
        UpdateRequest request = new UpdateRequest(
                INDEX_NAME,
                TYPE_NAME,
                id);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        try {

            String json = objectMapper.writeValueAsString(employee);
            request.doc(json, XContentType.JSON);
            UpdateResponse response = client.update(request);
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
    public boolean delete(String id){
        DeleteRequest request = new DeleteRequest(
                INDEX_NAME,
                TYPE_NAME,
                id);

        try {

            DeleteResponse response = client.delete(request);
            response.status();
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

    @Override
    public Employee getById(String id){
        GetRequest request = new GetRequest(
                INDEX_NAME,
                TYPE_NAME,
                id);

        try {

            GetResponse response = client.get(request);
            Employee employee = objectMapper.readValue(response.getSourceAsString(), Employee.class);
            if (response.isExists()) {
                return employee;
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
    public Employee getByUsernameAndPassword(String username, String password){
        SearchRequest request = new SearchRequest(INDEX_NAME);
        request.types(TYPE_NAME);

        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.boolQuery()
                            .must(matchQuery("emailId.keyword", username))
                            .must(matchQuery("password.keyword", password)));
        request.source(sourceBuilder);

        try {
            SearchResponse response = client.search(request);
            SearchHit[] hits = response.getHits().getHits();
            Employee employee = null;

            for (SearchHit hit : hits)
                employee = objectMapper.readValue(hit.getSourceAsString(), Employee.class);
            return employee;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}