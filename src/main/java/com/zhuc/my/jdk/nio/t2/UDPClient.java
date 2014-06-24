package com.zhuc.my.jdk.nio.t2;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * @author zhuc
 * @create 2013-9-4 上午10:28:20
 */
public class UDPClient extends Thread {
	@Override
	public void run() {
		DatagramChannel channel = null;
		Selector selector = null;
		SocketAddress sa = null;
		int udpPort = 5000;
		try {
			channel = DatagramChannel.open();
			channel.configureBlocking(false);
			sa = new InetSocketAddress("localhost", udpPort);

		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			selector = Selector.open();
			channel.register(selector, SelectionKey.OP_READ);
			int count = channel.send(Charset.defaultCharset().encode("Tell me your time"), sa);
		} catch (Exception e) {
			e.printStackTrace();
		}

		ByteBuffer byteBuffer = ByteBuffer.allocate(100);
		while (true) {
			try {
				int eventsCount = selector.select();
				if (eventsCount > 0) {
					Set<SelectionKey> selectedKeys = selector.selectedKeys();
					Iterator<SelectionKey> iterator = selectedKeys.iterator();
					while (iterator.hasNext()) {
						SelectionKey sk = iterator.next();
						iterator.remove();
						if (sk.isReadable()) {
							//在这里datagramChannel与channel实际是同一个对象
							DatagramChannel datagramChannel = (DatagramChannel) sk.channel();
							byteBuffer.clear();
							SocketAddress isa = datagramChannel.receive(byteBuffer);

							byteBuffer.flip();
							// 测试：通过将收到的ByteBuffer首先通过缺省的编码解码成CharBuffer 再输出
							System.out.println(Charset.defaultCharset().decode(byteBuffer).toString());
							Thread.sleep(3000);//延时
							int count = datagramChannel.send(Charset.defaultCharset().encode("hello server"), isa);

						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	/**
	* @param args
	*/
	public static void main(String[] args) {
		new UDPClient().start();
	}
}
