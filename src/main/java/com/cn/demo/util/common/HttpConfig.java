package com.cn.demo.util.common;

import com.cn.demo.contant.Constants;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: demo
 * @description: HttpConfig配置
 * @author: DongLianPo
 * @create: 2018/12/11 14:08
 **/
@Configuration
public class HttpConfig extends RequestConfig {
    @Configuration
    public class HttpClient {


        /**
         * 首先实例化一个连接池管理器，设置最大连接数、并发连接数
         *
         * @return PoolingHttpClientConnectionManager
         */
        @Bean(name = "httpClientConnectionManager")
        public PoolingHttpClientConnectionManager getHttpClientConnectionManager() {
            PoolingHttpClientConnectionManager httpClientConnectionManager = new PoolingHttpClientConnectionManager();
            //最大连接数
            httpClientConnectionManager.setMaxTotal(Constants.HTTP_MAX_TOTAL);
            //并发数
            httpClientConnectionManager.setDefaultMaxPerRoute(Constants.HTTP_DEFAULT_MAX_PER_ROUTE);
            return httpClientConnectionManager;
        }

        /**
         * 实例化连接池，设置连接池管理器。
         * 这里需要以参数形式注入上面实例化的连接池管理器
         *
         * @param httpClientConnectionManager http
         * @return HttpClientBuilder
         */
        @Bean(name = "httpClientBuilder")
        public HttpClientBuilder getHttpClientBuilder(@Qualifier("httpClientConnectionManager") PoolingHttpClientConnectionManager httpClientConnectionManager) {
            //HttpClientBuilder中的构造方法被protected修饰，所以这里不能直接使用new来实例化一个HttpClientBuilder，可以使用HttpClientBuilder提供的静态方法create()来获取HttpClientBuilder对象
            HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();

            httpClientBuilder.setConnectionManager(httpClientConnectionManager);

            return httpClientBuilder;
        }

        /**
         * 注入连接池，用于获取httpClient
         *
         * @param httpClientBuilder Http连接池
         * @return CloseableHttpClient
         */
        @Bean
        public CloseableHttpClient getCloseableHttpClient(@Qualifier("httpClientBuilder") HttpClientBuilder httpClientBuilder) {
            return httpClientBuilder.build();
        }

        /**
         * Builder是RequestConfig的一个内部类
         * 通过RequestConfig的custom方法来获取到一个Builder对象
         * 设置builder的连接信息
         * 这里还可以设置proxy，cookieSpec等属性。有需要的话可以在此设置
         *
         * @return Builder
         */
        @Bean(name = "builder")
        public RequestConfig.Builder getBuilder() {
            RequestConfig.Builder builder = RequestConfig.custom();
            return builder.setConnectTimeout(Constants.HTTP_CONNECT_TIMEOUT)
                    .setConnectionRequestTimeout(Constants.HTTP_CONNECTION_REQUEST_TIMEOUT)
                    .setSocketTimeout(Constants.HTTP_SOCKET_TIMEOUT);
        }

        /**
         * 使用builder构建一个RequestConfig对象
         *
         * @param builder config
         * @return RequestConfig
         */
        @Bean
        public RequestConfig getRequestConfig(@Qualifier("builder") RequestConfig.Builder builder) {
            return builder.build();
        }

    }
}
