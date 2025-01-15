package kr.hhplus.be.server.Filter;

import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

@Slf4j
public class HttpLogMessage {
    private String httpMethod;
    private String requestUri;
    private HttpStatus httpStatus;
    private String clientIp;
    private Map<String, String> headers;
    private String requestBody;
    private String responseBody;

    public HttpLogMessage(String httpMethod, String requestUri, HttpStatus httpStatus, String clientIp,
                          Map<String, String> headers,
                          String requestBody, String responseBody) {
        this.httpMethod = httpMethod;
        this.requestUri = requestUri;
        this.httpStatus = httpStatus;
        this.clientIp = clientIp;
        this.headers = headers;
        this.requestBody = requestBody;
        this.responseBody = responseBody;
    }

    public static HttpLogMessage createInstance(
            ContentCachingRequestWrapper requestWrapper,
            ContentCachingResponseWrapper responseWrapper
    ) throws IOException {

        String httpMethod = requestWrapper.getMethod();
        String requestUri = requestWrapper.getRequestURI();
        HttpStatus httpStatus = HttpStatus.valueOf(responseWrapper.getStatus());
        String clientIp = getClientIp(requestWrapper);
        Map<String, String> headers = getRequestHeaders(requestWrapper);
        String requestBody = getRequestBody(requestWrapper);
        String responseBody = getResponseBody(responseWrapper);

        return new HttpLogMessage(
                httpMethod,
                requestUri,
                httpStatus,
                clientIp,
                headers,
                requestBody,
                responseBody
        );
    }

    // 클라이언트 IP
    private static String getClientIp(HttpServletRequest request) {
        String clientIp = request.getHeader("X-Forwarded-For");
        if (clientIp == null) {
            clientIp = request.getRemoteAddr();
        }
        return clientIp;
    }

    // 요청 헤더
    private static Map<String, String> getRequestHeaders(HttpServletRequest request) {
        Map<String, String> headers = new HashMap<>();
        Enumeration<String> headerNames = request.getHeaderNames();
        while(headerNames.hasMoreElements()){
            String key = headerNames.nextElement();
            headers.put(key, request.getHeader(key));
        }
        return headers;
    }

    // 요청 body
    private static String getRequestBody(ContentCachingRequestWrapper request) {
        byte[] buf = request.getContentAsByteArray();
        if (buf.length > 0) {
            return new String(buf, StandardCharsets.UTF_8).replaceAll("\\s+", " ");
        }
        return "";
    }

    // 응답 body
    private static String getResponseBody(ContentCachingResponseWrapper response) throws IOException {
        byte[] buf = response.getContentAsByteArray();
        if (buf.length > 0) {
            return new String(buf, StandardCharsets.UTF_8).replaceAll("\\s+", " ");
        }
        return "";
    }


    // 로그 메시지 생성
    public String toPrettierLog() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n")
                .append("[REQUEST] ").append(this.httpMethod).append(" ").append(this.requestUri)
                .append(" ").append(this.httpStatus)
                .append("\n").append(">> CLIENT_IP: ").append(this.clientIp)
                .append("\n").append(">> HEADERS: ").append(this.headers)
                .append("\n").append(">> REQUEST_BODY: ").append(this.requestBody)
                .append("\n").append(">> RESPONSE_BODY: ").append(this.responseBody);
        return sb.toString();
    }

}
