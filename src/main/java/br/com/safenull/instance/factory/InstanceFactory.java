package br.com.safenull.instance.factory;

import java.lang.reflect.Field;
import java.util.Collection;

public class InstanceFactory {

	public static AbstractInstanceFactory get(Field field){
		if (Collection.class.isAssignableFrom(field.getType())) {
			return new CollectionFactory();
		} else {
			return new ObjectFactory();
		}
	}
	
}
