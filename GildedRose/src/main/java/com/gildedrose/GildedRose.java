package com.gildedrose;

class GildedRose {
	private static final String SULFURAS_HAND_OF_RAGNAROS = "Sulfuras, Hand of Ragnaros";
	private static final String AGED_BRIE = "Aged Brie";
	private static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
	Item[] items;

	public GildedRose(Item[] items) {
		this.items = items;
	}

	public void updateQuality() {
		for (int i = 0; i < items.length; i++) {
			updateQualityToo(items[i]);

			decrementSellInUnlessSulfuras(items[i]);

			updateQuality(items[i]);
		}
	}

	public void decrementSellInUnlessSulfuras(Item item) {
		if (item.name.equals(SULFURAS_HAND_OF_RAGNAROS)) {
			return;
		}
		item.sellIn--;
	}

	public void decrementQualityUnlessSulfuras(Item item) {
		if (item.name.equals(SULFURAS_HAND_OF_RAGNAROS)) {
			return;
		}
		item.quality--;
	}

	public void updateQuality(Item item) {
		if (item.sellIn < 0) {
			if (!item.name.equals(AGED_BRIE)) {
				if (!item.name.equals(BACKSTAGE_PASSES)) {
					if (item.quality > 0) {
						decrementQualityUnlessSulfuras(item);
					}
				} else {
					item.quality = 0;
				}
			} else {
				if (item.quality < 50) {
					item.quality++;
				}
			}
		}
	}

	public void updateQualityToo(Item item) {
		if (!item.name.equals(AGED_BRIE) && !item.name.equals(BACKSTAGE_PASSES)) {
			if (item.quality > 0) {
				decrementQualityUnlessSulfuras(item);
			}
		} else {
			if (item.quality < 50) {
				item.quality++;

				if (item.name.equals(BACKSTAGE_PASSES)) {
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
			}
		}
	}
}