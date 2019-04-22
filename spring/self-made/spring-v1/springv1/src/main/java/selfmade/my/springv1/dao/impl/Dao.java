package selfmade.my.springv1.dao.impl;

import selfmade.my.springv1.annotation.MyDao;
import selfmade.my.springv1.dao.IDao;

@MyDao
public class Dao implements IDao {

	@Override
	public String getName(String name) {

		return "2";
	}

}
