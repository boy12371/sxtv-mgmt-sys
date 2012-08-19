package com.vms.db.dao;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;

import com.vms.common.DaoUtils;
import com.vms.db.bean.Playchangelog;
import com.vms.db.dao.iface.IPlaychangelogDAO;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 * This is an automatically generated DAO class which should not be edited.
 */
public class PlaychangelogDAO extends com.vms.db.dao.BaseRootDAO implements
		IPlaychangelogDAO {

	private Class clz = com.vms.db.bean.Playchangelog.class;

	@Override
	public void deltePlaychangelog(int id) throws Exception {
		// TODO Auto-generated method stub
		this.deleteObject(this.loadObject(clz, id));
	}

	

	
}