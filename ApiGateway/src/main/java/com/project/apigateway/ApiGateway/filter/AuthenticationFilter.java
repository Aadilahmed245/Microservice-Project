package com.project.apigateway.ApiGateway.filter;

import com.project.apigateway.ApiGateway.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {
   @Autowired
   private RouteValidator validator;
    @Autowired
    private JwtUtil jwtUtil;
    public AuthenticationFilter()
   {
        super(Config.class);
   }

//    @Override
//    public GatewayFilter apply(Config config) {
//        return ((exchange, chain) -> {
//            if(validator.isSecured.test(exchange.getRequest()))
//            {
//                // header contains token or not
//                if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION))
//                {
//                    throw new RuntimeException("Missing Authorization header");
//                }
//                String authheader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
//                if(authheader!=null && authheader.startsWith("Bearer"))
//                {
//                    authheader= authheader.substring(7);
//                }
//                try{
//                    jwtUtil.validateToken(authheader);
//                }
//                catch (Exception e)
//                {
//                    throw new RuntimeException("Token is not valid");
//                }
//            }
//            return chain.filter(exchange);
//        });
//    }
    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            if (validator.isSecured.test(exchange.getRequest())) {
                // header contains token or not
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    return errorResponse(exchange, HttpStatus.UNAUTHORIZED, "Missing Authorization header");
                }
                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if (authHeader != null && authHeader.startsWith("Bearer")) {
                    authHeader = authHeader.substring(7);
                }
                try {
                    jwtUtil.validateToken(authHeader);
                } catch (Exception e) {
                    return errorResponse(exchange, HttpStatus.UNAUTHORIZED, "Token is not valid");
                }
            }
            return chain.filter(exchange);
        });
    }
    private Mono<Void> errorResponse(ServerWebExchange exchange, HttpStatus httpStatus, String message) {
        exchange.getResponse().setStatusCode(httpStatus);
        exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
        String responseBody = "{\"message\": \"" + message + "\"}";
        DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(responseBody.getBytes());
        return exchange.getResponse().writeWith(Mono.just(buffer));
    }


    public static class Config
    {

    }
}
