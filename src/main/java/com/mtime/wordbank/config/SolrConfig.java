package com.mtime.wordbank.config;

import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Mtime on 2016/5/3.
 */
@Configuration
public class SolrConfig {

    @Bean
    public CloudSolrClient CloudSolrClient(){
        CloudSolrClient.Builder builder = new CloudSolrClient.Builder();

        Collection<String> zklist = new ArrayList<>();
        zklist.add("192.168.50.243:2181");
        CloudSolrClient cloudSolrClient = builder.withZkHost(zklist).build();
        return cloudSolrClient;
    }
}
