package com.mine.myboot.simple.common.base;

import java.io.Serializable;

public abstract class BaseDTO<M> implements Serializable {

	private static final long serialVersionUID = 1L;

	public abstract M convertTo(String... ignoreName);

	public abstract void convertBack(M m, String... ignoreName);
}
