package dataInfo;


public class EmployeeSearchInfo {
	
	private String userName;
	private String userRole;
	private String empNameFull;
	private String empNamePart;
	private String empStatus;
	private String expectedRowCount;

	
	public EmployeeSearchInfo(String userName, String userRole, String empNameFull, String empNamePart,
			String empStatus, String expectedRowCount) {

		this.userName = userName;
		this.userRole = userRole;
		this.empNameFull = empNameFull;
		this.empNamePart = empNamePart;
		this.empStatus = empStatus;
		this.expectedRowCount = expectedRowCount;
	}
	
	public String getUserName() {
		return userName;
	}
	public String getUserRole() {
		return userRole;
	}
	public String getEmpNameFull() {
		return empNameFull;
	}
	public String getEmpNamePart() {
		return empNamePart;
	}
	public String getEmpStatus() {
		return empStatus;
	}
	public String getExpectedRowCount() {
		return expectedRowCount;
	}

	@Override
	public String toString() {
		return "EmployeeSearchInfo [userName=" + userName + ", userRole=" + userRole + ", empNameFull=" + empNameFull
				+ ", empNamePart=" + empNamePart + ", empStatus=" + empStatus + ", expectedRowCount=" + expectedRowCount
				+ "]";
	}
	

}
