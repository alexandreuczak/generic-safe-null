package br.com.safenull.instance.factory;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class CollectionFactory extends AbstractInstanceFactory{

	@Override
	public <T> Object createInstance(Field field) throws InstantiationException, IllegalAccessException{
		return new ArrayList<T>();
	}

}
