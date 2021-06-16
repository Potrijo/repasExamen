package ch.makery.address.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class EmpDAO {	
	private Connection connection = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private EmpDAO updater;

	private Connection getConnection() throws SQLException {
		Connection conn;
		ConnectionDB connDB = new ConnectionDB();
		conn = connDB.connection();
		return conn;
	}

	public ArrayList <EmpVO> obtenirTotEMP(){
		EmpVO empVOAux;
		ArrayList <EmpVO> empVO = null;
		try {
			empVO = new ArrayList<EmpVO>();
			connection = getConnection();
			ps = connection.prepareStatement("SELECT * FROM emp");
			rs = ps.executeQuery();
			System.out.println("Connected Successfully");

			while(rs.next()) {
				empVOAux = new EmpVO();
				empVOAux.setEmpno(Integer.parseInt(rs.getString(1)));
				empVOAux.setEname(rs.getString(2));
				empVOAux.setJob(rs.getString(3));
				empVOAux.setMgr(rs.getString(4));
				empVOAux.setHiredate(rs.getString(5));
				empVOAux.setSalary(Integer.parseInt(rs.getString(6)));
				empVOAux.setComm(rs.getString(7));
				empVOAux.setDeptno(Integer.parseInt(rs.getString(8)));
				empVO.add(empVOAux);
			}
			return empVO;
		} catch (SQLException e) {
		} finally {
			try {

				if(rs != null) rs.close();
				if(ps != null) ps.close();
				if(connection != null) connection.close();

			} catch (SQLException e) {
				System.out.println("SQLException ERROR");
			} 
			catch (Exception e) {
				System.out.println("ERROR");
			} 
		}
		return empVO;
	}

	public void deleteEMP(EmpVO employee) {
		try {
			connection = getConnection();
			PreparedStatement statementENAME = connection.prepareStatement("DELETE FROM emp WHERE EMPNO = ?");
			statementENAME.setInt(1, employee.getEmpno());
			statementENAME.executeUpdate();
			updater = new EmpDAO();
			updater.obtenirTotEMP();
		} 
		catch (SQLException e) {

		} 
		finally 
		{
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
				if(connection != null) connection.close();
			} catch (SQLException e) {
				System.out.println("SQLException ERROR");
			} 
			catch (Exception e) {
				System.out.println("ERROR");
			} 
		}
	}

	
	public void insertEMP(EmpVO employee) {
		try {
			connection = getConnection();
			PreparedStatement statementENAME = connection.prepareStatement("INSERT INTO "
					+ "emp(EMPNO, ENAME, JOB, MGR, HIREDATE, SALARY, COMM, DEPTNO) "
					+ "VALUES(?, ?, ?, ?, CURDATE(), ?, ?, ?)");
			
			statementENAME.setInt(1, employee.getEmpno());
			statementENAME.setString(2, employee.getEname());
			statementENAME.setString(3, employee.getJob());
			statementENAME.setString(4, employee.getMgr());
			statementENAME.setInt(5, employee.getSalary());
			statementENAME.setString(6, employee.getComm());
			statementENAME.setInt(7, employee.getDeptno());
			statementENAME.executeUpdate();
			updater = new EmpDAO();
			updater.obtenirTotEMP();
		} catch (SQLException e) {
		} finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
				if(connection != null) connection.close();
			} catch (SQLException e) {
				System.out.println("SQLException ERROR");
			} 
			catch (Exception e) {
				System.out.println("ERROR");
			} 
		}
	}
	 
}
