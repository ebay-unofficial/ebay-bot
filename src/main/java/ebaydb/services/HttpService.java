package ebaydb.services;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class HttpService {

    @Value("${ebay.api.url}")
    private String baseUrl;

    public HttpService() {

    }

    public JsonObject httpGetJson(String url) {
        return new JsonParser().parse(httpGet(url)).getAsJsonObject();
    }

    public String httpGet(String url) {
        HttpClient httpClient = HttpClientBuilder.create().build();
        try {
            return IOUtils.toString(httpClient.execute(new HttpGet(baseUrl + url)).getEntity().getContent());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
