package util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class HttpRequester {
    static CloseableHttpClient httpClient = HttpClients.createDefault();

    String url = null;
    Method method = Method.GET;
    String postBody = null;
    Map<String, Object> params = new HashMap<>();
    Map<String, Object> headers = new HashMap<>();

    public static HttpRequester newRequest() {
        return new HttpRequester();
    }

    public HttpRequester setHeader(String key, Object value) {
        headers.put(key, value);
        return this;
    }

    public HttpRequester setParams(String key, Object value) {
        params.put(key, value);
        return this;
    }

    public HttpRequester setUrl(String url) {
        this.url = url;
        return this;
    }

    public HttpRequester setMethod(Method method) {
        this.method = method;
        return this;
    }

    public HttpRequester setPostBody(String body) {
        this.postBody = body;
        return this;
    }

    public enum Method {
        GET,
        POST
    }

    /**
     * 下载数据
     */
    public byte[] downloadBytes() {

        // 构造完整url
        String paramPart = String.join("&", params.entrySet()
            .stream()
            .map((x) -> x.getKey() + "=" + x.getValue().toString())
            .collect(Collectors.toList()));
        if (!StringUtils.isBlank(paramPart)) {
            url += '?' + paramPart;
        }

        HttpRequestBase httpRequest = method == Method.GET ? new HttpGet(url) : new HttpPost(url);

        // 设置Header
        headers.entrySet()
            .forEach(x -> httpRequest.setHeader(x.getKey(), x.getValue().toString()));

        // 设置Post body
        if (method == Method.POST && postBody != null) {
            ((HttpPost)httpRequest).setEntity(new StringEntity(postBody, "utf-8"));
        }

        return execute(httpRequest);
    }

    /**
     * 下载文本内容
     */
    public String downloadString() {
        byte[] bytes = downloadBytes();
        String content = new String(bytes);
        return content;
    }

    /**
     * 执行Http请求
     */
    private byte[] execute(HttpUriRequest httpUriRequest) {
        try {
            try (CloseableHttpResponse response = httpClient.execute(httpUriRequest)) {
                HttpEntity entity = response.getEntity();
                int code = response.getStatusLine().getStatusCode();
                if (entity != null && code == 200) {
                    // 读取结果
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    entity.writeTo(stream);
                    return stream.toByteArray();
                }

                String msg = String.format("Failed pulling %s, http code: %d", httpUriRequest.getURI(), code);
                throw new IOException(msg);
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
