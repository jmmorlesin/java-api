package com.jmms.api.controller;

import org.springframework.stereotype.Controller;
import com.jmms.api.domain.jwt.LoginInfo;
import com.jmms.api.utils.Routes;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;


@Controller
@Path(Routes.PATH_LOGIN)
public class LoginController {

    @POST
    public Response getLoginInfo(LoginInfo loginInfo){
        return Response.status(Response.Status.OK).entity(loginInfo).build();
    }

}
