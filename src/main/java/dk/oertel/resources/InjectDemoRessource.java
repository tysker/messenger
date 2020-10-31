package dk.oertel.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.UriInfo;

@Path("/injectdemo")
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.TEXT_PLAIN)
public class InjectDemoRessource {

    @GET
    @Path("annotations")
    public String getParamUsingAnnotations(@MatrixParam("param") String param) {
        return "Matrix param: " + param;
    }

    @GET
    @Path("headers")
    public String getHeaderValues(@HeaderParam("authSessionId") String authSessionId) {
        return "Header param: " + authSessionId;
    }

    @GET
    @Path("cookie")
    public String getCookieValues(@CookieParam("name") String name) {
        return "Cookie param: " + name;
    }

    @GET
    @Path("context")
    public String getParamsUsingContext(
            @Context UriInfo uriInfo,
            @Context HttpHeaders httpHeaders
    ) {
        String absolutePath = uriInfo.getAbsolutePath().toString();
        String cookie = httpHeaders.getCookies().toString();

        return cookie;
    }




    // is not used as much
//    @GET
//    @Path("form")
//    public String getFormValues(@FormParam("name") String name) {
//        return "Cookie param: " + name;
//    }

}
