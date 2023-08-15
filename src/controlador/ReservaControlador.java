package controlador;

import java.sql.Connection;
import java.util.List;

import modelo.ConexionBD;
import modelo.Reserva;
import modelo.ReservaDao;

public class ReservaControlador {

	private ReservaDao reservaDao;

	public ReservaControlador() {
		Connection con = new ConexionBD().getConnection();
		reservaDao = new ReservaDao(con);

	}

	public void guardarReserva(Reserva reserva) {

		this.reservaDao.guardarReserva(reserva);
	}

	public List<Reserva> buscarPorNReserva(int idReserva) {
		return this.reservaDao.buscarPorNReserva(idReserva);
	}

	public List<Reserva> traerReservas() {

		return this.reservaDao.traerReservas();
	}

	public void modificarReserva(Reserva reserva) {
		this.reservaDao.modificarReserva(reserva);

	}

	public void Eliminar(int valor) {

		this.reservaDao.eliminar(valor);
	}

}
