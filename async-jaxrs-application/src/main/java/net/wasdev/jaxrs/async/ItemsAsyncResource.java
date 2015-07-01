package net.wasdev.jaxrs.async;

import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.InvocationCallback;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("asyncitems")
public class ItemsAsyncResource {
    
    ItemService itemService = new ItemService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public void getItems(@Suspended final AsyncResponse ar) {
        System.out.println("items");
        Collection<Item> result = itemService.listItems();
        //Response resp = Response.ok(result).build();
        ar.resume(result);
    }
    
    @GET
    @Path("client")
    @Produces(MediaType.TEXT_HTML)
    public void test(@Suspended final AsyncResponse asyncResponse) {
        InvocationCallback<Response> callback1 = new InvocationCallback<Response>() {
            @Override
            public void completed(Response resp) {
                InvocationCallback<Response> callback2 = new InvocationCallback<Response>() {
                    @Override
                    public void completed(Response resp) {
                        asyncResponse.resume(Response.fromResponse(resp));
                    }
                    @Override
                    public void failed(Throwable arg0) {
                        asyncResponse.resume(Response.Status.INTERNAL_SERVER_ERROR);
                    }
                    
                };
                dumpClassloaders(true);
                Client client = ClientBuilder.newClient();
                WebTarget target = client.target("http://www.google.com");
                target.request(MediaType.TEXT_HTML).async().get(callback2);
            }
            
            @Override
            public void failed(Throwable arg0) {
                asyncResponse.resume(Response.Status.INTERNAL_SERVER_ERROR);
            }
            
        };
        
        dumpClassloaders(false);
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://www.google.com");
        target.request(MediaType.TEXT_HTML).async().get(callback1);
    }
    
    private void dumpClassloaders(boolean nested) {
        Class<?> c = this.getClass();
        ClassLoader cl = c.getClassLoader();
        System.out.println((nested ? "client thread " : "main thread ") + Thread.currentThread());
        System.out.println("sys cl = " + ClassLoader.getSystemClassLoader());
        System.out.println("tccl = " + Thread.currentThread().getContextClassLoader());
        int i = 1;
        while ( cl != null ) {
            System.out.println(i++ + " cl == " + cl);
            cl = cl.getParent();
        }
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addItems(Item newItem) {
        itemService.addItem(newItem);
    }
    
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Item fetchItem(@PathParam("id") int id) {
        return itemService.get(id);
    }
}
