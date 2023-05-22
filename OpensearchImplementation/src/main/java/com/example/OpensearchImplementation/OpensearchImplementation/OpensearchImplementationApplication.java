package com.example.OpensearchImplementation.OpensearchImplementation;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.opensearch.action.admin.indices.delete.DeleteIndexRequest;
import org.opensearch.action.delete.DeleteRequest;
import org.opensearch.action.delete.DeleteResponse;
import org.opensearch.action.get.GetRequest;
import org.opensearch.action.get.GetResponse;
import org.opensearch.action.index.IndexRequest;
import org.opensearch.action.index.IndexResponse;
import org.opensearch.action.support.master.AcknowledgedResponse;
import org.opensearch.client.RequestOptions;
import org.opensearch.client.RestClient;
import org.opensearch.client.RestClientBuilder;
import org.opensearch.client.RestHighLevelClient;
import org.opensearch.client.indices.CreateIndexRequest;
import org.opensearch.client.indices.CreateIndexResponse;
import org.opensearch.common.settings.Settings;
import org.opensearch.common.xcontent.XContentType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchProperties.Restclient;

import java.io.IOException;
import java.util.HashMap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class OpensearchImplementationApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(OpensearchImplementationApplication.class, args);
	
        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();

        credentialsProvider.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials("admin", "admin"));

        //Create a client.
        RestClientBuilder builder = RestClient.builder(new HttpHost("localhost", 9200, "https"));
        RestHighLevelClient client = new RestHighLevelClient(builder);

        //Create a non-default index with custom settings and mappings.
        CreateIndexRequest createIndexRequest = new CreateIndexRequest("custom-index");

        createIndexRequest.settings(Settings.builder() //Specify in the settings how many shards you want in the index.
                .put("index.number_of_shards", 4)
                .put("index.number_of_replicas", 3)
        );
    
	Employee emp = new Employee("Mohit", "Srivastava");
	 
	IndexRequest indexRequest = new IndexRequest("sampleIndex");
	indexRequest.id("003");
	indexRequest.source(new ObjectMapper().writeValueAsString(emp), XContentType.JSON);
	IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
	System.out.println("response id: "+indexResponse.getId());
	System.out.println("response name: "+indexResponse.getResult().name());
	
	}
}
