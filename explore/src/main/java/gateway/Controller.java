package gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
public class Controller {
    @RequestMapping("/fallback")
    public Mono<String> fallback() {
        return Mono.just("fallback");
    }

    @RequestMapping(value = "/test/post", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<String> test(@RequestHeader("API-Version") String apiVersion, @RequestBody ApiRequest apiRequest) {
        log.info("this is the api version: {}", apiVersion);
        log.info("this is the request body: {}", apiRequest);
        return Mono.just("test");
    }

}
