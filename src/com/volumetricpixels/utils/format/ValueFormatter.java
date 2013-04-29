package com.volumetricpixels.utils.format;

public class ValueFormatter {
	public static String format(double val, boolean small, UnitType unit,
			MeasurementType measure) {
		return small ? formatShort(val, unit, measure) : formatLong(val, unit,
				measure);
	}

	public static String formatLong(double val, UnitType unit,
			MeasurementType measure) {
		return new StringBuilder(Double.toString(val)).append(measure.name)
				.append(val != 1 ? unit.getPlural() : unit.getSingular())
				.toString();
	}

	public static String formatShort(double val, UnitType unit,
			MeasurementType measure) {
		return new StringBuilder(Double.toString(val)).append(measure.symbol)
				.append(unit.getSymbol()).toString();
	}
}
