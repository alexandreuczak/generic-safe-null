package br.com.safenull.instance.factory;

import java.lang.reflect.Field;

public class ObjectFactory extends AbstractInstanceFactory{

	@Override
	public <T> Object createInstance(Field field) throws InstantiationException, IllegalAccessException {
		return field.getType().newInstance();
	}

}
