package net.wasdev.jaxrs.async;

import java.util.Collection;
import java.util.concurrent.ExecutorService;

import javax.naming.InitialContext;
import javax.naming.NamingException;
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

@Path("execitems")
public class ItemsExecutorResource {
    
    ItemService itemService = new ItemService();

    private ExecutorService getExecutor() throws NamingException {
        return (ExecutorService) new InitialContext()
                 .lookup("java:comp/DefaultManagedExecutorService");
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public void getItems(@Suspended final AsyncResponse ar) {
        Runnable r = new Runnable() {
            public void run() {
                Collection<Item> result = itemService.listItems();
                Response resp = Response.ok(result).build();
                ar.resume(resp);
            }
        };

        try {
            ExecutorService executor = getExecutor();
            System.out.println("execitems: " + executor);
            executor.submit(r);
        } catch (NamingException e) {
            r.run();
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
