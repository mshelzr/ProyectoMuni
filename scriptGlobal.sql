--WARNING:PEGAR LAS CARPETAS FOTOS Y FIRMAS EN C:\\ 			EXAMPLE: C:\\FOTOS\ or C:\\FIRMAS\
--

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;

SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;

SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';



CREATE SCHEMA IF NOT EXISTS `bdmuni` DEFAULT CHARACTER SET latin1 ;

USE `bdmuni` ;



-- -----------------------------------------------------

-- Table `bdmuni`.`actividades`

-- -----------------------------------------------------

CREATE  TABLE IF NOT EXISTS `bdmuni`.`actividades` (

  `codActividades` INT(11) NOT NULL ,

  `descripcion` VARCHAR(150) NULL DEFAULT NULL ,

  PRIMARY KEY (`codActividades`) )

ENGINE = InnoDB

DEFAULT CHARACTER SET = latin1;





-- -----------------------------------------------------

-- Table `bdmuni`.`detalle_pac`

-- -----------------------------------------------------

CREATE  TABLE IF NOT EXISTS `bdmuni`.`detalle_pac` (

  `codDetallePac` INT(11) NOT NULL AUTO_INCREMENT ,

  `objeto` VARCHAR(155) NULL DEFAULT NULL ,

  `valorRef` DOUBLE NULL DEFAULT NULL ,

  `fechaConvocatoria` VARCHAR(45) NULL DEFAULT NULL ,

  PRIMARY KEY (`codDetallePac`) )

ENGINE = InnoDB

AUTO_INCREMENT = 15

DEFAULT CHARACTER SET = latin1;





-- -----------------------------------------------------

-- Table `bdmuni`.`perfil`

-- -----------------------------------------------------

CREATE  TABLE IF NOT EXISTS `bdmuni`.`perfil` (

  `codPerfil` INT(11) NOT NULL AUTO_INCREMENT ,

  `descripcion` VARCHAR(45) NULL DEFAULT NULL ,

  PRIMARY KEY (`codPerfil`) )

ENGINE = InnoDB

AUTO_INCREMENT = 10

DEFAULT CHARACTER SET = latin1;





-- -----------------------------------------------------

-- Table `bdmuni`.`tbusuario`

-- -----------------------------------------------------

CREATE  TABLE IF NOT EXISTS `bdmuni`.`tbusuario` (

  `codUsuario` INT(11) NOT NULL AUTO_INCREMENT ,

  `nombre` VARCHAR(45) NULL DEFAULT NULL ,

  `ape_paterno` VARCHAR(45) NULL DEFAULT NULL ,

  `ape_materno` VARCHAR(45) NULL DEFAULT NULL ,

  `usuario` VARCHAR(45) NULL DEFAULT NULL ,

  `password` VARCHAR(45) NULL DEFAULT NULL ,

  `foto` BLOB NULL DEFAULT NULL ,

  `firma` BLOB NULL DEFAULT NULL ,

  `codPerfil` INT(11) NOT NULL ,

  PRIMARY KEY (`codUsuario`, `codPerfil`) ,

  INDEX `fk_tbusuario_perfil1_idx` (`codPerfil` ASC) ,

  CONSTRAINT `fk_tbusuario_perfil1`

    FOREIGN KEY (`codPerfil` )

    REFERENCES `bdmuni`.`perfil` (`codPerfil` )

    ON DELETE NO ACTION

    ON UPDATE NO ACTION)

ENGINE = InnoDB

AUTO_INCREMENT = 10

DEFAULT CHARACTER SET = latin1;





-- -----------------------------------------------------

-- Table `bdmuni`.`formatorequerimiento`

-- -----------------------------------------------------

CREATE  TABLE IF NOT EXISTS `bdmuni`.`formatorequerimiento` (

  `codFormato` INT(11) NOT NULL AUTO_INCREMENT ,

  `codUsuario` INT(11) NOT NULL ,

  `fecha` DATE NULL DEFAULT NULL ,

  `tipo` VARCHAR(45) NULL DEFAULT 'Bienes' ,

  `descripcion` VARCHAR(145) NULL DEFAULT NULL ,

  `codDetallePac` INT(11) NOT NULL ,

  PRIMARY KEY (`codFormato`, `codUsuario`, `codDetallePac`) ,

  INDEX `fk_formatorequerimiento_usuario1_idx` (`codUsuario` ASC) ,

  INDEX `fk_formatorequerimiento_detalle_pac1_idx` (`codDetallePac` ASC) ,

  CONSTRAINT `fk_formatorequerimiento_detalle_pac1`

    FOREIGN KEY (`codDetallePac` )

    REFERENCES `bdmuni`.`detalle_pac` (`codDetallePac` )

    ON DELETE NO ACTION

    ON UPDATE NO ACTION,

  CONSTRAINT `fk_formatorequerimiento_usuario1`

    FOREIGN KEY (`codUsuario` )

    REFERENCES `bdmuni`.`tbusuario` (`codUsuario` )

    ON DELETE NO ACTION

    ON UPDATE NO ACTION)

