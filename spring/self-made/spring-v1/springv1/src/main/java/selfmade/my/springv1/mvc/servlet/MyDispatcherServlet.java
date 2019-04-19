package selfmade.my.springv1.mvc.servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import selfmade.my.springv1.annotation.MyAutoWired;
import selfmade.my.springv1.annotation.MyController;
import selfmade.my.springv1.annotation.MyDao;
import selfmade.my.springv1.annotation.MyService;

public class MyDispatcherServlet extends HttpServlet {

	private Properties properties;
	private Set<String> classNameList = new HashSet<String>();
	private HashMap<String, Object> iocMap = new HashMap<String, Object>();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

	}

	@Override
	public void init(ServletConfig servletConfig) throws ServletException {

		doLoadConfig(servletConfig);

		doInstance();

		doAutoWired();

		initHandlerMapping();

	}

	private void initHandlerMapping() {
		// TODO Auto-generated method stub

	}

	private void doAutoWired() {

		for (String className : classNameList) {
			Class clazz;
			try {
				clazz = Class.forName(className);

				for (Field field : clazz.getFields()) {
					if (field.isAnnotationPresent(MyAutoWired.class)) {
						String keyName = field.getType().getSimpleName()
								.toLowerCase();
						if (!iocMap.containsKey(keyName))
							throw new Exception(
									"field "
											+ field.getName()
											+ "in class "
											+ clazz.getSimpleName()
											+ " can not be autowired, class not registered.");
						field.setAccessible(true);
						field.set(obj, iocMap.get(keyName));

					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	private void doInstance() {

		for (String className : classNameList) {
			try {
				Class clazz = Class.forName(className);
				String keyName;
				if (clazz.isAnnotationPresent(MyController.class)
						|| clazz.isAnnotationPresent(MyService.class)
						|| clazz.isAnnotationPresent(MyDao.class)) {
					iocMap.put(clazz.getSimpleName().toLowerCase(),
							clazz.newInstance());

					for (Class c : clazz.getInterfaces())
						iocMap.put(c.getSimpleName().toLowerCase(),
								clazz.newInstance());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	private void doScanPackage(String scanPackage) {

		URL baseUrl = this.getClass().getClassLoader()
				.getResource("/" + scanPackage.replaceAll("\\.", "/"));
		File baseDir = new File(baseUrl.getFile());

		for (File file : baseDir.listFiles()) {
			if (file.isDirectory())
				doScanPackage(scanPackage + "." + file.getName());
			else if (!file.getName().endsWith(".class"))
				continue;
			else
				classNameList.add(scanPackage + "."
						+ file.getName().replace(".class", ""));

		}

	}

	private void doLoadConfig(ServletConfig servletConfig) {

		InputStream in;

		try {
			in = this
					.getClass()
					.getClassLoader()
					.getResourceAsStream(
							servletConfig
									.getInitParameter("contextConfigLocation"));
			properties.load(in);
			String scanPackage = properties.getProperty("scanPackage");
			doScanPackage(scanPackage);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
