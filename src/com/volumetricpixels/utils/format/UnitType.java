package com.volumetricpixels.utils.format;

import java.util.HashMap;
import java.util.Map;

public class UnitType {
	private static final Map<String, UnitType> units = new HashMap<String, UnitType>();

	private final String singular;
	private final String plural;
	private final String symbol;

	public UnitType(final String singular, final String plural,
			final String symbol) {
		units.put(singular, this);

		this.singular = singular;
		this.plural = plural;
		this.symbol = symbol;
	}

	public String getSingular() {
		return singular;
	}

	public String getPlural() {
		return plural;
	}

	public String getSymbol() {
		return symbol;
	}

	public static final UnitType get(String name) {
		return units.get(name);
	}
}
