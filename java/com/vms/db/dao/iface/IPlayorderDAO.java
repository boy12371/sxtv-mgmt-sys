package com.vms.db.dao.iface;

import java.io.Serializable;

public interface IPlayorderDAO  extends IBaseRootDAO{
	
	
	void deletePlayorder(int id)throws Exception;
}