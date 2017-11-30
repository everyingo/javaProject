package com.mine.myboot.simple.common.inter;

public abstract class Converter<A, B> {

	protected abstract B doForward(A a);

	protected abstract A doBackward(B b);
}
