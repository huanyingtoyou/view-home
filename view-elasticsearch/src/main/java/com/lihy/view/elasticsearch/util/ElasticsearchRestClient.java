package com.lihy.view.elasticsearch.util;

import com.lihy.view.common.constant.SystemConstant;
import com.lihy.view.common.util.ResponseResult;
import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.admin.indices.get.GetIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author lihy
 * @date 2018/10/31
 */
@Service
public class ElasticsearchRestClient {
    private static Logger logger = LoggerFactory.getLogger(ElasticsearchRestClient.class);

    public RestHighLevelClient getRestClient() {

        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http")));
        return client;
    }

    /**
     * 初始化索引
     *
     * @param indexName
     * @param typeName
     * @param shardNum
     * @param replicNum
     * @param builder
     * @return
     */

    public Boolean initIndex(String indexName,
                             String typeName,
                             int shardNum,
                             int replicNum,
                             XContentBuilder builder) {
        //创建索引
        RestHighLevelClient client = getRestClient();
        CreateIndexRequest request = new CreateIndexRequest(indexName);

        //设置分片和副本
        request.settings(Settings.builder()
                .put("index.number_of_shards", shardNum)
                .put("index.number_of_replicas", replicNum)
        );

        request.mapping(typeName, builder);
        CreateIndexResponse createIndexResponse = null;
        try {
            createIndexResponse = client.indices().create(request);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return createIndexResponse.isAcknowledged();
    }

    /**
     * @param indexName
     * @param typeName
     * @param jsonString
     * @return
     */

    public boolean indexNews(String indexName,
                            String typeName,
                            String id,
                            String jsonString) {

        RestHighLevelClient client = getRestClient();
        IndexRequest indexRequest = new IndexRequest(indexName, typeName, id)
                .source(jsonString, XContentType.JSON);
        try {
            IndexResponse indexResponse = client.index(indexRequest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }


    /**
     * 判断索引是否存在
     *
     * @param indexName
     * @return
     */
    public boolean existIndex(String indexName) {
        GetIndexRequest request = new GetIndexRequest();
        request.indices(indexName);
        try {
            boolean exists = getRestClient().indices().exists(request);
            return exists;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除索引
     *
     * @param indexName
     * @return
     */
    public boolean deleteIndex(String indexName) {
        try {
            if (existIndex(indexName)) {
                DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest(indexName);
                DeleteIndexResponse deleteIndexResponse = getRestClient().indices().delete(deleteIndexRequest);
                return deleteIndexResponse.isAcknowledged();
            } else {
                logger.info("索引不存在");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * 批量索引文档
     *
     * @param indexName
     * @param typeName
     * @param newList
     * @return
     */

    public boolean indexNews(String indexName,
                            String typeName,
                            List newList) {
        RestHighLevelClient client = getRestClient();
        BulkRequest bulkRequest = new BulkRequest();
        Iterator<String> iter = newList.iterator();
        while (iter.hasNext()) {
            String jsonString = iter.next();
            IndexRequest indexRequest = new IndexRequest(indexName, typeName)
                    .source(jsonString, XContentType.JSON);
            bulkRequest.add(indexRequest);
        }
        try {
            BulkResponse bulkResponse = client.bulk(bulkRequest);
            if (bulkResponse.hasFailures()) {
                logger.error("批量索引失败");
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }


    /**
     * 搜索文档
     *
     * @param indics
     * @param keyword
     * @param fieldNames
     * @param pageNum
     * @param pageSize
     * @return
     */
    public ResponseResult<Map> searchNews(String indics,
                                     String keyword,
                                     String[] fieldNames,
                                     int pageNum,
                                     int pageSize) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        long start = System.currentTimeMillis();
        ResponseResult<Map> responseResult = new ResponseResult<>();
        SearchRequest searchRequest = new SearchRequest(indics);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        MultiMatchQueryBuilder multiMatchQuery = QueryBuilders
                .multiMatchQuery(keyword, fieldNames)
                .operator(Operator.AND);

        HighlightBuilder highlightBuilder = new HighlightBuilder();
        HighlightBuilder.Field highlightTitle = new HighlightBuilder.Field("title");
        highlightBuilder.field(highlightTitle);
        HighlightBuilder.Field highlightContent = new HighlightBuilder.Field("content");
        highlightBuilder.field(highlightContent);

        highlightBuilder.preTags("<span style=color:red>").postTags("</span>");
        searchSourceBuilder.highlighter(highlightBuilder);
        searchSourceBuilder.query(multiMatchQuery);
        searchSourceBuilder.from((pageNum - 1) * pageSize);
        searchSourceBuilder.size(pageSize);
        searchRequest.source(searchSourceBuilder);
        ArrayList<Map<String, Object>> resultList = new ArrayList<>();

        try {
            SearchResponse searchResponse = getRestClient()
                    .search(searchRequest);
            SearchHits hits = searchResponse.getHits();
            long totalHits = hits.getTotalHits();
            SearchHit[] searchHits = hits.getHits();
            for (SearchHit hit : searchHits) {

                Map<String, Object> sourceAsMap = hit.getSourceAsMap();
                String postDate = sdf.format(sourceAsMap.get("postdate"));
                Map<String, HighlightField> highlightFields = hit.getHighlightFields();
                HighlightField hTitle = highlightFields.get("title");

                if (hTitle != null) {
                    String hTitleText = "";
                    Text[] fragments = hTitle.fragments();
                    for (Text text : fragments) {
                        hTitleText += text;
                    }
                    sourceAsMap.put("title", hTitleText);
                }
                HighlightField highlightField = highlightFields.get("content");
                if (highlightField != null) {
                    String highlightFieldText = "";
                    Text[] fragments = highlightField.fragments();
                    for (Text text : fragments) {
                        highlightFieldText += text;
                    }
                    sourceAsMap.put("content", postDate + " - " + highlightFieldText);
                }
                resultList.add(sourceAsMap);
            }
            long end = System.currentTimeMillis();
            Map map = new HashMap();
            map.put("totalHits", String.valueOf(totalHits));
            map.put("totalTime", String.valueOf(end - start));
            map.put("resultList", resultList);
            responseResult.setData(map);
            responseResult.setResponseCode(SystemConstant.SUCCESS.getCode());
            responseResult.setResponseMsg(SystemConstant.SUCCESS.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseResult;
    }
}
