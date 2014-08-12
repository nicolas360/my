package com.zhuc.my.xstream.t5;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.thoughtworks.xstream.XStream;
import com.zhuc.my.xstream.t1.Person;

public class SerializeTest {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		XStream xstream = new XStream();

		File file = new File("d:\\se.txt");
		FileWriter writer = new FileWriter(file);
		ObjectOutputStream out = xstream.createObjectOutputStream(writer);

		out.writeObject(new Person("Joe", "Walnes"));
		out.writeObject(new Person("Someone", "Else"));
		out.writeObject("hello");
		out.writeInt(12345);

		out.close();

		FileReader reader = new FileReader(file);
		ObjectInputStream in = xstream.createObjectInputStream(reader);

		Person a = (Person) in.readObject();
		Person b = (Person) in.readObject();
		String c = (String) in.readObject();
		System.out.println(a);
		System.out.println(b);
		System.out.println(c);
	}

}
