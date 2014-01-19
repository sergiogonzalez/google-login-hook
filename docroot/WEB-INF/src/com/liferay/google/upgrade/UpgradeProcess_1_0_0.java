package com.liferay.google.upgrade;

import com.liferay.google.GoogleLoginConstants;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.model.Group;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.expando.DuplicateColumnNameException;
import com.liferay.portlet.expando.DuplicateTableNameException;
import com.liferay.portlet.expando.model.ExpandoColumn;
import com.liferay.portlet.expando.model.ExpandoColumnConstants;
import com.liferay.portlet.expando.model.ExpandoTable;
import com.liferay.portlet.expando.model.ExpandoTableConstants;
import com.liferay.portlet.expando.service.ExpandoColumnLocalServiceUtil;
import com.liferay.portlet.expando.service.ExpandoTableLocalServiceUtil;

/**
 * 
 * UpgradeProcess creates expando column "Google Login JSON Code" of type 
 * ExpandoColumnConstants.STRING to Group entity. If the fields already exists,
 * creation is ommited.
 * 
 * @author krzysiek.golebiowski@gmail.com
 *
 */
public class UpgradeProcess_1_0_0 extends UpgradeProcess {
	
	private static Log _log = LogFactoryUtil.getLog(UpgradeProcess_1_0_0.class);
	
	public static final String EXPANDO_SETTING = 
			"height=105\nwidth=450";
	
	@Override
	protected void doUpgrade() throws Exception {
		
		_log.info("Starting upgrade with "
				+ UpgradeProcess_1_0_0.class.getName());
		
		// Create expando table

		ExpandoTable table = null;
		try {
			table = ExpandoTableLocalServiceUtil.addTable(
				PortalUtil.getDefaultCompanyId(), Group.class.getName(),
				ExpandoTableConstants.DEFAULT_TABLE_NAME);
		} catch (DuplicateTableNameException ex) {
			_log.warn("Expando table has been previously created. "
					+ "Skipping and processing fields creation...");
			table = ExpandoTableLocalServiceUtil.getDefaultTable(
					PortalUtil.getDefaultCompanyId(), Group.class.getName());
		}
		
		// Create expando columns

		try {
			ExpandoColumn exColumn = null;
			
			exColumn = ExpandoColumnLocalServiceUtil.addColumn(
					table.getTableId(), 
					GoogleLoginConstants.EXPANDO_KEY_GOOGLE_LOGIN_JSON_CODE, 
					ExpandoColumnConstants.STRING, 
					"");
			exColumn.setTypeSettings(EXPANDO_SETTING);
			ExpandoColumnLocalServiceUtil.updateExpandoColumn(exColumn);
			_log.info("Created '" + GoogleLoginConstants.EXPANDO_KEY_GOOGLE_LOGIN_JSON_CODE + "' Group EXPANDO field.");
		} catch (DuplicateColumnNameException ex) {
			_log.info("Field '" + GoogleLoginConstants.EXPANDO_KEY_GOOGLE_LOGIN_JSON_CODE + "' has been previously created, skipping...");
		}
		
		_log.info("Finished " + UpgradeProcess_1_0_0.class.getName());
	}
}