ENGINE = InnoDB

AUTO_INCREMENT = 29

DEFAULT CHARACTER SET = latin1;





-- -----------------------------------------------------

-- Table `bdmuni`.`expedientecompra`

-- -----------------------------------------------------

CREATE  TABLE IF NOT EXISTS `bdmuni`.`expedientecompra` (

  `codExpediente` INT(11) NOT NULL AUTO_INCREMENT ,

  `descripcion` VARCHAR(145) NULL DEFAULT NULL ,

  `fecha` DATE NULL DEFAULT NULL ,

  `codFormato` INT(11) NOT NULL ,

  PRIMARY KEY (`codExpediente`, `codFormato`) ,

  INDEX `fk_expedientecompra_formatorequerimiento1_idx` (`codFormato` ASC) ,

  CONSTRAINT `fk_expedientecompra_formatorequerimiento1`

    FOREIGN KEY (`codFormato` )

    REFERENCES `bdmuni`.`formatorequerimiento` (`codFormato` )

    ON DELETE NO ACTION

    ON UPDATE NO ACTION)

ENGINE = InnoDB

AUTO_INCREMENT = 16

DEFAULT CHARACTER SET = latin1;





-- -----------------------------------------------------

-- Table `bdmuni`.`cuadrocomparativo`

-- -----------------------------------------------------

CREATE  TABLE IF NOT EXISTS `bdmuni`.`cuadrocomparativo` (

  `codCuadroComparativo` INT(11) NOT NULL AUTO_INCREMENT ,

  `url` VARCHAR(100) NULL DEFAULT NULL ,

  `fecha` DATE NULL DEFAULT NULL ,

  `codExpediente` INT(11) NOT NULL ,

  PRIMARY KEY (`codCuadroComparativo`, `codExpediente`) ,

  INDEX `fk_cuadrocomparativo_expediente1_idx` (`codExpediente` ASC) ,

  CONSTRAINT `fk_cuadrocomparativo_expediente1`

    FOREIGN KEY (`codExpediente` )

    REFERENCES `bdmuni`.`expedientecompra` (`codExpediente` )

    ON DELETE NO ACTION

    ON UPDATE NO ACTION)

ENGINE = InnoDB

AUTO_INCREMENT = 53

DEFAULT CHARACTER SET = latin1;





-- -----------------------------------------------------

-- Table `bdmuni`.`proveedor`

-- -----------------------------------------------------

CREATE  TABLE IF NOT EXISTS `bdmuni`.`proveedor` (

  `codProveedor` INT(11) NOT NULL AUTO_INCREMENT ,

  `nombre` VARCHAR(45) NULL DEFAULT NULL ,

  `ruc` INT(11) NULL DEFAULT NULL ,

  `telefono` INT(11) NULL DEFAULT NULL ,

  `correo` VARCHAR(45) NULL DEFAULT NULL ,

  PRIMARY KEY (`codProveedor`) )

ENGINE = InnoDB

AUTO_INCREMENT = 5

DEFAULT CHARACTER SET = latin1;





-- -----------------------------------------------------

-- Table `bdmuni`.`solicitudcotizacion`

-- -----------------------------------------------------

CREATE  TABLE IF NOT EXISTS `bdmuni`.`solicitudcotizacion` (

  `codSolicitudCotizacion` INT(11) NOT NULL AUTO_INCREMENT ,

  `url` VARCHAR(100) NULL DEFAULT NULL ,

  `fecha` DATE NULL DEFAULT NULL ,

  `codExpediente` INT(11) NOT NULL ,

  PRIMARY KEY (`codSolicitudCotizacion`, `codExpediente`) ,

  INDEX `fk_SolicitudCotizacion_Expediente1_idx` (`codExpediente` ASC) ,

  CONSTRAINT `fk_SolicitudCotizacion_Expediente1`

    FOREIGN KEY (`codExpediente` )

    REFERENCES `bdmuni`.`expedientecompra` (`codExpediente` )

    ON DELETE NO ACTION

    ON UPDATE NO ACTION)

