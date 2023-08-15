package controlador;

import java.sql.Connection;
import java.util.List;

import modelo.ConexionBD;
import modelo.Huespedes;
import modelo.HuespedesDao;

public class HuespedControlador {

	private HuespedesDao huespedesDao;

	public HuespedControlador() {

		Connection con = new ConexionBD().getConnection();
		huespedesDao = new HuespedesDao(con);
	}

	public void registrarHuesped(Huespedes huespedes) {

		this.huespedesDao.registrarHuesped(huespedes);

	}

	public List<Huespedes> buscarApellido(String apellido) {

		return this.huespedesDao.buscarApellido(apellido);
	}

	public List<Huespedes> traerHuespedes() {

		return this.huespedesDao.traerHuespedes();
	}

	public void actualizar(Huespedes huespedes) {
		this.huespedesDao.Actualizar(huespedes);

	}

	public void Eliminar(int valor) {
		this.huespedesDao.eliminar(valor);
	}

}
