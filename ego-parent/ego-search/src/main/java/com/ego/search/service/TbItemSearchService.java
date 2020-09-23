package com.ego.search.service;

import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;
import java.util.Map;

public interface TbItemSearchService {
    void init() throws IOException, SolrServerException;

    Map<String, Object> selByQuery(String query, int page, int rows) throws IOException, SolrServerException;

    int add(Map<String, Object> map, String desc) throws SolrServerException, IOException;
}
