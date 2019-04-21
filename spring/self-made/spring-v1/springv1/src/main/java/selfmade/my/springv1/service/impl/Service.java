package selfmade.my.springv1.service.impl;

import selfmade.my.springv1.annotation.MyAutoWired;
import selfmade.my.springv1.annotation.MyDao;
import selfmade.my.springv1.annotation.MyService;
import selfmade.my.springv1.dao.IDao;
import selfmade.my.springv1.service.*;

@MyService
public class Service implements IService {

	@MyAutoWired
	private IDao myDao;

	@Override
	public String getName(String name) {

		return myDao.getName(name);
	}

}
