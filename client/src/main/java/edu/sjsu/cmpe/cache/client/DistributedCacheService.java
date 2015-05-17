package edu.sjsu.cmpe.cache.client;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class DistributedCacheService implements CacheServiceInterface {
    private final String cacheInstanceServerUrl;

    public DistributedCacheService(String serverUrl) {
        this.cacheInstanceServerUrl = serverUrl;
    }
    
    
    @Override
    public void put(long key, String value) {
        HttpResponse<JsonNode> response = null;
        try {
            response = Unirest
            .put(this.cacheInstanceServerUrl + "/cache/{key}/{value}")
            .header("accept", "application/json")
            .routeParam("key", Long.toString(key))
            .routeParam("value", value).asJson();
        } catch (UnirestException e) {
            System.err.println(e);
        }
        
        if (response.getCode() != 200) {
            System.out.println("Failed to add to the cache.");
            

    @Override
    public String get(long key) {
        HttpResponse<JsonNode> response = null;
        try {
            response = Unirest.get(this.cacheInstanceServerUrl + "/cache/{key}")
                    .header("accept", "application/json")
                    .routeParam("key", Long.toString(key)).asJson();
        } catch (UnirestException e) {
            System.err.println(e);
        }
        String value = response.getBody().getObject().getString("value");

        return value;
    }

   
 }
    }
}
