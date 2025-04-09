package zxf.springboot.service.c.support.retrofit;

import lombok.extern.slf4j.Slf4j;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.function.Consumer;

@Slf4j
public class LoggingRequestInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        try {
            Response response = chain.proceed(request);
            boolean isError = !response.isSuccessful();
            if (isError || log.isDebugEnabled()) {
                logRequest(request, isError ? log::error : log::debug);
                logResponse(response, isError ? log::error : log::debug);
            }
            return response;

        } catch (Exception ex) {
            log.error("Exception when seng request", ex);
            logRequest(request, log::error);
            throw ex;
        }
    }

    private void logRequest(Request request, Consumer<String> logger) {
        logger.accept("=================================================Request begin=================================================");
        logger.accept("URI             : " + request.url());
        logger.accept("Methed          : " + request.method());
        logger.accept("Headers         : " + removeSensitiveHeaders(request.headers()));
        logger.accept("Request Body    : " + request.body());
        logger.accept("=================================================Request end=================================================");
    }

    private void logResponse(Response response, Consumer<String> logger) throws IOException {
        logger.accept("=================================================Response begin=================================================");
        logger.accept("Status code     : " + response.code());
        logger.accept("Headers         : " + response.headers());
        logger.accept("Response Body   : " + response.body());
        logger.accept("=================================================Response end=================================================");
    }

    private Headers removeSensitiveHeaders(Headers originalHttpHeaders) {
        //TODO:
        return originalHttpHeaders;
    }
}