ENGINE = InnoDB

AUTO_INCREMENT = 68

DEFAULT CHARACTER SET = latin1;





-- -----------------------------------------------------

-- Table `bdmuni`.`detalle_proveedorsolicitudcotiz`

-- -----------------------------------------------------

CREATE  TABLE IF NOT EXISTS `bdmuni`.`detalle_proveedorsolicitudcotiz` (

  `codProveedor` INT(11) NOT NULL ,

  `codSolicitudCotizacion` INT(11) NOT NULL ,

  `elegido` VARCHAR(45) NULL DEFAULT NULL ,

  PRIMARY KEY (`codProveedor`, `codSolicitudCotizacion`) ,

  INDEX `fk_detalle_proveedorexpediente_solicitudcotizacion1_idx` (`codSolicitudCotizacion` ASC) ,

  CONSTRAINT `fk_detalle_ProveedorExpediente_Proveedor1`

    FOREIGN KEY (`codProveedor` )

    REFERENCES `bdmuni`.`proveedor` (`codProveedor` )

    ON DELETE NO ACTION

    ON UPDATE NO ACTION,

  CONSTRAINT `fk_detalle_proveedorexpediente_solicitudcotizacion1`

    FOREIGN KEY (`codSolicitudCotizacion` )

    REFERENCES `bdmuni`.`solicitudcotizacion` (`codSolicitudCotizacion` )

    ON DELETE NO ACTION

    ON UPDATE NO ACTION)

ENGINE = InnoDB

DEFAULT CHARACTER SET = latin1;





-- -----------------------------------------------------

-- Table `bdmuni`.`detalle_trabajadorexpediente`

-- -----------------------------------------------------

CREATE  TABLE IF NOT EXISTS `bdmuni`.`detalle_trabajadorexpediente` (

  `codExpediente` INT(11) NOT NULL ,

  `codUsuario` INT(11) NOT NULL ,

  `posicionEXP` INT(1) NOT NULL ,

  `aprobado` VARCHAR(45) NULL DEFAULT NULL ,

  `firmado` VARCHAR(45) NULL DEFAULT NULL ,

  PRIMARY KEY (`codExpediente`, `codUsuario`, `posicionEXP`) ,

  INDEX `fk_detalle_trabajadorexpediente_expedientecompra1_idx` (`codExpediente` ASC) ,

  INDEX `fk_detalle_trabajadorexpediente_tbusuario1_idx` (`codUsuario` ASC) ,

  CONSTRAINT `fk_detalle_trabajadorexpediente_expedientecompra1`

    FOREIGN KEY (`codExpediente` )

    REFERENCES `bdmuni`.`expedientecompra` (`codExpediente` )

    ON DELETE NO ACTION

    ON UPDATE NO ACTION,

  CONSTRAINT `fk_detalle_trabajadorexpediente_tbusuario1`

    FOREIGN KEY (`codUsuario` )

    REFERENCES `bdmuni`.`tbusuario` (`codUsuario` )

    ON DELETE NO ACTION

    ON UPDATE NO ACTION)

ENGINE = InnoDB

DEFAULT CHARACTER SET = latin1;





-- -----------------------------------------------------

-- Table `bdmuni`.`detalle_trabajadorformato`

-- -----------------------------------------------------

CREATE  TABLE IF NOT EXISTS `bdmuni`.`detalle_trabajadorformato` (

  `codFormato` INT(11) NOT NULL ,

  `codUsuario` INT(11) NOT NULL ,

  `posicionFR` INT(1) NULL DEFAULT NULL ,

  `aprobado` VARCHAR(45) NULL DEFAULT NULL ,

  `firmado` VARCHAR(45) NULL DEFAULT NULL ,

  PRIMARY KEY (`codFormato`, `codUsuario`) ,

  INDEX `fk_detalle_trabajadorformato_usuario1_idx` (`codUsuario` ASC) ,

  CONSTRAINT `fk_detalle_TrabajadorFormato_FormatoRequerimiento1`

    FOREIGN KEY (`codFormato` )

    REFERENCES `bdmuni`.`formatorequerimiento` (`codFormato` )

    ON DELETE NO ACTION

    ON UPDATE NO ACTION,

  CONSTRAINT `fk_detalle_trabajadorformato_usuario1`

    FOREIGN KEY (`codUsuario` )

    REFERENCES `bdmuni`.`tbusuario` (`codUsuario` )

    ON DELETE NO ACTION

    ON UPDATE NO ACTION)

