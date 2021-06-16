package ch.makery.address.model;

import java.util.ArrayList;

public class ProvaDAO {
	public static void main(String[] args) {
		EmpDAO daoProva = new EmpDAO();
		ArrayList<EmpVO> arraylist = daoProva.obtenirTotEMP();

		EmpVO empVOAux;

		for(int i =0 ; i<arraylist.size(); i++) {

			 empVOAux = arraylist.get(i);

			 System.out.print(empVOAux.getEmpno() + " -" + empVOAux.getEname());

			 System.out.println();	

		}

	}
}