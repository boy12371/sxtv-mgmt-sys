package com.vms.service.iface;

import java.util.List;

import com.vms.db.bean.Resources;

public interface IResourceService {
	List<Resources> findAllResources()throws Exception;
}
