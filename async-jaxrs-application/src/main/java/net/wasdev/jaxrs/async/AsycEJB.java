package net.wasdev.jaxrs.async;

import java.util.Collection;
import java.util.concurrent.Future;

import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;

@Stateless
@Asynchronous
public class AsycEJB {
    ItemService itemService = new ItemService();

    public Future<Collection<Item>> getItems(@Suspended final AsyncResponse ar) {
 
        return new AsyncResult<Collection<Item>>(itemService.listItems());
    }
}
