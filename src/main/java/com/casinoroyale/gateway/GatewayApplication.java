package com.casinoroyale.gateway;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Primary
    @Bean
    SwaggerResourcesProvider swaggerResourcesProvider(final RouteLocator routeLocator) {
        return () -> {
            List<SwaggerResource> resources = new ArrayList<>();

            routeLocator
                    .getRoutes()
                    .forEach(route -> {
                        final String name = route.getId();
                        final String location = route.getFullPath().replace("**", "v2/api-docs");
                        final SwaggerResource swaggerResource = swaggerResource(name, location, "1.0");
                        resources.add(swaggerResource);
                    });

            return resources;
        };
    }

    private SwaggerResource swaggerResource(final String name, final String location, final String version) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion(version);
        return swaggerResource;
    }

}
