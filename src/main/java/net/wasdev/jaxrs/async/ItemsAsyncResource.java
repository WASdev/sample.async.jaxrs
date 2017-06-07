/*******************************************************************************
 * Copyright (c) 2015 IBM Corp.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package net.wasdev.jaxrs.async;

import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;

@Path("asyncitems")
public class ItemsAsyncResource {
    
    ItemService itemService = new ItemService();

    /**
     * Attempt asynchronous processing by using the @Suspended annotation. Note that as the long-running
     * service is called on the same request thread, there isn't much of a behavior change between this method
     * and {@link ItemsResource#listItems()}.
     * 
     * @param ar AsyncResponse object that is used to resume the request (re-associate the connection with the thread) 
     *           in order to send the response.
     * @return null to allow the method to return immediately: any other result is sent via {@link AsyncResponse#resume(Object)}
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public void getItems(@Suspended final AsyncResponse ar) {
        System.out.println("items");
        Collection<Item> result = itemService.listItems();
        //Response resp = Response.ok(result).build();
        ar.resume(result);
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
