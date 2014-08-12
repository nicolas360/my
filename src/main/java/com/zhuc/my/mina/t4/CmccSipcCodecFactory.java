package com.zhuc.my.mina.t4;

import java.nio.charset.Charset;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

public class CmccSipcCodecFactory implements ProtocolCodecFactory {
	private final CmccSipcEncoder encoder;
	private final CmccSipcDecoder decoder;

	public CmccSipcCodecFactory() {
		this(Charset.defaultCharset());
	}

	public CmccSipcCodecFactory(Charset charSet) {
		this.encoder = new CmccSipcEncoder(charSet);
		this.decoder = new CmccSipcDecoder(charSet);
	}

	@Override
	public ProtocolDecoder getDecoder(IoSession session) throws Exception {
		return decoder;
	}

	@Override
	public ProtocolEncoder getEncoder(IoSession session) throws Exception {
		return encoder;
	}
}