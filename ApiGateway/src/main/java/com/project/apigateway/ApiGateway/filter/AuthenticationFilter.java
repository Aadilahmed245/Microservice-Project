package com.project.apigateway.ApiGateway.filter;

import com.project.apigateway.ApiGateway.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {
   @Autowired
   private RouteValidator validator;
//   @Autowired
//   private RestTemplate restTemplate;
    @Autowired
 private JwtUtil jwtUtil;
    public AuthenticationFilter()
   {
        super(Config.class);
   }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            if(validator.isSecured.test(exchange.getRequest()))
            {
                // header contains token or not
                if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION))
                {
                    throw new RuntimeException("Missing Authorization header");
                }
                String authheader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if(authheader!=null && authheader.startsWith("Bearer"))
                {
                    authheader= authheader.substring(7);
                }
                try{
                    //REST CALL TO AUTH SERVICE
                 //   String forObject = restTemplate.getForObject("http://IDENTITY-SERVICE/validate?token=" + authheader, String.class);
                      jwtUtil.validateToken(authheader);
                }
                catch (Exception e)
                {
                    throw new RuntimeException("Token is not valid");
                }
            }
            return chain.filter(exchange);
        });
    }


    public static class Config
{

}
}
