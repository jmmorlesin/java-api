package com.jmms.api.controller;

import org.springframework.stereotype.Controller;
import com.jmms.api.domain.health.Status;
import com.jmms.api.utils.Routes;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;


@Controller
@Path(Routes.PATH_HEALTH)
public class HealthController {

    // http://localhost:8080/api/health
    @GET
    public Response getHealth(){
        return Response.status(Response.Status.OK).entity(new Status()).build();
    }

}
