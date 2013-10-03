package com.java.factoria;

import com.java.cuadrocomp.daos.CuadroCompDAO;
import com.java.expediente.daos.ExpedienteCompraDAO;
import com.java.formato.daos.DetallePacDAO;
import com.java.formato.daos.FormatoRequerimientoDAO;
import com.java.ordencompra.daos.OrdenCompraDAO;
import com.java.saldopresup.daos.SaldoPresupDAO;
import com.java.seguridad.daos.AuditoriaDAO;
import com.java.seguridad.daos.HbnAuditoriaDao;
import com.java.seguridad.daos.HbnCuadroCompDao;
import com.java.seguridad.daos.HbnDetallePacDao;
import com.java.seguridad.daos.HbnExpedienteCompraDao;
import com.java.seguridad.daos.HbnFormatoRequerimientoDao;
import com.java.seguridad.daos.HbnMenuDao;
import com.java.seguridad.daos.HbnOrdenCompraDao;
import com.java.seguridad.daos.HbnPerfilDao;
import com.java.seguridad.daos.HbnProveedorDao;
import com.java.seguridad.daos.HbnSaldoPresupDao;
import com.java.seguridad.daos.HbnSolicitudCotizDao;
import com.java.seguridad.daos.HbnUsuarioDAO;
import com.java.seguridad.daos.MenuDAO;
import com.java.seguridad.daos.PerfilDAO;
import com.java.seguridad.daos.UsuarioDAO;
import com.java.solicitudcotiz.daos.ProveedorDAO;
import com.java.solicitudcotiz.daos.SolicitudCotizDAO;

public class HbnDAOFactory extends DAOFactory {

	@Override
	public UsuarioDAO getUsuarioDAO() {
		return new HbnUsuarioDAO();
	}

	@Override
	public FormatoRequerimientoDAO getFormatoRequerimientoDAO() {
		return new HbnFormatoRequerimientoDao();
	}

	@Override
	public ExpedienteCompraDAO getExpedienteCompraDAO() {
		return new HbnExpedienteCompraDao();
	}

	@Override
	public CuadroCompDAO getCuadroCompDAO() {
		return new HbnCuadroCompDao();
	}

	@Override
	public OrdenCompraDAO getOrdenCompraDAO() {
		return new HbnOrdenCompraDao();
	}

	@Override
	public SaldoPresupDAO getSaldoPresupDAO() {
		return new HbnSaldoPresupDao();
	}

	@Override
	public SolicitudCotizDAO getSolicitudCotizDAO() {
		return new HbnSolicitudCotizDao();
	}

	@Override
	public MenuDAO getMenuDao() {
		return new HbnMenuDao();
	}

	@Override
	public PerfilDAO getPerfilDao() {
		return new HbnPerfilDao();
	}

	@Override
	public DetallePacDAO getDetallePacDao() {
		return new HbnDetallePacDao();
	}

	@Override
	public ProveedorDAO getProveedorDao() {
		return new HbnProveedorDao();
	}

	@Override
	public AuditoriaDAO getAuditoriaDAO() {
		return new HbnAuditoriaDao();
	}

}
