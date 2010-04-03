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
		List<UserRole> urs = this.findObjectByField(UserRole.class, "roleid", new Role(CommonVariable.ROLE_EXAMINER), -1, -1, "roleid", true);
		for(UserRole ur:urs){
			if(ur.getUserid().getStatus()==1 && ur.getUserid().getEmployee().getStatus()==1){
				users.add(ur.getUserid());
			}
		}
		return users;
	}
	
	public List<Vedioscore> findScoresOfUserAndTapes(User user, List<Vediotape> tapes) throws Exception{
		String hql = "from Vedioscore s where s.vedio in (:tapes) and s.examiner=(:user)";
		List<Vedioscore> list;
		if(null == tapes && 0 == tapes.size()){
			return new ArrayList<Vedioscore>();
		}
		try{
//			list = this.getHibernateTemplate().find(hql, tapes);
			Query query = this.getQuery(hql);
			query.setParameterList("tapes", tapes);
			query.setParameter("user", user);
			list = query.list();
			
			for(Vedioscore vs:list){
				for(Vediotape vt: tapes){
					if(vs.getVedio().getId().equals(vt.getId())){
						vs.getVedio().setScore(vt.getScore());
						break;
					}
				}
			}
		}catch(Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			list = new ArrayList<Vedioscore>();
		}
		return list;
	}
}