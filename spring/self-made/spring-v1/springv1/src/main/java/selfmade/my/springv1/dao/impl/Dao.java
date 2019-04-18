package selfmade.my.springv1.dao.impl;

import selfmade.my.springv1.dao.IDao;

public class Dao implements IDao {

	@Override
	public String getName(String name) {

		return "name = " + name;
	}

}
