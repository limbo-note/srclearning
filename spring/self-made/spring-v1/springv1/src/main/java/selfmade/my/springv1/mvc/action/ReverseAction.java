package selfmade.my.springv1.mvc.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import selfmade.my.springv1.annotation.MyAutoWired;
import selfmade.my.springv1.annotation.MyController;
import selfmade.my.springv1.annotation.MyRequestMapping;
import selfmade.my.springv1.service.IService;

@MyController
@MyRequestMapping("/reverse")
public class ReverseAction {

	@MyAutoWired
	private IService service;

	@MyRequestMapping("/name")
	public void reverseName(HttpServletRequest request,
			HttpServletResponse response, String name) {

		try {
			StringBuffer sb = new StringBuffer(service.getName(name));
			response.getWriter().write("name= " + sb.reverse().toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
