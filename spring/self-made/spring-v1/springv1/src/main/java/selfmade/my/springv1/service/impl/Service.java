package selfmade.my.springv1.service.impl;

import selfmade.my.springv1.annotation.MyDao;
import selfmade.my.springv1.dao.IDao;
import selfmade.my.springv1.service.*;

public class Service implements IService {

	@MyDao
	private IDao myDao;

	@Override
	public String getName(String name) {

		return myDao.getName(name);
	}

}
