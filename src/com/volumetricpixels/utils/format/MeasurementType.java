package com.volumetricpixels.utils.format;

import java.util.HashMap;
import java.util.Map;

import com.volumetricpixels.utils.Utilities;

public enum MeasurementType {
	/** Micro* (e.g micrometer) */
	MICRO("Micro", "mi", 0.000001),
	/** Milli* (e.g millilitres) */
	MILLI("Milli", "m", 0.001),
	/** Kilo* (e.g kilowatts) */
	KILO("Kilo", "k", 1000),
	/** Mega* (e.g megajoules) */
	MEGA("Mega", "M", 1000000);

	private static final Map<String, MeasurementType> measureTypes = new HashMap<String, MeasurementType>();

	public String name;
	public String symbol;
	public double value;

	MeasurementType(final String name, final String symbol, final double value) {
		this.name = name;
		this.symbol = symbol;
		this.value = value;

		final MeasurementType mt = this;
		Utilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				measureTypes.put(name, mt);
			}
		});
	}

	public String getName(boolean symbol) {
		if (symbol) {
			return this.symbol;
		} else {
			return name;
		}
	}

	public double process(double value) {
		return value / this.value;
	}

	public static MeasurementType get(String name) {
		return measureTypes.get(name);
	}
}
