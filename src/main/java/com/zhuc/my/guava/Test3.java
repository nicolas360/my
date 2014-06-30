package com.zhuc.my.guava;

import com.google.common.base.Optional;

public class Test3 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(null instanceof Object);

		//		Optional<Integer> possible = Optional.of(6);
		//		if (possible.isPresent()) {
		//			System.out.println("possible isPresent:" + possible.isPresent());
		//			System.out.println("possible value:" + possible.get());
		//		}

		Optional<Integer> possible = Optional.of(6);
		Optional<Integer> absentOpt = Optional.absent();
		Optional<Integer> NullableOpt = Optional.fromNullable(null);
		Optional<Integer> NoNullableOpt = Optional.fromNullable(10);
		if (possible.isPresent()) {
			System.out.println("possible isPresent:" + possible.isPresent());
			System.out.println("possible value:" + possible.get());
		}
		if (absentOpt.isPresent()) {
			System.out.println("absentOpt isPresent:" + absentOpt.isPresent());
		}
		if (NullableOpt.isPresent()) {
			System.out.println("fromNullableOpt isPresent:" + NullableOpt.isPresent());
		}
		if (NoNullableOpt.isPresent()) {
			System.out.println("NoNullableOpt isPresent:" + NoNullableOpt.isPresent());
		}

		Optional<String> ss = Optional.of(null);
		System.out.println(ss.get());

	}

}
