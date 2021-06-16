package ch.makery.address.model;

public class EmpVO {
	private int empno;
	private String ename;
	private String job;
	private String mgr;
	private String hiredate;
	private int salary;
	private String comm;
	private int deptno;
	
	public EmpVO() {
		
	}
	public EmpVO(int empno, String ename, String job, String mgr, int salary, String comm, int deptno) {
		this.empno= empno;
		this.ename= ename;
		this.job= job;
		this.mgr= mgr;
		this.salary= salary;
		this.comm= comm;
		this.deptno= deptno;
	}
	
	public EmpVO(int empno, String ename, String job, String mgr, String hiredate, int salary, String comm, int deptno) {
		this.empno= empno;
		this.ename= ename;
		this.job= job;
		this.mgr= mgr;
		this.hiredate= hiredate;
		this.salary= salary;
		this.comm= comm;
		this.deptno= deptno;
	}
	
	public String getComm() {
		return comm;
	}
	
	public void setComm(String comm) {
		this.comm = comm;
	}
	
	
	
	public int getDeptno() {
		return deptno;
	}
	
	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}
	
	
	public int getEmpno() {
		return empno;
	}
	
	public void setEmpno(int empno) {
		this.empno = empno;
	}
	
	
	public String getEname() {
		return ename;
	}
	
	public void setEname(String ename) {
		this.ename = ename;
	}
	
	
	
	public String getHiredate() {
		return hiredate;
	}
	
	public void setHiredate(String hiredate) {
		this.hiredate = hiredate;
	}
	
	
	public String getJob() {
		return job;
	}
	
	public void setJob(String job) {
		this.job = job;
	}
	
	
	public String getMgr() {
		return mgr;
	}
	
	public void setMgr(String mgr) {
		this.mgr = mgr;
	}
	
	
	public int getSalary() {
		return salary;
	}
	
	public void setSalary(int salary) {
		this.salary = salary;
	}
}
