package com.java.seguridad.daos;

import org.hibernate.Query;
import org.hibernate.Session;

import com.java.saldopresup.daos.SaldoPresupDAO;
import com.java.util.HbnConexion;

public class HbnSaldoPresupDao implements SaldoPresupDAO {

	@Override
	public double getValorDelPacEnUitPorFormato(int codFormato) {
		Session s=HbnConexion.getSessionFactory().getCurrentSession();
		s.beginTransaction();
		Query q=s.createQuery("SELECT ROUND(dpac.valorRef/(SELECT s.cantidad FROM SaldoPresupuestalDTO s WHERE s.codSaldoPresupuestal=2),1) " +
							  "FROM DetallePacDTO dpac JOIN dpac.formatoRequerimientoDTOs fr " +
							  "WHERE fr.codFormato=:codFormato");
		q.setParameter("codFormato", codFormato);
		double valuePacEnUit=(double)q.uniqueResult();
		s.getTransaction().commit();
		
		return valuePacEnUit;
	}

	@Override
	public double getRangoUnoDeUit() {
		Session s=HbnConexion.getSessionFactory().getCurrentSession();
		s.beginTransaction();
		double rangoUno=(double)s.createQuery("SELECT s.cantidad " +
											  "FROM SaldoPresupuestalDTO s " +
											  "WHERE s.codSaldoPresupuestal=3")
											  .uniqueResult();
		s.getTransaction().commit();
		return rangoUno;
	}

	@Override
	public double getRangoDosDeUit() {
		Session s=HbnConexion.getSessionFactory().getCurrentSession();
		s.beginTransaction();
		double rangoDos=(double)s.createQuery("SELECT s.cantidad " +
				  "FROM SaldoPresupuestalDTO s " +
				  "WHERE s.codSaldoPresupuestal=4")
				  .uniqueResult();
		s.getTransaction().commit();
		return rangoDos;
	}

	@Override
	public double getSaldoBase() {
		Session s=HbnConexion.getSessionFactory().getCurrentSession();
		s.beginTransaction();
		double montoBase=(double)s.createQuery("SELECT s.cantidad " +
				  "FROM SaldoPresupuestalDTO s " +
				  "WHERE s.codSaldoPresupuestal=1")
				  .uniqueResult();
		s.getTransaction().commit();
		return montoBase;
	}

}
