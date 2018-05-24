package com.bizleap.ds.service.impl;

import com.bizleap.commons.domain.enums.EntityType;
import com.bizleap.ds.service.AbstractService;

public abstract class AbstractServiceImpl implements AbstractService {

	public abstract long getCount();

	public String makeBoId(String prefix,int currentObjCount) {
		if(currentObjCount<10)
			return prefix+"0000"+currentObjCount;
		if(currentObjCount<100 && currentObjCount>9)
			return prefix+"000"+currentObjCount;
		if(currentObjCount<1000 && currentObjCount>99)
			return prefix+"00"+currentObjCount;
		return prefix+"0"+currentObjCount;
	}
	
	public String makeBoId(EntityType entityType, int currentObjectCount) {
		return makeBoId(entityType.getBoIdPrefix(),currentObjectCount);
	}
	
	public String getNextBoId(EntityType entityType) {
		return makeBoId(entityType,(int)getCount()+1);
	}
}
