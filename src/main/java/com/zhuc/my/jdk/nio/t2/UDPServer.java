package com.zhuc.my.jdk.nio.t2;

import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * @author zhuc
 * @create 2013-9-4 上午10:27:20
 */
public class UDPServer extends Thread {

	@Override
	public void run() {
		Selector selector = null;
		DatagramChannel channel = null;
		int udpPort = 5000;
		try {
			channel = DatagramChannel.open();
			DatagramSocket socket = channel.socket();
			channel.configureBlocking(false);
			socket.bind(new InetSocketAddress(udpPort));
			System.out.println("server start!");
			selector = Selector.open();
			channel.register(selector, SelectionKey.OP_READ);
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
							SocketAddress sa = datagramChannel.receive(byteBuffer);
							byteBuffer.flip();

							// 测试：通过将收到的ByteBuffer首先通过缺省的编码解码成CharBuffer 再输出
							CharBuffer charBuffer = Charset.defaultCharset().decode(byteBuffer);
							System.out.println("receive message:" + charBuffer.toString());

							String echo = "server reply!";
							ByteBuffer buffer = Charset.defaultCharset().encode(echo);
							Thread.sleep(1000);//延时
							datagramChannel.send(buffer, sa);

						}
					}//while iterator
				}//if
			} catch (Exception e) {
				e.printStackTrace();
			}
		}//while

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new UDPServer().start();
	}
}
