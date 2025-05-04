package dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import entity.model.Reservation;
import exception.InvalidInputException;

public interface IReservationService {

	public Reservation getReservationById(int reservationId) throws FileNotFoundException, ClassNotFoundException, IOException, InvalidInputException;
	public List<Reservation> getReservationsByCustomerId(int cutomerId) throws SQLException, IOException, ClassNotFoundException;
	public void createReservation(Reservation reservation) throws SQLException, InvalidInputException, IOException, ClassNotFoundException;
	public void updateReservation(Reservation reservation) throws SQLException, InvalidInputException, IOException, ClassNotFoundException;
	public void cancelReservation(int reservationId);
}