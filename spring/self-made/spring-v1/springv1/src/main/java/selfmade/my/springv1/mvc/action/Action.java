package selfmade.my.springv1.mvc.action;

import selfmade.my.springv1.annotation.MyController;
import selfmade.my.springv1.annotation.MyService;
import selfmade.my.springv1.service.IService;

@MyController
public class Action {

	@MyService
	private IService service;
}
