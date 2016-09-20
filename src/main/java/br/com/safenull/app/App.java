package br.com.safenull.app;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class App {

	public static void main(String[] args) {
		new App().doIt();
	}

	protected void doIt() {
		try {
			File file = new File("src\\main\\resources\\config.prop");
			FileInputStream stream = new FileInputStream(file);
			Properties prop = new Properties();
			prop.load(stream);
			String url = prop.getProperty("url");
			System.out.println(url);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