ENGINE = InnoDB

DEFAULT CHARACTER SET = latin1;





-- -----------------------------------------------------

-- Table `bdmuni`.`menu`

-- -----------------------------------------------------

CREATE  TABLE IF NOT EXISTS `bdmuni`.`menu` (

  `codMenu` INT(11) NOT NULL AUTO_INCREMENT ,

  `descripcion` VARCHAR(99) NULL DEFAULT NULL ,

  `direccion` VARCHAR(99) NULL DEFAULT NULL ,

  `codPerfil` INT(11) NOT NULL ,

  PRIMARY KEY (`codMenu`, `codPerfil`) ,

  INDEX `fk_menu_perfil1_idx` (`codPerfil` ASC) ,

  CONSTRAINT `fk_menu_perfil1`

    FOREIGN KEY (`codPerfil` )

    REFERENCES `bdmuni`.`perfil` (`codPerfil` )

    ON DELETE NO ACTION

    ON UPDATE NO ACTION)

ENGINE = InnoDB

AUTO_INCREMENT = 26

DEFAULT CHARACTER SET = latin1;





-- -----------------------------------------------------

-- Table `bdmuni`.`ordencompra`

-- -----------------------------------------------------

CREATE  TABLE IF NOT EXISTS `bdmuni`.`ordencompra` (

  `ordencompra` INT(11) NOT NULL AUTO_INCREMENT ,

  `url` VARCHAR(100) NULL DEFAULT NULL ,

  `fecha` DATE NULL DEFAULT NULL ,

  `codExpediente` INT(11) NOT NULL ,

  PRIMARY KEY (`ordencompra`, `codExpediente`) ,

  INDEX `fk_ordencompra_expediente1_idx` (`codExpediente` ASC) ,

  CONSTRAINT `fk_ordencompra_expediente1`

    FOREIGN KEY (`codExpediente` )

    REFERENCES `bdmuni`.`expedientecompra` (`codExpediente` )

    ON DELETE NO ACTION

    ON UPDATE NO ACTION)

ENGINE = InnoDB

AUTO_INCREMENT = 17

DEFAULT CHARACTER SET = latin1;





-- -----------------------------------------------------

-- Table `bdmuni`.`saldopresupuestal`

-- -----------------------------------------------------

CREATE  TABLE IF NOT EXISTS `bdmuni`.`saldopresupuestal` (

  `codSaldoPresupuestal` INT(11) NOT NULL AUTO_INCREMENT ,

  `descripcion` VARCHAR(45) NULL DEFAULT NULL ,

  `cantidad` DOUBLE(9,2) NULL DEFAULT NULL ,

  PRIMARY KEY (`codSaldoPresupuestal`) )

ENGINE = InnoDB

AUTO_INCREMENT = 5

DEFAULT CHARACTER SET = latin1;





-- -----------------------------------------------------

-- Table `bdmuni`.`tbauditoria`

-- -----------------------------------------------------

CREATE  TABLE IF NOT EXISTS `bdmuni`.`tbauditoria` (

  `codAuditoria` INT(11) NOT NULL AUTO_INCREMENT ,

  `fechaLogin` DATETIME NULL DEFAULT NULL ,

  `fechaLogout` DATETIME NULL DEFAULT NULL ,

  `usuario` VARCHAR(45) NULL DEFAULT NULL ,

  `hostname` VARCHAR(45) NULL DEFAULT NULL ,

  `tiempo` VARCHAR(45) NULL DEFAULT NULL ,

  PRIMARY KEY (`codAuditoria`) )

ENGINE = InnoDB

AUTO_INCREMENT = 121

DEFAULT CHARACTER SET = latin1;







SET SQL_MODE=@OLD_SQL_MODE;

SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;

SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


insert into perfil values(1,'Representante Area Solicitante');
insert into perfil values(2,'Gerente y/o Jefe Superior');
insert into perfil values(3,'Técnico de Presupuesto');
insert into perfil values(4,'Gerente de Administración General');
insert into perfil values(5,'Jefe Unidad Logistica');
insert into perfil values(6,'Tecnico Administrativo');
insert into perfil values(7,'Tecnico SNP');
insert into perfil values(8,'Tecnico Contable');
insert into perfil values(9,'Administrador');

