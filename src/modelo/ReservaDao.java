package modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.QueryReturnType;

import views.ReservasView;

public class ReservaDao {

	private Connection con;

	public ReservaDao(Connection con) {

		this.con = con;

	}

	public void guardarReserva(Reserva reserva) {

		java.sql.Date entradaFecha = new java.sql.Date(reserva.getFechaEntrada().getTime());
		java.sql.Date salidaFecha = new java.sql.Date(reserva.getFechaSalida().getTime());

		try {
			String consulta = "INSERT INTO reservas (fecha_entrada, fecha_salida, valor, forma_pago) "
					+ "VALUES(?,?,?,?) ";
			try (PreparedStatement pstm = con.prepareStatement(consulta, PreparedStatement.RETURN_GENERATED_KEYS)) {
				pstm.setDate(1, entradaFecha);
				pstm.setDate(2, salidaFecha);
				pstm.setString(3, reserva.getValor());
				pstm.setString(4, reserva.getFormaPago());
				pstm.executeUpdate();

				try (ResultSet rst = pstm.getGeneratedKeys()) {

					while (rst.next()) {
						reserva.setId(rst.getInt(1));
					}

				}
			}

		} catch (SQLException e) {
			System.out.println("Error en la consulta " + e.getMessage());
		}

	}

	public List<Reserva> buscarPorNReserva(int idReserva) {
		List<Reserva> listaReservas = new ArrayList<>();
		Reserva reserva = new Reserva();
		try {
			String consultaString = "SELECT * FROM reservas WHERE id = ?";
			try (PreparedStatement pstm = con.prepareStatement(consultaString)) {
				pstm.setInt(1, idReserva);

				try (ResultSet rst = pstm.executeQuery()) {

					while (rst.next()) {

						reserva.setId(rst.getInt("id"));
						reserva.setFechaEntrada(rst.getDate("fecha_entrada"));
						reserva.setFechaSalida(rst.getDate("fecha_salida"));
						reserva.setValor(rst.getString("valor"));
						reserva.setFormaPago(rst.getString("forma_pago"));

						listaReservas.add(reserva);
						return listaReservas;

					}

				}

			}

		} catch (SQLException e) {
			System.out.println("Error en la consulta" + e.getMessage());
		}
		System.out.println("pasa el catch en la clase DAo");
		return listaReservas;

	}

	public List<Reserva> traerReservas() {
		List<Reserva> listaReservas = new ArrayList<>();
		try {
			String consultaString = "SELECT * FROM reservas ";
			try (PreparedStatement pstm = con.prepareStatement(consultaString)) {

				try (ResultSet rst = pstm.executeQuery()) {

					while (rst.next()) {
						Reserva reserva = new Reserva();
						reserva.setId(rst.getInt("id"));
						reserva.setFechaEntrada(rst.getDate("fecha_entrada"));
						reserva.setFechaSalida(rst.getDate("fecha_salida"));
						reserva.setValor(rst.getString("valor"));
						reserva.setFormaPago(rst.getString("forma_pago"));

						listaReservas.add(reserva);
					}

				}

			}

		} catch (SQLException e) {
			System.out.println("Error en la consulta" + e.getMessage());
		}

		return listaReservas;

	}

	public void modificarReserva(Reserva reserva) {

		java.sql.Date entradaFecha = new java.sql.Date(reserva.getFechaEntrada().getTime());
		java.sql.Date salidaFecha = new java.sql.Date(reserva.getFechaSalida().getTime());

		try {
			String consulta = "UPDATE reservas SET fecha_entrada = ?, fecha_salida= ?, valor=?, forma_pago=? "
					+ "WHERE id = ? ";

			try (PreparedStatement pstm = con.prepareStatement(consulta)) {
				pstm.setDate(1, entradaFecha);
				pstm.setDate(2, salidaFecha);
				pstm.setString(3, reserva.getValor());
				pstm.setString(4, reserva.getFormaPago());
				pstm.setInt(5, reserva.getId());
				pstm.execute();
			}

		} catch (SQLException e) {

			System.out.println("Error en la consulta " + e.getMessage());
		}

	}

	public void eliminar(int valor) {
		try (PreparedStatement stm = con.prepareStatement("DELETE FROM reservas WHERE id = ?")) {
			stm.setInt(1, valor);
			stm.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
