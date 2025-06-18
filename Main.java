import java.time.LocalDate;
import java.sql.Connection;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // 1. Check connection
        try {
            Connection con = DBUtil.getConnection();
            if (con != null && !con.isClosed()) {
                System.out.println("JDBC Connection successful!");
            } else {
                System.out.println("Connection failed.");
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Insert Citizen
        CitizenDAO citizenDAO = new CitizenDAO();
        Citizen c1 = new Citizen(0, "Ishaan Verma", "9123456789", "ishaan@example.com", "Jaipur");
        citizenDAO.addCitizen(c1);

        // Update Citizen
        citizenDAO.updateCitizenByEmail("ishaan@example.com", "Ishaan V.", "9000000000", "Udaipur");

        // Delete Citizen
        citizenDAO.deleteCitizenByEmail("ishaan@example.com");

        // View all Citizens
        List<Citizen> allCitizens = citizenDAO.getAllCitizens();
        System.out.println("All Citizens in the system:");
        for (Citizen c : allCitizens) {
            System.out.println(c);
        }

        // Create a sample Request
        Request sampleRequest = new Request(
                1,
                101,
                201,
                1,
                Status.PENDING,
                Priority.HIGH,
                LocalDate.now()
        );
        System.out.println("Testing Request object:");
        System.out.println(sampleRequest);
        System.out.println("Status: " + sampleRequest.getStatus());
        System.out.println("Priority: " + sampleRequest.getPriority());

        ServiceType service = new ServiceType(1, "Solar Installation");
        System.out.println(service);

        // Create DAO instance
        RequestDAO requestDAO = new RequestDAO();

        // Insert Request
        Request newRequest = new Request(
                0,
                8,
                1,
                2,
                Status.PENDING,
                Priority.HIGH,
                LocalDate.now()
        );
        requestDAO.addRequest(newRequest);

        // View all Requests
        List<Request> allRequests = requestDAO.getAllRequests();
        for (Request req : allRequests) {
            System.out.println(req);
        }

        // Update Request
        Request updatedReq = new Request(
                7,
                8,
                1,
                1,
                Status.IN_PROGRESS,
                Priority.HIGH,
                LocalDate.now()
        );
        requestDAO.updateRequest(updatedReq);

        // Delete Request
        boolean deleted = requestDAO.deleteRequestById(7);
        System.out.println("Delete status: " + deleted);

        // View all Requests again
        List<Request> updatedRequests = requestDAO.getAllRequests();
        System.out.println("Requests in system:");
        for (Request r : updatedRequests) {
            System.out.println(r);
        }

        // Final update and delete
        Request finalUpdated = new Request(1, 3, 1, 1, Status.COMPLETED, Priority.LOW, LocalDate.now());
        requestDAO.updateRequest(finalUpdated);
        requestDAO.deleteRequestById(2);

        // Final list
        System.out.println("Requests after update and delete:");
        List<Request> finalRequests = requestDAO.getAllRequests();
        for (Request r : finalRequests) {
            System.out.println(r);
        }
    }
}