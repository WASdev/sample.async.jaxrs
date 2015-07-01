package net.wasdev.jaxrs.async;

import java.util.Collection;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
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

@Stateless
@Path("ejbitems")
public class ItemEJBResource {   
    ItemService itemService = new ItemService();

    @GET
    @Asynchronous
    @Produces(MediaType.APPLICATION_JSON)
    public void getItems(@Suspended final AsyncResponse ar) {
        System.out.println("ejbitems");
        Collection<Item> result = itemService.listItems();
        Response resp = Response.ok(result).build();
        ar.resume(resp);
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
