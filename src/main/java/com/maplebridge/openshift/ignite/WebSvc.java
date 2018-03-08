package com.maplebridge.openshift.ignite;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class WebSvc
{
        public WebSvc()
        {
                System.out.println("mcm: In WebSvc Contructor method");
        }

        // This method is called if TEXT_PLAIN is request
        @GET
        @Produces(MediaType.TEXT_PLAIN)
        public String sayPlainTextHello() {
            return "Hello Jersey";
        }

}
