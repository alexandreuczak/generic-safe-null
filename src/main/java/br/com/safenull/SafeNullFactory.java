package br.com.safenull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SafeNullFactory {

	List<String> ignoredPackages = new ArrayList<>();
	
	public SafeNullFactory ignorePackages(String ... packages){
		ignoredPackages.addAll(Arrays.asList(packages));
		return this;
	}
	
	public SafeNull getInstance(){
		SafeNull safeNull = new SafeNull();
		
		if(ignoredPackages != null && !ignoredPackages.isEmpty()){
			safeNull.addIgnoredPackages(ignoredPackages);
		}
		
		return safeNull;
	}
	
}
