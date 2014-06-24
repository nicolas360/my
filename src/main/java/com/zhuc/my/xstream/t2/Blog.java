package com.zhuc.my.xstream.t2;

import java.util.ArrayList;
import java.util.List;

public class Blog {
	private final Author writer;
	private final List entries = new ArrayList();

	public Blog(Author writer) {
		this.writer = writer;
	}

	public void add(Entry entry) {
		entries.add(entry);
	}

	public List getContent() {
		return entries;
	}
}
