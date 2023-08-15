package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HuespedesDao {

	private Connection con;

	public HuespedesDao(Connection con) {

		this.con = con;

	}

	public void registrarHuesped(Huespedes huespedes) {

		java.sql.Date fechaNacimiento = new java.sql.Date(huespedes.getFecha_nacimiento().getTime());

		try {
			String consulta = "INSERT INTO huespedes (nombre, apellido, fecha_nacimiento, nacionalidad, telefono, idReserva)"
					+ "VALUES(?,?,?,?,?,?)";

			try (PreparedStatement pstm = con.prepareStatement(consulta, PreparedStatement.RETURN_GENERATED_KEYS)) {
				pstm.setString(1, huespedes.getNombre());
				pstm.setString(2, huespedes.getApellido());
				pstm.setDate(3, fechaNacimiento);
				pstm.setString(4, huespedes.getNacionalidad());
				pstm.setString(5, huespedes.getTelefono());
				pstm.setInt(6, huespedes.getIdReserva());

				pstm.executeUpdate();

				try (ResultSet rst = pstm.getGeneratedKeys()) {
					while (rst.next()) {
						huespedes.setId(rst.getInt(1));

					}
				}
			}

		} catch (SQLException e) {
			System.out.println("Error en la consulta" + e.getMessage());
		}

	}

	public List<Huespedes> buscarApellido(String apellido) {

		List<Huespedes> huespedesList = new ArrayList<>();

		Huespedes huespedes = new Huespedes();

		try {
			String consulta = "SELECT * FROM huespedes WHERE apellido = ? ";

			try (PreparedStatement pstm = con.prepareStatement(consulta)) {
				pstm.setString(1, apellido);

				try (ResultSet rst = pstm.executeQuery()) {

					while (rst.next()) {
						huespedes.setId(rst.getInt("id"));
						huespedes.setNombre(rst.getString("nombre"));
						huespedes.setApellido(rst.getString("apellido"));
						huespedes.setFecha_nacimiento(rst.getDate("fecha_nacimiento"));
						huespedes.setNacionalidad(rst.getString("nacionalidad"));
						huespedes.setTelefono(rst.getString("telefono"));
						huespedes.setIdReserva(rst.getInt("idReserva"));

						huespedesList.add(huespedes);

					}

				}
			}

		} catch (SQLException e) {
			System.out.println("Error en la consulta " + e.getMessage());
		}

		return huespedesList;
	}

	public List<Huespedes> traerHuespedes() {
		List<Huespedes> huespedesList = new ArrayList<>();

		try {
			String consulta = "SELECT * FROM huespedes ";

			try (PreparedStatement pstm = con.prepareStatement(consulta)) {

				try (ResultSet rst = pstm.executeQuery()) {

					while (rst.next()) {
						Huespedes huespedes = new Huespedes();
						huespedes.setId(rst.getInt("id"));
						huespedes.setNombre(rst.getString("nombre"));
						huespedes.setApellido(rst.getString("apellido"));
						huespedes.setFecha_nacimiento(rst.getDate("fecha_nacimiento"));
						huespedes.setNacionalidad(rst.getString("nacionalidad"));
						huespedes.setTelefono(rst.getString("telefono"));
						huespedes.setIdReserva(rst.getInt("idReserva"));

						huespedesList.add(huespedes);

					}

				}
			}

		} catch (SQLException e) {
			System.out.println("Error en la consulta " + e.getMessage());
		}

		return huespedesList;
	}

	public void Actualizar(Huespedes huespedes) {

		java.sql.Date fechaNacimiento = new java.sql.Date(huespedes.getFecha_nacimiento().getTime());

		try {
			String consulta = "UPDATE huespedes SET nombre = ?, apellido=?, fecha_nacimiento=?, nacionalidad=?, telefono=?, idReserva=? WHERE id=? ";

			try (PreparedStatement pstm = con.prepareStatement(consulta, PreparedStatement.RETURN_GENERATED_KEYS)) {
				pstm.setString(1, huespedes.getNombre());
				pstm.setString(2, huespedes.getApellido());
				pstm.setDate(3, fechaNacimiento);
				pstm.setString(4, huespedes.getNacionalidad());
				pstm.setString(5, huespedes.getTelefono());
				pstm.setInt(6, huespedes.getIdReserva());
				pstm.setInt(7, huespedes.getId());
				pstm.executeUpdate();

			}

		} catch (SQLException e) {
			System.out.println("Error en la consulta" + e.getMessage());
		}

	}

	public void eliminar(int valor) {
		try (PreparedStatement stm = con.prepareStatement("DELETE FROM huespedes WHERE id = ?")) {
			stm.setInt(1, valor);
			stm.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

}
