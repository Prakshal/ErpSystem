package com.brevitaz.dao.impl;

import com.brevitaz.config.ESConfig;
import com.brevitaz.dao.EmployeeDao;
import com.brevitaz.model.Employee;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
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
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.termQuery;

@Repository()
public class EmplyeeDaoImpl implements EmployeeDao
{

    private static final String TYPE_NAME="doc";
    private static final String INDEX_NAME="employee";

    @Autowired
    private ESConfig esConfig;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public boolean insert(Employee employee) throws IOException {
        IndexRequest request = new IndexRequest(
                INDEX_NAME,
                TYPE_NAME,
                employee.getEmployeeId());
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        String json = objectMapper.writeValueAsString(employee);
        request.source(json, XContentType.JSON);
        IndexResponse response =esConfig.getEsClient().index(request);
        if(response.status()== RestStatus.CREATED) {
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public List<Employee> getAll() throws IOException {
        List<Employee> employees = new ArrayList<>();
        SearchRequest request = new SearchRequest(INDEX_NAME);
        request.types(TYPE_NAME);

        SearchResponse response = esConfig.getEsClient().search(request);
        SearchHit[] hits = response.getHits().getHits();
        Employee employee;

        for (SearchHit hit : hits)
        {
            employee = objectMapper.readValue(hit.getSourceAsString(), Employee.class);
            employees.add(employee);
        }
        return employees;
    }

    @Override
    public boolean update(Employee employee, String employeeId) throws IOException {
        UpdateRequest request = new UpdateRequest(
                INDEX_NAME,
                TYPE_NAME,
                employeeId);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        String json = objectMapper.writeValueAsString(employee);
        request.doc(json, XContentType.JSON);
        UpdateResponse response = esConfig.getEsClient().update(request);
        if (response.status() == RestStatus.OK)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public boolean delete(String employeeId) throws IOException {
        DeleteRequest request = new DeleteRequest(
                INDEX_NAME,
                TYPE_NAME,
                employeeId);

        DeleteResponse response = null;
        response = esConfig.getEsClient().delete(request);
        if(response.status()==RestStatus.OK)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public Employee getById(String employeeId) throws IOException {
        GetRequest request = new GetRequest(
                INDEX_NAME,
                TYPE_NAME,
                employeeId);

        GetResponse response = null;
        Employee employee=null;
        {
            response = esConfig.getEsClient().get(request);
            employee = objectMapper.readValue(response.getSourceAsString(), Employee.class);
        }
        if (response.isExists()) {
            return employee;
        }
        else
        {
            return null;
        }
    }
}