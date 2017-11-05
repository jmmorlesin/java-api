package com.jmms.api.controller;

import com.jmms.api.utils.Routes;
import org.springframework.stereotype.Controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;


@Controller
@Path(Routes.PATH_AUTHENTICATED)
public class AuthenticatedController {

    @GET
    public Response getProofOfAuthentication(){
        System.out.println("LALALALALAL");
        return Response.status(Response.Status.OK).entity("The token is valid").build();
    }

}
