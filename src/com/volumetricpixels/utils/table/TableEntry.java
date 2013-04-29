package com.volumetricpixels.utils.table;

public final class TableEntry<V> {
	private final String row;
	private final String column;
	private final V value;

	public TableEntry(String row, String column, V value) {
		this.row = row;
		this.column = column;
		this.value = value;
	}

	public String getRow() {
		return row;
	}

	public String getColumn() {
		return column;
	}

	public V getValue() {
		return value;
	}
}
