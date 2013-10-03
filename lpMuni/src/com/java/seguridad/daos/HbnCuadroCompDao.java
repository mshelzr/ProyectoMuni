package com.java.seguridad.daos;

import org.hibernate.Query;
import org.hibernate.Session;

import com.java.beans.CuadroComparativoDTO;
import com.java.cuadrocomp.daos.CuadroCompDAO;
import com.java.util.HbnConexion;

public class HbnCuadroCompDao implements CuadroCompDAO {

	@Override
	public void adjuntaCuadroCompDAO(CuadroComparativoDTO cc) {
		Session s=HbnConexion.getSessionFactory().getCurrentSession();
		s.beginTransaction();
		s.save(cc);
		s.getTransaction().commit();
	}

	@Override
	public String obtenerUrlCuadroComparativo(int codExpediente) {
		Session s=HbnConexion.getSessionFactory().getCurrentSession();
		s.beginTransaction();
		Query q=s.createQuery("SELECT cc.url FROM CuadroComparativoDTO cc JOIN cc.expedienteCompraDTO ec " +
				  "WHERE ec.codExpediente=:codExpediente")
				  .setParameter("codExpediente", codExpediente);
		String url=(String)q.uniqueResult();
		s.getTransaction().commit();
		return url;
	}

}
