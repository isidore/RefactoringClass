package com.gildedrose;

public class DefaultAgeItem implements AgeItem {

	public void age(Item item) {
		if (item.quality > 0) {
			item.quality--;
		}
	}

	public void expire(Item item) {
		age(item);
	}
}
