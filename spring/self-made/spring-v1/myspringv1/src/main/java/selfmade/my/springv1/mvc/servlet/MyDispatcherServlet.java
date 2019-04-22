package selfmade.my.springv1.mvc.servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
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
import selfmade.my.springv1.annotation.MyRequestMapping;
import selfmade.my.springv1.annotation.MyService;

public class MyDispatcherServlet extends HttpServlet {

	private Properties properties = new Properties();
	private Set<String> classNameSet = new HashSet<String>();
	private HashMap<String, Object> iocMap = new HashMap<String, Object>();
	private HashMap<String, Method> handlerMap = new HashMap<String, Method>();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		doDispatch(req, resp);
	}

	private void doDispatch(HttpServletRequest req, HttpServletResponse resp) {

		String url = req.getRequestURI();
		String contextPath = req.getContextPath();
		url = url.replace(contextPath, "").replaceAll("/+", "/");
		try {
			if (!handlerMap.containsKey(url))
				resp.getWriter().write("404 not found!");

			Method handlerMethod = handlerMap.get(url);
			Map<String, String[]> params = req.getParameterMap();
			handlerMethod.invoke(
					iocMap.get(handlerMethod.getDeclaringClass()
							.getSimpleName().toLowerCase()), new Object[]{req,
							resp, params.get("name")[0]});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void init(ServletConfig servletConfig) throws ServletException {

		doLoadConfig(servletConfig);

		doInstance();

		doAutoWired();

		initHandlerMapping();

	}

	private void initHandlerMapping() {

		for (Map.Entry<String, Object> entry : iocMap.entrySet()) {

			Class<?> clazz = entry.getValue().getClass();
			if (!clazz.isAnnotationPresent(MyController.class))
				continue;
			MyRequestMapping mapping = clazz
					.getAnnotation(MyRequestMapping.class);
			String basePath = ("/" + mapping.value()).replaceAll("/+", "/");

			for (Method method : clazz.getDeclaredMethods()) {
				if (!method.isAnnotationPresent(MyRequestMapping.class))
					continue;
				mapping = method.getAnnotation(MyRequestMapping.class);
				String path = (basePath + "/" + mapping.value()).replaceAll(
						"/+", "/");
				handlerMap.put(path, method);
			}
		}

	}
	private void doAutoWired() {

		for (String className : classNameSet) {
			Class<?> clazz;
			try {
				clazz = Class.forName(className);

				for (Field field : clazz.getDeclaredFields()) {
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
						field.set(
								iocMap.get(clazz.getSimpleName().toLowerCase()),
								iocMap.get(keyName));
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	private void doInstance() {

		for (String className : classNameSet) {
			try {
				Class<?> clazz = Class.forName(className);

				if (clazz.isAnnotationPresent(MyController.class)
						|| clazz.isAnnotationPresent(MyService.class)
						|| clazz.isAnnotationPresent(MyDao.class)) {
					Object object = clazz.newInstance();
					iocMap.put(clazz.getSimpleName().toLowerCase(), object);

					for (Class<?> c : clazz.getInterfaces())
						iocMap.put(c.getSimpleName().toLowerCase(), object);
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
				classNameSet.add(scanPackage + "."
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
