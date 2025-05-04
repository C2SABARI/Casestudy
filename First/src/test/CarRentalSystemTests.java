package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dao.ICustomerService;
import dao.IVehicleService;
import dao.CustomerService;
import dao.VehicleService;
import entity.model.Customer;
import entity.model.Vehicle;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class CarRentalSystemTests {

    private ICustomerService customerService;
    private IVehicleService vehicleService;
    private Scanner scanner;

    @BeforeEach
    public void setup() {
        scanner = new Scanner(System.in);
        try {
            customerService = new CustomerService();
        } catch (ClassNotFoundException | SQLException | IOException e) {
            e.printStackTrace();
        }
        try {
            vehicleService = new VehicleService();
        } catch (ClassNotFoundException | SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    // 1. Test customer authentication with invalid credentials
    @Test
    public void testInvalidCustomerAuthentication() {
        System.out.print("Enter username for authentication: ");
        String username = scanner.nextLine();
        System.out.print("Enter password for authentication: ");
        String password = scanner.nextLine();

        try {
            Customer customer = customerService.getCustomerByUsername(username);
            if (customer != null && customer.getPassword().equals(password)) {
                fail("Authentication should fail with invalid credentials");
            } else {
                System.out.println("Invalid credentials");
            }
        } catch (Exception e) {
            assertTrue(e instanceof SQLException);
            System.out.println("Invalid credentials");
        }
    }

    // 2. Test updating customer information
    @Test
    public void testUpdateCustomerInfo() {
        System.out.print("Enter customer ID to update: ");
        int customerId = Integer.parseInt(scanner.nextLine());

        try {
            Customer customer = customerService.getCustomerById(customerId);
            System.out.print("Enter new email: ");
            String newEmail = scanner.nextLine();

            customer.setEmail(newEmail);
            customerService.updateCustomer(customer);

            Customer updated = customerService.getCustomerById(customerId);
            assertEquals(newEmail, updated.getEmail());
        } catch (Exception e) {
            fail("Update failed with exception: " + e.getMessage());
        }
    }

    // 3. Test adding a new vehicle
    @Test
    public void testAddNewVehicle() {
        System.out.print("Enter vehicle model: ");
        String model = scanner.nextLine();
        System.out.print("Enter vehicle make: ");
        String make = scanner.nextLine();
        System.out.print("Enter vehicle year: ");
        String year = scanner.nextLine();
        System.out.print("Enter vehicle color: ");
        String color = scanner.nextLine();
        System.out.print("Enter vehicle registration number: ");
        String registrationNumber = scanner.nextLine();
        System.out.print("Is the vehicle available? (true/false): ");
        boolean availability = Boolean.parseBoolean(scanner.nextLine());
        System.out.print("Enter daily rate: ");
        double dailyRate = Double.parseDouble(scanner.nextLine());

        try {
            Vehicle vehicle = new Vehicle();
            vehicle.setModel(model);
            vehicle.setMake(make);
            vehicle.setYear(year);
            vehicle.setColor(color);
            vehicle.setRegistrationNumber(registrationNumber);
            vehicle.setAvailability(availability);
            vehicle.setDailyRate(dailyRate);

            vehicleService.addVehicle(vehicle);
            assertNotNull(vehicle);
        } catch (Exception e) {
            fail("Add vehicle failed: " + e.getMessage());
        }
    }

    // 4. Test updating vehicle details
    @Test
    public void testUpdateVehicleDetails() {
        System.out.print("Enter vehicle ID to update: ");
        int vehicleId = Integer.parseInt(scanner.nextLine());

        try {
            Vehicle vehicle = vehicleService.getVehicleById(vehicleId);

            System.out.print("Enter new color for the vehicle: ");
            String newColor = scanner.nextLine();

            vehicle.setColor(newColor);
            vehicleService.updateVehicle(vehicle);

            Vehicle updated = vehicleService.getVehicleById(vehicleId);
            assertEquals(newColor, updated.getColor());
        } catch (Exception e) {
            fail("Update vehicle failed: " + e.getMessage());
        }
    }

    // 5. Test getting a list of available vehicles
    @Test
    public void testGetAvailableVehicles() {
        try {
            List<Vehicle> available = vehicleService.getAvailableVehicles();
            assertNotNull(available);
            assertTrue(available.size() > 0);
            System.out.println("Available vehicles: ");
            available.forEach(v -> System.out.println(v.getModel() + " - " + v.getColor()));
        } catch (Exception e) {
            fail("Fetching available vehicles failed: " + e.getMessage());
        }
    }

    // 6. Test getting a list of all vehicles
    @Test
    public void testGetAllVehicles() {
        try {
            List<Vehicle> allVehicles = vehicleService.getAvailableVehicles();
            assertNotNull(allVehicles);
            assertTrue(allVehicles.size() > 0);
            System.out.println("All vehicles: ");
            allVehicles.forEach(v -> System.out.println(v.getModel() + " - " + v.getColor()));
        } catch (Exception e) {
            fail("Fetching all vehicles failed: " + e.getMessage());
        }
    }
}
