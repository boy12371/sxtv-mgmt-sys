package com.vms.db.bean;

import com.vms.db.bean.base.BaseAudienceScorePK;

public class AudienceScorePK extends BaseAudienceScorePK {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public AudienceScorePK () {}
	
	public AudienceScorePK (
		com.vms.db.bean.Audience audienceID,
		com.vms.db.bean.Vediotape vedioID) {

		super (
			audienceID,
			vedioID);
	}
/*[CONSTRUCTOR MARKER END]*/


}