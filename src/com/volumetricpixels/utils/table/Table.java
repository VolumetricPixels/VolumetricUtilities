package com.volumetricpixels.utils.table;

import java.util.ArrayList;
import java.util.List;

public class Table<V> {
	private final List<TableEntry<V>> entries;
	private final boolean caseSensitive;

	public Table() {
		this(false);
	}

	public Table(boolean caseSensitive) {
		entries = new ArrayList<>();
		this.caseSensitive = caseSensitive;
	}

	public Table(List<TableEntry<V>> entries, boolean caseSensitive) {
		this(caseSensitive);
		this.entries.addAll(entries);
	}

	@SuppressWarnings("unchecked")
	public Table(Table table, boolean caseSensitive) {
		this(table.entries, caseSensitive);
	}

	public V get(String row, String column) {
		if (row == null) {
			throw new IllegalArgumentException("row cannot be null!");
		}
		if (column == null) {
			throw new IllegalArgumentException("column cannot be null!");
		}

		for (TableEntry<V> entry : entries) {
			String r = entry.getRow();
			String c = entry.getColumn();

			if ((!caseSensitive) ? r.equalsIgnoreCase(row) && c.equals(column)
					: r.equals(row) && c.equals(column)) {
				return entry.getValue();
			}
		}
		return null;
	}

	public V put(String row, String column, V value) {
		return put(new TableEntry<V>(row, column, value));
	}

	public V put(TableEntry<V> entry) {
		V val = get(entry.getRow(), entry.getColumn());
		if (val != null) {
			return val;
		}

		entries.add(entry);
		return null;
	}

	public List<TableEntry<V>> entries() {
		return entries;
	}
}
