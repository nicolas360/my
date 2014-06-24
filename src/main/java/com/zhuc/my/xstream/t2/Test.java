package com.zhuc.my.xstream.t2;

import com.thoughtworks.xstream.XStream;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Blog teamBlog = new Blog(new Author("Guilherme Silveira"));
		teamBlog.add(new Entry("first", "My first blog entry."));
		teamBlog.add(new Entry("tutorial", "Today we have developed a nice alias tutorial. Tell your friends! NOW!"));

		XStream xstream = new XStream();

		xstream.alias("blog", Blog.class);
		xstream.aliasField("author", Blog.class, "writer");
		xstream.alias("entry", Entry.class);

		//Now let's implement what was called an implicit collection: 
		//whenever you have a collection which doesn't need to display it's root tag, you can map it as an implicit collection.
		xstream.addImplicitCollection(Blog.class, "entries");

		//Fields can be written as attributes if the field type is handled by a SingleValueConverter 
		xstream.useAttributeFor(Blog.class, "writer");
		xstream.registerConverter(new AuthorConverter());

		//		xstream.aliasPackage("my.company", "com.zhuc.my");

		System.out.println(xstream.toXML(teamBlog));

	}

}
