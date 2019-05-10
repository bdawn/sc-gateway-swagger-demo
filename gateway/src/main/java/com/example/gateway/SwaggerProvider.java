package com.example.gateway;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

@Component
@Primary
public class SwaggerProvider implements SwaggerResourcesProvider {
    public static final String API_URI = "/v2/api-docs";

    private final EurekaClient eurekaClient;

    @Autowired
    public SwaggerProvider( EurekaClient eurekaClient) {
        this.eurekaClient = eurekaClient;
    }

    @Override
    public List<SwaggerResource> get() {
        List<SwaggerResource> resources = new ArrayList<>();

        eurekaClient.getApplications().getRegisteredApplications().forEach(application -> {
            InstanceInfo instanceInfo = application.getInstances().get(0);

            resources.add(swaggerResource(instanceInfo.getAppName(),
                    "/" + instanceInfo.getVIPAddress() + API_URI));
        });
        return resources;
    }

    private SwaggerResource swaggerResource(String name, String location) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion("2.0");
        return swaggerResource;
    }
}