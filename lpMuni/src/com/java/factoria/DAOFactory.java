/*
 * Created on 01/10/2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.java.factoria;

import com.java.cuadrocomp.daos.CuadroCompDAO;
import com.java.expediente.daos.ExpedienteCompraDAO;
import com.java.formato.daos.DetallePacDAO;
import com.java.formato.daos.FormatoRequerimientoDAO;
import com.java.ordencompra.daos.OrdenCompraDAO;
import com.java.saldopresup.daos.SaldoPresupDAO;
import com.java.seguridad.daos.AuditoriaDAO;
import com.java.seguridad.daos.MenuDAO;
import com.java.seguridad.daos.PerfilDAO;
import com.java.seguridad.daos.UsuarioDAO;
import com.java.solicitudcotiz.daos.ProveedorDAO;
import com.java.solicitudcotiz.daos.SolicitudCotizDAO;

public abstract class DAOFactory {
    public static final int MYSQL = 1;
    public static final int ORACLE = 2;
    public static final int DB2 = 3;
    public static final int SQLSERVER = 4;
    public static final int XML = 5;
    public static final int HIBERNATE = 6;
    
    // Existirá un método get por cada DAO que exista en el sistema
    // Ejemplo:
    //public abstract ArticuloDAO getArticuloDAO();

    // registramos nuestros daos
    public abstract UsuarioDAO getUsuarioDAO();
    public abstract FormatoRequerimientoDAO getFormatoRequerimientoDAO();
    public abstract ExpedienteCompraDAO getExpedienteCompraDAO();
    public abstract CuadroCompDAO getCuadroCompDAO();
    public abstract OrdenCompraDAO getOrdenCompraDAO();
    public abstract SaldoPresupDAO getSaldoPresupDAO();
    public abstract SolicitudCotizDAO getSolicitudCotizDAO();
    public abstract MenuDAO getMenuDao();
    public abstract PerfilDAO getPerfilDao();
    public abstract DetallePacDAO getDetallePacDao();
    public abstract ProveedorDAO getProveedorDao();
    public abstract AuditoriaDAO getAuditoriaDAO();
    
    public static DAOFactory getDAOFactory(int whichFactory){
       switch(whichFactory){
      	case MYSQL:
       	    return null;
       	case HIBERNATE:
       	    return new HbnDAOFactory();
       	case ORACLE:
       	    //return new OracleDAOFactory();
       	/*case DB2:
       	    return new Db2DAOFactory();
       	case SQLSERVER:
       	    return new SqlServerDAOFactory();
       	case XML:
       	    return new XmlDAOFactory();*/
       	default:
       	    return null;
       }
    }
    
}
