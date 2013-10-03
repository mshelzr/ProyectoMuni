package com.java.util;

import java.util.Iterator;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.jmesa.model.WorksheetSaver;
import org.jmesa.worksheet.Worksheet;
import org.jmesa.worksheet.WorksheetColumn;
import org.jmesa.worksheet.WorksheetRow;
import org.jmesa.worksheet.WorksheetRowStatus;
import org.jmesa.worksheet.editor.CheckboxWorksheetEditor;

import com.java.seguridad.daos.JMesaUMLDAO;

public class WorksheetSaveReusable  implements WorksheetSaver {
    
	private JMesaUMLDAO toListDao;
	private StringBuffer saveResults = new StringBuffer();
	
	public WorksheetSaveReusable (JMesaUMLDAO beansitoDao) {
    	this.toListDao = beansitoDao;
	}
	
	public String getSaveResults() {
	    return saveResults.toString();
	}
	
	public void saveWorksheet(Worksheet worksheet) {
		Iterator<WorksheetRow> worksheetRows = worksheet.getRows().iterator();
		Map<String, ?> beansitosAsMap = toListDao.getAsMap();
		
		while (worksheetRows.hasNext()) {
			boolean valid = true;
			WorksheetRow worksheetRow = worksheetRows.next();
			
			String uniqueValue = worksheetRow.getUniqueProperty().getValue();
			String message = null;

			if (worksheetRow.getRowStatus().equals(WorksheetRowStatus.ADD)) {
				Object newBeansito = toListDao.getNewObjeto();
				
				for (WorksheetColumn worksheetColumn : worksheetRow.getColumns()) {
					valid = setProperty(worksheetColumn, newBeansito) & valid;
				}
				
				if (valid) {
					toListDao.save(newBeansito, true);
					message = getSuccessHtml("Saved new record: " ); //+ newBeansito.getNombre()
				} else {
					message = getErrorHtml("Error: Not saving new record (uniqueValue: " + uniqueValue + ")");
				}

			} else if (worksheetRow.getRowStatus().equals(WorksheetRowStatus.MODIFY)) {
				Object toList = beansitosAsMap.get(uniqueValue);

				for (WorksheetColumn worksheetColumn : worksheetRow.getColumns()) {
					valid = setProperty(worksheetColumn, toList) & valid;
				}

				if (valid) {
					toListDao.save(toList, false);
					message = getSuccessHtml("Saved record: " ); //+ toList.getNombre()
				} else {
					message = getErrorHtml("Error: Not saving record (uniqueValue: " + uniqueValue + ")");
				}

			} else if (worksheetRow.getRowStatus().equals(WorksheetRowStatus.REMOVE)) {
				valid = true;
				Object toList = beansitosAsMap.get(uniqueValue);
				toListDao.delete(toList);
				message = getSuccessHtml("Deleted record (uniqueValue: " + uniqueValue + ")");
			}
			
			if (valid) {
				worksheetRows.remove();
			}

			saveResults.append(message);
		}
	}

	private boolean setProperty (WorksheetColumn worksheetColumn, Object toList) {
		String property = worksheetColumn.getProperty();
		Object changedValue = worksheetColumn.getChangedValue();
		
		// return success for dummy column "remove", which is used to show delete icon
		if (property.equals("remove")) {
			return true;
		}
		
		try {
			//Convirtiendo el Object al valor ingresado
			if(((String) changedValue).contains(".0")){
				changedValue=Double.parseDouble((String) changedValue);
			}
			if(!(changedValue instanceof Double))
				if(((String) changedValue).matches("\\d"))
					changedValue=Integer.parseInt((String) changedValue);
			
			if (worksheetColumn.getProperty().equals("selected")) {
				if (changedValue.equals(CheckboxWorksheetEditor.CHECKED)) {
					PropertyUtils.setProperty(toList, property, 1);
				} else {
					PropertyUtils.setProperty(toList, property, 0);
				}
			} else if (worksheetColumn.getProperty().equals("career")) {
				// validate in API
				if ("foo".equals(changedValue)) {
					worksheetColumn.setError("Enter valid Career");
					return false;
				}
				
				// has to remove if validating in API
				worksheetColumn.removeError();
				PropertyUtils.setProperty(toList, property, changedValue);
			} else {
				// set by javascript validation framework
				if (worksheetColumn.hasError()) {
					return false;
				}
				PropertyUtils.setProperty(toList, property, changedValue);
			}
		} catch (Exception ex) {
            ex.printStackTrace();
			worksheetColumn.setError("Some error occured");
			return false;
		}
		
		return true;
	}

	private String getSuccessHtml(String message) {
		return "<span style=\"color:green\">" + message + "</span><br/>";
	}

	private String getErrorHtml(String message) {
		return "<span style=\"color:red\">" + message + "</span><br/>";
	}
}
