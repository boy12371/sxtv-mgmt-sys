package com.vms.service.iface;

import java.util.Date;
import java.util.List;

import com.vms.beans.VedioTapeVO;

public interface IArrangeService {
	public List<VedioTapeVO> findArrangedTapes(Date month) throws Exception;
}
