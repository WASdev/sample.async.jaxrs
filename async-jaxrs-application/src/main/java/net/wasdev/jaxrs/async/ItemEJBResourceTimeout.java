package net.wasdev.jaxrs.async;

import java.util.concurrent.TimeUnit;

import javax.ejb.EJB;
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

@Path("ejbitemsTimeout")
public class ItemEJBResourceTimeout implements TimeoutHandler{   
    @EJB
    AsycEJB asyncEJB;
        
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public void getItems(@Suspended final AsyncResponse ar) {
        System.out.println("ejbitemsTimeout");
        ar.setTimeout(500, TimeUnit.MILLISECONDS);
        ar.setTimeoutHandler(this);
        
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

        asyncEJB.getItems(ar);
    }
    
    public void handleTimeout(AsyncResponse ar) {
        ar.resume(Response.ok("Backup plan! " + new Item().getId()).build());
    }
}
