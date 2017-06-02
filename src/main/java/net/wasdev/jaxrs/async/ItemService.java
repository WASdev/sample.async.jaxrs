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

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

public class ItemService {

	/**
	 * "Long-running" list operation
	 * @return Collection containing two items
	 */
	public Collection<Item> listItems() {
		Collection<Item> result = Arrays.asList(new Item(), new Item());
		try {
			System.out.println("Wait for 2 seconds before returning " + result);
			Thread.sleep(TimeUnit.SECONDS.toMillis(2));
		} catch (InterruptedException e) {
		}

		// The list always contains 2 items, that will have incrementing ids
		return result;
	}

	public void addItem(Item newItem) {
	}

	public Item get(int id) {
		return null;
	}
}
