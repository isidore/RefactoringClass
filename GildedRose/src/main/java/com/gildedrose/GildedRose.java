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
			ageItem(items[i]);

			decrementSellInUnlessSulfuras(items[i]);

			handleExpired(items[i]);
		}
	}

	public void decrementSellInUnlessSulfuras(Item item) {
		if (item.name.equals(SULFURAS_HAND_OF_RAGNAROS)) {
			return;
		}
		item.sellIn--;
	}

	public void handleExpired(Item item) {
		if (0 <= item.sellIn) {
			return;
		}
		if (!handleExpiredBrie(item)) {
			if (!handleExpiredBackstagePass(item)) {
				if (!handleExpiredSulfuras(item)) {
					handleExpiredDefault(item);
				}
			}
		}
	}

	public void handleExpiredDefault(Item item) {
		ageDefault(item);
	}

	public boolean handleExpiredSulfuras(Item item) {
		if (item.name.equals(SULFURAS_HAND_OF_RAGNAROS)) {
			return true;
		}
		return false;
	}

	public boolean handleExpiredBackstagePass(Item item) {
		if (item.name.equals(BACKSTAGE_PASSES)) {
			item.quality = 0;
			return true;
		}
		return false;
	}

	public boolean handleExpiredBrie(Item item) {
		if (item.name.equals(AGED_BRIE)) {
			ageBrie(item);
			return true;
		}
		return false;
	}

	public void ageItem(Item item) {
		if (item.name.equals(AGED_BRIE)) {
			ageBrie(item);
		} else if (item.name.equals(BACKSTAGE_PASSES)) {
			ageBackstagePass(item);
		} else if (item.name.equals(SULFURAS_HAND_OF_RAGNAROS)) {
			ageSulfuras(item);
		} else {
			handleExpiredDefault(item);
		}
	}

	private void ageSulfuras(Item item) {
	}

	public void ageBrie(Item item) {
		incrementQualityUnlessGreaterThan50(item);
	}

	public void ageDefault(Item item) {
		if (item.quality > 0) {
			item.quality--;
		}
	}

	public void ageBackstagePass(Item item) {
		ageBrie(item);
		if (item.sellIn < 11) {
			ageBrie(item);
		}
		if (item.sellIn < 6) {
			ageBrie(item);
		}
	}

	public void incrementQualityUnlessGreaterThan50(Item item) {
		if (item.quality >= 50) {
			return;
		}
		item.quality++;
	}
}