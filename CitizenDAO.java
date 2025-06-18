import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CitizenDAO {

    public void addCitizen(Citizen citizen) {
        String query = "INSERT INTO citizen (citizen_name, email, phone_number, address) VALUES (?, ?, ?, ?)";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setString(1, citizen.getName());
            pst.setString(2, citizen.getEmail());
            pst.setString(3, citizen.getPhoneNumber());
            pst.setString(4, citizen.getAddress());
                        int rowsInserted = pst.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Citizen inserted successfully.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



     public List<Citizen> getAllCitizens() {
        List<Citizen> citizens = new ArrayList<>();
        String query = "SELECT * FROM citizen";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement pst = con.prepareStatement(query);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("citizen_id");
                String name = rs.getString("citizen_name");
                String email = rs.getString("email");
                String phone = rs.getString("phone_number");
                String address = rs.getString("address");

                Citizen citizen = new Citizen(id, name, phone, email, address);
                citizens.add(citizen);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return citizens;
    }

        public boolean deleteCitizenByEmail(String email) {
            String query = "DELETE FROM citizen WHERE email = ?";

            try (Connection con = DBUtil.getConnection();
                PreparedStatement pst = con.prepareStatement(query)) {

                pst.setString(1, email);
                int rowsDeleted = pst.executeUpdate();

                if (rowsDeleted > 0) {
                    System.out.println("Citizen with email '" + email + "' deleted successfully.");
                    return true;
                } else {
                    System.out.println("No citizen found with email '" + email + "'.");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return false;
        }

    public boolean updateCitizenByEmail(String email, String newName, String newPhoneNumber, String newAddress) {
    String query = "UPDATE citizen SET citizen_name = ?, phone_number = ?, address = ? WHERE email = ?";

    try (Connection con = DBUtil.getConnection();
         PreparedStatement pst = con.prepareStatement(query)) {

        pst.setString(1, newName);
        pst.setString(2, newPhoneNumber);
        pst.setString(3, newAddress);
        pst.setString(4, email);

        int rowsUpdated = pst.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("Citizen with email '" + email + "' updated successfully.");
            return true;
        } else {
            System.out.println("No citizen found with email '" + email + "'.");
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return false;
}



}
