package com.mtime.wordbank.solr;

import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Mtime on 2016/12/19.
 */
@Service
public class SolrOperateDemo {
    @Autowired
    private CloudSolrClient cloudSolrClient;

    public void create(){
        //cloudSolrClient.commit()
        //cloudSolrClient.query()

    }

}
