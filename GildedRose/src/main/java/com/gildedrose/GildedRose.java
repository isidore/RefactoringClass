package com.gildedrose;

class GildedRose {
	Item[] items;

	public GildedRose(Item[] items) {
		this.items = items;
	}

	public void updateQuality() {
		for (int i = 0; i < items.length; i++) {

			Item item = items[i];
			if (item.name.equals("Sulfuras, Hand of Ragnaros")) {
				continue;
			}
			if (item.name.equals("Aged Brie")) {
				doAgedBrie(item);
				continue;
			}
			if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
				doBackstagePass(item);
				continue;
			}
			doNormal(item);

		}
	}

	public void doNormal(Item item) {
		if (item.quality > 0) {
			item.quality = item.quality - 1;

		}

		item.sellIn = item.sellIn - 1;

		if (item.sellIn < 0) {

			if (item.quality > 0) {
				item.quality = item.quality - 1;

			}

		}
	}

	public void doBackstagePass(Item item) {
		if (item.quality < 50) {
			item.quality = item.quality + 1;

			if (item.sellIn < 11) {
				if (item.quality < 50) {
					item.quality++;
				}
			}

			if (item.sellIn < 6) {
				if (item.quality < 50) {
					item.quality++;
				}
			}

		}
		item.sellIn--;

		if (item.sellIn < 0) {

			item.quality = 0;
		}
	}

	public void doAgedBrie(Item item) {
		if (item.quality < 50) {
			item.quality = item.quality + 1;

		}

		item.sellIn = item.sellIn - 1;

		if (item.sellIn < 0) {
			if (item.quality < 50) {
				item.quality = item.quality + 1;
			}
		}
	}
}