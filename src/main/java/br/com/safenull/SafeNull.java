package br.com.safenull;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import br.com.safenull.exception.SafeNullException;
import br.com.safenull.instance.factory.InstanceFactory;

public class SafeNull {

	private static final String NAO_FOI_POSSIVEL_INSTANCIAR_O_OBJETO = "Nao foi possivel instanciar o objeto";

	private static final String DEFAULT_PACKAGE_IGNORED = "java.lang"; 
	
	private List<String> ignoredPackages = new ArrayList<>();

	protected SafeNull() {
		ignoredPackages.add(DEFAULT_PACKAGE_IGNORED);
	}

	protected void addIgnoredPackages(List<String> packages) {
		List<String> packs = packages;
		this.ignoredPackages.addAll(packs);
	}

	public <T> T create(Class<T> clazz) {
		try {
			Constructor<T> constructor = clazz.getConstructor();
			T t = constructor.newInstance();
			return create(t, clazz);
		} catch (Exception e) {
			throw new SafeNullException(NAO_FOI_POSSIVEL_INSTANCIAR_O_OBJETO, e);
		}
	}

	@SuppressWarnings("unchecked")
	public <T> T create(T obj, Class<?> clazz) {
		if (obj == null) {
			try {
				obj = (T) clazz.newInstance();
			} catch (Exception e) {
				throw new SafeNullException(NAO_FOI_POSSIVEL_INSTANCIAR_O_OBJETO, e);
			}
		}

		List<Field> fields = getAllFields(new ArrayList<Field>(), obj.getClass());

		createFilds(fields, obj);

		return obj;
	}

	protected <T> void createFilds(List<Field> fields, T obj) {

		for (Field field : fields) {
			try {
				field.setAccessible(true);

				if (ignorarCampo(field) || (field.get(obj) != null)) {
					continue;
				}

				Object newInstance = InstanceFactory.get(field).createInstance(field);

				field.set(obj, newInstance);
				
				List<Field> newFields = getAllFields(new ArrayList<Field>(), newInstance.getClass());
								
				createFilds(newFields, newInstance);

			} catch (Exception e) {
				throw new SafeNullException(NAO_FOI_POSSIVEL_INSTANCIAR_O_OBJETO, e);
			}
		}

	}

	protected List<Field> getAllFields(List<Field> fields, Class<?> clazz){
		fields.addAll( Arrays.asList(clazz.getDeclaredFields()));
		
		if(clazz.getSuperclass() != null){
			fields = getAllFields(fields, clazz.getSuperclass());
		}
		
		return fields;
	}

	protected boolean ignorarCampo(Field field) {
		for (String pack : ignoredPackages) {
			if (field.getType().getName().startsWith(pack)) {
				return true;
			}
		}
		return false;
	}

}
