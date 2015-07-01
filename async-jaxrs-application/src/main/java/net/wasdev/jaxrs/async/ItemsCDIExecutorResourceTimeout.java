package net.wasdev.jaxrs.async;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.CompletionCallback;
import javax.ws.rs.container.ConnectionCallback;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.container.TimeoutHandler;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("cdiexecitemsTimeout")
public class ItemsCDIExecutorResourceTimeout {
    
    ItemService itemService = new ItemService();

    @Resource
    ManagedExecutorService executor;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public void getItems(@Suspended final AsyncResponse ar) {
        System.out.println("cdiexecitems: " + executor);
        ar.setTimeout(500, TimeUnit.MILLISECONDS);
        ar.setTimeoutHandler(new TimeoutHandler() {
            public void handleTimeout(AsyncResponse arg0) {
                ar.resume(Response.ok("Backup plan! " + new Item().getId()).build());
            }
        });
        
        ar.register(new CompletionCallback() {
            @Override
            public void onComplete(Throwable t) {
                System.out.println("DONE! ");
                if ( t != null ) {
                    t.printStackTrace();
                }
            }
        });
        
        ar.register(new ConnectionCallback() {
            @Override
            public void onDisconnect(AsyncResponse ar) {
                System.out.println("Disconnected: " + ar);
            }
        });

        executor.submit(new Runnable() {
            public void run() {
                Collection<Item> result = itemService.listItems();
                Response resp = Response.ok(result).build();
                ar.resume(resp);
            }
        });
    }
}
