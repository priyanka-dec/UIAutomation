package dataInfo;

public class AddUserInfo {
	
	private String username;
	private String userRole;
	private String empName;
	private String status;
	private String password;
	private String cpassword;
	
	public AddUserInfo(String username, String userRole, String empName, String status, String password,
			String cpassword) {
		this.username = username;
		this.userRole = userRole;
		this.empName = empName;
		this.status = status;
		this.password = password;
		this.cpassword = cpassword;
	}

	public String getUsername() {
		return username;
	}

	public String getUserRole() {
		return userRole;
	}

	public String getEmpName() {
		return empName;
	}

	public String getStatus() {
		return status;
	}

	public String getPassword() {
		return password;
	}

	public String getCpassword() {
		return cpassword;
	}

	@Override
	public String toString() {
		return "AddUserInfo [username=" + username + ", userRole=" + userRole + ", empName=" + empName + ", status="
				+ status + ", password=" + password + ", cpassword=" + cpassword + "]";
	}
	
	
}
