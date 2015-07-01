package net.wasdev.jaxrs.async;

import java.util.Collection;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("cdiexecitems")
public class ItemsCDIExecutorResource {
    
    ItemService itemService = new ItemService();

    @Resource
    ManagedExecutorService executor;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public void getItems(@Suspended final AsyncResponse ar) {
        System.out.println("cdiexecitems: " + executor);
        executor.submit(new Runnable() {
            public void run() {
                Collection<Item> result = itemService.listItems();
                Response resp = Response.ok(result).build();
                ar.resume(resp);
            }
        });
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