insert into tbusuario values(1, 'Juan', 'Perez', 'Casas', 'representante', '111',load_file('c:/fotos/1.jpg'), LOAD_FILE('C:/firmas/firma1.jpg'), '1');
insert into tbusuario values(2, 'Josué', 'Centeno', 'Cárdenas', 'gerente', '111',load_file('c:/fotos/2.jpg'), LOAD_FILE('C:/firmas/firma2.jpg'), '2');
insert into tbusuario values(3, 'Lili', 'Morales', 'Muñoz', 'tecnicopresup','111',load_file('c:/fotos/3.jpg'),load_file('c:/firmas/firma3.jpg'), '3');
insert into tbusuario values(4, 'Roberto', 'Tello', 'Peramás', 'gerenteadm', '111',load_file('c:/fotos/4.jpg'),load_file('c:/firmas/firma4.jpg'), '4');
insert into tbusuario values(5, 'Alfonso', 'Vasquéz', 'Osorio', 'jefeul', '111',load_file('c:/fotos/5.jpg'),load_file('c:/firmas/firma5.jpg'), '5');
insert into tbusuario values(6, 'Demetrio José', 'Yachi', 'Del Pino', 'tecnicoadm', '111',load_file('c:/fotos/6.jpg'),load_file('c:/firmas/firma6.jpg'), '6');
insert into tbusuario values(7, 'Karina M.', 'Tello', 'Romero', 'tecnicosnp', '111',load_file('c:/fotos/7.jpg'),load_file('c:/firmas/firma7.jpg'), '7');
insert into tbusuario values(8, 'Leoncio', 'Fernandez', 'Jeri', 'tecnicocontable', '111',load_file('c:/fotos/8.jpg'),load_file('c:/firmas/firma8.jpg'), '8');
insert into tbusuario values(9, 'Funny', 'Gonchales', 'Jeri', 'adm', '111',load_file('c:/fotos/9.jpg'),load_file('c:/firmas/firma9.jpg'), '9');

insert into menu values(1,'Generar Formato','generarFormato.jsp',1);
insert into menu values(2,'Firmar Autorizacion de Formato Req','FormatoServlet?operacion=grillaformato&vposicion=0',2);
insert into menu values(3,'Consultar Saldo Presup.<br/> y dar de alta al Formato Req','FormatoServlet?operacion=grillaformato&vposicion=1',3);
insert into menu values(4,'Verificar Formato Req si<br/> esta dentro del PAAC','FormatoServlet?operacion=grillaformato&vposicion=2',4);
insert into menu values(5,'Verificar Datos Correctos<br/> en el Formato de Requerimiento','FormatoServlet?operacion=grillaformato&vposicion=3',5);
insert into menu values(6,'Validar UIT','FormatoServlet?operacion=grillaformato&vposicion=4',6);
insert into menu values(7,'Generar Expediente','FormatoServlet?operacion=grillaformato&vposicion=5',7);
insert into menu values(8,'Aprobar Solicitud de Cotizacion','ExpedienteServlet?operacion=grillaexpediente&vposicion=6',5);
insert into menu values(9,'Realizar Invitacion a Proveedores','ExpedienteServlet?operacion=grillaexpediente&vposicion=7',7);
insert into menu values(10,'Enviar Cuadro Comparativo','ExpedienteServlet?operacion=grillaexpediente&vposicion=8',7);
insert into menu values(11,'Elegir Propuesta ganadora','ExpedienteServlet?operacion=grillaexpediente&vposicion=9',5);
insert into menu values(12,'Generar Orden de Compra','ExpedienteServlet?operacion=grillaexpediente&vposicion=10',8);
insert into menu values(13,'Verifica Orden de Compra','ExpedienteServlet?operacion=grillaexpediente&vposicion=11',5);
insert into menu values(14,'Matener Perfil','mantenimientoperfil.jsp',9);
insert into menu values(15,'Matener Usuario','mantenimientousuario.jsp',9);
insert into menu values(16,'Matener Menu','mantenimientomenu.jsp',9);
insert into menu values(17,'Matener Proveedor','mantenimientoproveedor.jsp',9);
insert into menu values(18,'Matener Pac','mantenimientodetallepac.jsp',9);
insert into menu values(19,'Restablecer proceso','FormatoServlet?operacion=restableceroperacion',2);
insert into menu values(20,'Restablecer proceso','FormatoServlet?operacion=restableceroperacion',3);
insert into menu values(21,'Restablecer proceso','FormatoServlet?operacion=restableceroperacion',4);
insert into menu values(22,'Restablecer proceso','FormatoServlet?operacion=restableceroperacion',5);
insert into menu values(23,'Restablecer proceso','FormatoServlet?operacion=restableceroperacion',6);
insert into menu values(24,'Restablecer proceso','FormatoServlet?operacion=restableceroperacion',7);
insert into menu values(25,'Restablecer proceso','FormatoServlet?operacion=restableceroperacion',8);

