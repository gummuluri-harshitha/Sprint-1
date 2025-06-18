import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RequestDAO {

    public void addRequest(Request request) {
        String query = "INSERT INTO request (citizen_id, technician_id, service_id, status, priority, request_date) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setInt(1, request.getCitizenId());
            pst.setInt(2, request.getTechnicianId());
            pst.setInt(3, request.getServiceId());
            pst.setString(4, request.getStatus().name());
            pst.setString(5, request.getPriority().name());
            pst.setDate(6, Date.valueOf(request.getSubmissionDate()));

            int rowsInserted = pst.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("✅ Request added successfully.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Request> getAllRequests() {
        List<Request> requests = new ArrayList<>();
        String query = "SELECT * FROM request";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement pst = con.prepareStatement(query);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("request_id");
                int citizenId = rs.getInt("citizen_id");
                int technicianId = rs.getInt("technician_id");
                int serviceId = rs.getInt("service_id");
                String statusStr = rs.getString("status");
                String priorityStr = rs.getString("priority");
                LocalDate requestDate = rs.getDate("request_date").toLocalDate();

                Request req = new Request(id, citizenId, technicianId, serviceId,
                        Status.valueOf(statusStr), Priority.valueOf(priorityStr), requestDate);

                requests.add(req);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return requests;
    }



    public boolean updateRequest(Request request) {
    String query = "UPDATE request SET technician_id = ?, service_id = ?, status = ?, priority = ?, request_date = ? WHERE request_id = ?";

    try (Connection con = DBUtil.getConnection();
         PreparedStatement pst = con.prepareStatement(query)) {

        pst.setInt(1, request.getTechnicianId());
        pst.setInt(2, request.getServiceId());
        pst.setString(3, request.getStatus().name());
        pst.setString(4, request.getPriority().name());
        pst.setDate(5, Date.valueOf(request.getSubmissionDate()));
        pst.setInt(6, request.getRequestId());

        int rowsUpdated = pst.executeUpdate();

        if (rowsUpdated > 0) {
            System.out.println("Request updated successfully.");
            return true;
        } else {
            System.out.println("No request found with ID: " + request.getRequestId());
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return false;
}


public boolean deleteRequestById(int requestId) {
    String query = "DELETE FROM request WHERE request_id = ?";

    try (Connection con = DBUtil.getConnection();
         PreparedStatement pst = con.prepareStatement(query)) {

        pst.setInt(1, requestId);
        int rowsDeleted = pst.executeUpdate();

        if (rowsDeleted > 0) {
            System.out.println("Request with ID " + requestId + " deleted successfully.");
            return true;
        } else {
            System.out.println("No request found with ID: " + requestId);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return false;
}


}
