package com.brevitaz.dao.impl;

import com.brevitaz.config.ESConfig;
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
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.termQuery;

@Repository
public class EmplyeeDaoImpl implements EmployeeDao
{

    private static final String TYPE_NAME="doc";
    private static final String INDEX_NAME="employee";

    @Autowired
    private ESConfig esConfig;

    private ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public boolean insert(Employee employee)
    {
        IndexRequest request = new IndexRequest(
                INDEX_NAME,
                TYPE_NAME,
                employee.getEmployeeId());
        try
        {
            String json = objectMapper.writeValueAsString(employee);
            request.source(json, XContentType.JSON);
            IndexResponse indexResponse =esConfig.getEsClient().index(request);
            System.out.println(indexResponse);
            if(indexResponse.status()== RestStatus.CREATED) {
                return true;
            }
            else
                return false;
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Employee> getAll()
    {
        List<Employee> students = new ArrayList<>();
        SearchRequest searchRequest = new SearchRequest(INDEX_NAME);
        searchRequest.types(TYPE_NAME);

        try {
            SearchResponse searchResponse = esConfig.getEsClient().search(searchRequest);

            System.out.println(searchResponse);

            SearchHit[] hits = searchResponse.getHits().getHits();

            Employee employee;

            for (SearchHit hit : hits)
            {
                employee = objectMapper.readValue(hit.getSourceAsString(), Employee.class);
                students.add(employee);
                System.out.println(employee);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return students;
    }

    @Override
    public boolean update(Employee employee, String employeeId) throws IOException {
        UpdateRequest request = new UpdateRequest(
                INDEX_NAME,
                TYPE_NAME,
                employeeId);


        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        String json=objectMapper.writeValueAsString(employee);
        request.doc(json,XContentType.JSON);

        UpdateResponse updateResponse = esConfig.getEsClient().update(request);

        System.out.println(updateResponse.status());
        if(updateResponse.status()==RestStatus.OK)
        {
            System.out.println("Prakshal");
            return true;
        }
        else {
            System.out.println("Doshi");
            return false;
        }
    }

    @Override
    public boolean delete(String employeeId)
    {
        DeleteRequest request = new DeleteRequest(
                INDEX_NAME,
                TYPE_NAME,
                employeeId);

        DeleteResponse deleteResponse = null;
        try {
            deleteResponse = esConfig.getEsClient().delete(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(deleteResponse.status()==RestStatus.OK)
        {
            return true;
        }
        else
            return false;
    }

    @Override
    public Employee getById(String employeeId)
    {
        GetRequest getRequest = new GetRequest(
                INDEX_NAME,
                TYPE_NAME,
                employeeId);

        GetResponse getResponse = null;
        Employee employee=null;
        {
            try {
                getResponse = esConfig.getEsClient().get(getRequest);

                employee = objectMapper.readValue(getResponse.getSourceAsString(), Employee.class);
                System.out.println(getResponse.isExists());
                System.out.println(employee);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        if (getResponse.isExists()) {
            return employee;
        }
        else
        {
            return null;
        }
    }
}