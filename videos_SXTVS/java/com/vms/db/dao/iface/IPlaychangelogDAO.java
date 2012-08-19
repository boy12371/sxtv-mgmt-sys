package com.vms.db.dao.iface;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.vms.db.bean.Playchangelog;

public interface IPlaychangelogDAO  extends IBaseRootDAO{
	
	void deltePlaychangelog(int id) throws Exception;
	
	

}