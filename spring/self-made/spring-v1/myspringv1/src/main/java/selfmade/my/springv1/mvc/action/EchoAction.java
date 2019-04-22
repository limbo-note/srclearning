package selfmade.my.springv1.mvc.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import selfmade.my.springv1.annotation.MyAutoWired;
import selfmade.my.springv1.annotation.MyController;
import selfmade.my.springv1.annotation.MyRequestMapping;
import selfmade.my.springv1.annotation.MyService;
import selfmade.my.springv1.service.IService;

@MyController
@MyRequestMapping("/echo")
public class EchoAction {

	@MyAutoWired
	private IService service;

	@MyRequestMapping("/name")
	public void echoName(HttpServletRequest request,
			HttpServletResponse response, String name) {

		try {
			response.getWriter().write("name= " + service.getName(name));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