insert into proveedor values('1', 'PEDRO SAC', '888888', '88888', 'mshelzr@gmail.com');
insert into proveedor values('2', 'JORGE SAC', '999999', '99999', 'elvis_1108@hotmail.com');
insert into proveedor values('3', 'PILSEN SAC', '777777', '77777', 'elviz.zr@facebook.com');
insert into proveedor values('4', 'COCACOLA SAC', '666666', '66666', 'i201012088@cibertec.edu.pe');

insert into detalle_pac values(1,'Contratación de un profesional para la elaboración e implementación de un plan de difución y promoción del Programa Credito Beca',20000,'Marzo');
insert into detalle_pac values(2,'Contratación del servicio de Impresión de material de difusión del Programa Credito beca',30000,'Febrero');
insert into detalle_pac values(3,'Contratación del servicio de desarrollo de merchandising para charlas informativas del Programa Credito Beca',25000,'Marzo');
insert into detalle_pac values(4,'Contratación de un profesional para la elaboración e implementación de un plan de difución y promoción del Programa Credito Beca',17000,'Marzo');
insert into detalle_pac values(5,'Contratación del servicio de Impresión de material de difusión del Programa Credito beca',25000,'Febrero');
insert into detalle_pac values(6,'Contratación del servicio de desarrollo de merchandising para charlas informativas del Programa Credito Beca',30000,'Marzo');
insert into detalle_pac values(7,'Contratación de un profesional para la elaboración e implementación de un plan de difución y promoción del Programa Credito Beca',27000,'Octubre');
insert into detalle_pac values(8,'Contratación del servicio de Impresión de material de difusión del Programa Credito beca',23000,'Febrero');
insert into detalle_pac values(9,'Contratación del servicio de desarrollo de merchandising para charlas informativas del Programa Credito Beca',2000,'Marzo');
insert into detalle_pac values(10,'Contratación de un profesional para la elaboración e implementación de un plan de difución y promoción del Programa Credito Beca',11170,'Diciembre');
insert into detalle_pac values(11,'Contratación del servicio de Impresión de material de difusión del Programa Credito beca',71000,'Febrero');
insert into detalle_pac values(12,'Contratación del servicio de desarrollo de merchandising para charlas informativas del Programa Credito Beca',21000,'Marzo');
insert into detalle_pac values(13,'Contratación de un profesional para la elaboración e implementación de un plan de difución y promoción del Programa Credito Beca',17088,'Abril');
insert into detalle_pac values(14,'Contratación del servicio de Impresión de material de difusión del Programa Credito beca',7880,'Febrero');
insert into detalle_pac values(15,'Contratación del servicio de desarrollo de merchandising para charlas informativas del Programa Credito Beca',8820,'Marzo');
insert into detalle_pac values(16,'Contratación de un profesional para la elaboración e implementación de un plan de difución y promoción del Programa Credito Beca',66170,'Enero');

insert into saldopresupuestal values (1,'Saldo Base',200000);
insert into saldopresupuestal values (2,'Valor UIT',3700);
insert into saldopresupuestal values (3,'UIT Inicial',1);
insert into saldopresupuestal values (4,'UIT Final',13.4);

insert into actividades values(-1,'Generar Formato Req');
insert into actividades values(1,'Firmar Autorizacion  de Formato Req');
insert into actividades values(2,'Consultar Saldo Presupuesto y dar de alta al Formato Req');
insert into actividades values(3,'Verificar Formato Req si esta dentro del PAC');
insert into actividades values(4,'Verificar Datos Correptos en el Formato de Requerimiento');
insert into actividades values(5,'Validar UIT');
insert into actividades values(6,'Generar Expediente');
insert into actividades values(7,'Aprobar Solicitud de Cotizacion');
insert into actividades values(8,'Realizar Invitacion a Proveedores');
insert into actividades values(9,'Enviar Cuadro Comparativo');
insert into actividades values(10,'Elegir Propuesta ganadora');
insert into actividades values(11,'Generar Orden de Compra');
insert into actividades values(12,'Verifica Orden de Compra');