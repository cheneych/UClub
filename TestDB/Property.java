package TestDB;

public class Property {
	
	private String ID;
	private String ROWSTAMP;
	private String REFIDTYPE;
	private String REFID;
	private String PROPERTY;
	private String VALUE;
	
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getROWSTAMP() {
		return ROWSTAMP;
	}
	public void setROWSTAMP(String rOWSTAMP) {
		ROWSTAMP = rOWSTAMP;
	}
	public String getREFIDTYPE() {
		return REFIDTYPE;
	}
	public void setREFIDTYPE(String rEFIDTYPE) {
		REFIDTYPE = rEFIDTYPE;
	}
	public String getREFID() {
		return REFID;
	}
	public void setREFID(String rEFID) {
		REFID = rEFID;
	}
	public String getPROPERTY() {
		return PROPERTY;
	}
	public void setPROPERTY(String pROPERTY) {
		PROPERTY = pROPERTY;
	}
	public String getVALUE() {
		return VALUE;
	}
	public void setVALUE(String vALUE) {
		VALUE = vALUE;
	}
	
	@SuppressWarnings("serial")
	public static class PropertyException extends Exception {
		
		public PropertyException(String message) {
			super(message);
		}
		
	}

}
