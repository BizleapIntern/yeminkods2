package com.bizleap.ds.loader;

public interface AssociationMapper {
		public void buildAssociations();
		public void handleLinkageError(String message, Object source);
		public void handleLinkageError(String message, Object source, Object issue);
		public void reportError(String message, Object object);
		public void reportError(String message, Object object, Object issue);
}
