package br.com.safenull.instance.factory;

import java.lang.reflect.Field;

public abstract class AbstractInstanceFactory {

	public abstract <T> Object createInstance(Field field) throws InstantiationException, IllegalAccessException;
	
}
