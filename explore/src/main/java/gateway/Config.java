package gateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder
                .routes()
                // create endpoint gateway /get
                .route(api -> api
                        .path("/get")
                        .filters(filter -> filter.addRequestHeader("myheader", "ohmygod")) // adding request header when hit to uri
                        .uri("http://httpbin.org:80"))

                // hystrix implementation example
                .route(api -> api
                        .host("*.hystrix.com") // hystrix, only allow with headers Host <any>.hystrix.com
                        .filters(filter -> filter
                                .hystrix(config -> config
                                        .setName("mycmd").setFallbackUri("forward:/fallback")))
                        .uri("http://httpbin.org:80"))
                .build();
    }
}
