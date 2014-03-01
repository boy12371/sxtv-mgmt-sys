package com.vms.db.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;

import com.vms.action.accuracy.AccuracyAction;
import com.vms.common.CommonVariable;
import com.vms.db.bean.Role;
import com.vms.db.bean.User;
import com.vms.db.bean.UserRole;
import com.vms.db.bean.Vedioscore;
import com.vms.db.bean.Vediotape;
import com.vms.db.dao.iface.IVedioscoreDAO;


/**
 * This is an automatically generated DAO class which should not be edited.
 */
public class VedioscoreDAO extends com.vms.db.dao.BaseRootDAO implements IVedioscoreDAO {

	public static Class clz = com.vms.db.bean.Vedioscore.class;	
	
	private static Logger logger = Logger.getLogger(VedioscoreDAO.class);
	
	public List<User> findAllExaminer() throws Exception{
		List<User> users = new ArrayList<User>();
		List<UserRole> urs = this.findObjectByField(UserRole.class, "roleid", new Role(3), -1, -1, "roleid", true);
		for(UserRole ur:urs){
			if(ur.getUserid().getStatus()==1 && ur.getUserid().getEmployee().getStatus()==1){
				users.add(ur.getUserid());
			}
		}
		return users;
	}
	
	@SuppressWarnings("unchecked")
	public List<Vedioscore> findScoresOfUserAndTapes(User user, String tapes) throws Exception{
		String hql = "from Vedioscore s where s.examiner="+user.getId()+" and s.vedio.id in ("+tapes+")";
		List<Vedioscore> list = null;
		try{
			Query query = this.getQuery(hql);
			//query.setString("videoIDs", tapes);
			list = query.list();
			//list = this.getHibernateTemplate().find(hql, tapes);
			/*Query query = this.getQuery(hql);
			query.setParameterList("tapes", tapes);
			query.setParameter("user", user);
			list = query.list();
			*/
			
		}catch(Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			list = new ArrayList<Vedioscore>();
		}
		return list;
	}
}