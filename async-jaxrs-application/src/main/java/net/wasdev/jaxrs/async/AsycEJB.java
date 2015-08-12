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
