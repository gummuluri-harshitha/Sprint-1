import java.time.LocalDate;
import java.sql.Connection;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

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
Scanner sc = new Scanner(System.in);
        CitizenDAO citizenDAO = new CitizenDAO();
        RequestDAO requestDAO = new RequestDAO();

        while (true) {
            System.out.println("----- Main Menu -----");
            System.out.println("1. Citizen Management");
            System.out.println("2. Request Management");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int mainChoice = sc.nextInt();
            sc.nextLine();  

            switch (mainChoice) {
                case 1:
                    handleCitizenMenu(sc, citizenDAO);
                    break;

                case 2:
                    handleRequestMenu(sc, requestDAO);
                    break;

                case 3:
                    System.out.println("Exiting program...");
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void handleCitizenMenu(Scanner sc, CitizenDAO citizenDAO) {
    while (true) {
        System.out.println("\n----- Citizen Management -----");
        System.out.println("1. Add Citizen");
        System.out.println("2. View All Citizens");
        System.out.println("3. Update Citizen by Email");
        System.out.println("4. Delete Citizen by Email");
        System.out.println("5. Back to Main Menu");
        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {
            case 1:
                System.out.print("Enter name: ");
                String name = sc.nextLine();
                System.out.print("Enter phone: ");
                String phone = sc.nextLine();
                System.out.print("Enter email: ");
                String email = sc.nextLine();
                System.out.print("Enter address: ");
                String address = sc.nextLine();

                Citizen citizen = new Citizen(0, name, phone, email, address);
                citizenDAO.addCitizen(citizen);
                break;

            case 2:
                citizenDAO.getAllCitizens().forEach(System.out::println);
                break;

            case 3:
                System.out.print("Enter email of citizen to update: ");
                String updateEmail = sc.nextLine();
                List<Citizen> allCitizens = citizenDAO.getAllCitizens();
                Citizen oldCitizen = null;
                for (Citizen c : allCitizens) {
                    if (c.getEmail().equalsIgnoreCase(updateEmail)) {
                        oldCitizen = c;
                        break;
                    }
                }

                if (oldCitizen == null) {
                    System.out.println(" No citizen found with email: " + updateEmail);
                    break;
                }

                System.out.print("Enter new name (leave blank to keep '" + oldCitizen.getName() + "'): ");
                String newName = sc.nextLine();
                if (newName.isEmpty()) newName = oldCitizen.getName();

                System.out.print("Enter new phone number (leave blank to keep '" + oldCitizen.getPhoneNumber() + "'): ");
                String newPhone = sc.nextLine();
                if (newPhone.isEmpty()) newPhone = oldCitizen.getPhoneNumber();

                System.out.print("Enter new address (leave blank to keep '" + oldCitizen.getAddress() + "'): ");
                String newAddress = sc.nextLine();
                if (newAddress.isEmpty()) newAddress = oldCitizen.getAddress();

                citizenDAO.updateCitizenByEmail(updateEmail, newName, newPhone, newAddress);
                break;

            case 4:
                System.out.print("Enter email of citizen to delete: ");
                String deleteEmail = sc.nextLine();
                citizenDAO.deleteCitizenByEmail(deleteEmail);
                break;

            case 5:
                return;

            default:
                System.out.println("Invalid choice.");
        }
    }
}

    private static void handleRequestMenu(Scanner sc, RequestDAO requestDAO) {
    while (true) {
        System.out.println("\n----- Request Management -----");
        System.out.println("1. Add Request");
        System.out.println("2. View All Requests");
        System.out.println("3. Update Request by ID");
        System.out.println("4. Delete Request");
        System.out.println("5. View Pending Requests by Priority");
         System.out.println("6. Back to Main Menu");

        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {
            case 1:
                System.out.print("Enter citizen ID: ");
                int citizenId = sc.nextInt();
                System.out.print("Enter technician ID: ");
                int techId = sc.nextInt();
                System.out.print("Enter service ID: ");
                int serviceId = sc.nextInt();
                sc.nextLine(); 
                System.out.print("Enter status (PENDING/IN_PROGRESS/COMPLETED): ");
                Status status = Status.valueOf(sc.nextLine().toUpperCase());
                System.out.print("Enter priority (LOW/MEDIUM/HIGH): ");
                Priority priority = Priority.valueOf(sc.nextLine().toUpperCase());
                LocalDate today = LocalDate.now();

                Request newRequest = new Request(0, citizenId, techId, serviceId, status, priority, today);
                requestDAO.addRequest(newRequest);
                break;

            case 2:
                requestDAO.getAllRequests().forEach(System.out::println);
                break;

            case 3:
                System.out.print("Enter request ID to update: ");
                int reqId = Integer.parseInt(sc.nextLine());

                List<Request> allRequests = requestDAO.getAllRequests();
                Request oldRequest = null;
                for (Request r : allRequests) {
                    if (r.getRequestId() == reqId) {
                        oldRequest = r;
                        break;
                    }
                }

                if (oldRequest == null) {
                    System.out.println(" No request found with ID: " + reqId);
                    break;
                }

                System.out.print("Enter new technician ID (leave blank to keep '" + oldRequest.getTechnicianId() + "'): ");
                String newTechStr = sc.nextLine();
                int newTechId = newTechStr.isEmpty() ? oldRequest.getTechnicianId() : Integer.parseInt(newTechStr);

                System.out.print("Enter new service ID (leave blank to keep '" + oldRequest.getServiceId() + "'): ");
                String newServiceStr = sc.nextLine();
                int newServiceId = newServiceStr.isEmpty() ? oldRequest.getServiceId() : Integer.parseInt(newServiceStr);

                System.out.print("Enter new status (PENDING/IN_PROGRESS/COMPLETED) (leave blank to keep '" + oldRequest.getStatus() + "'): ");
                String statusStr = sc.nextLine().toUpperCase();
                Status newStatus = statusStr.isEmpty() ? oldRequest.getStatus() : Status.valueOf(statusStr);

                System.out.print("Enter new priority (LOW/MEDIUM/HIGH) (leave blank to keep '" + oldRequest.getPriority() + "'): ");
                String priorityStr = sc.nextLine().toUpperCase();
                Priority newPriority = priorityStr.isEmpty() ? oldRequest.getPriority() : Priority.valueOf(priorityStr);

                LocalDate updatedDate = LocalDate.now(); 

                Request updatedRequest = new Request(reqId, oldRequest.getCitizenId(), newTechId, newServiceId, newStatus, newPriority, updatedDate);
                requestDAO.updateRequest(updatedRequest);
                break;

            case 4:
                System.out.print("Enter request ID to delete: ");
                int deleteId = sc.nextInt();
                sc.nextLine();
                requestDAO.deleteRequestById(deleteId);
                break;


            case 5:
    
    List<Request> allPendingRequests = requestDAO.getAllRequests();

    // PriorityQueue with custom comparator: HIGH > MEDIUM > LOW
    PriorityQueue<Request> pendingQueue = new PriorityQueue<>((r1, r2) -> 
        r2.getPriority().compareTo(r1.getPriority()) // descending order
    );

    for (Request req : allPendingRequests) {
        if (req.getStatus() == Status.PENDING) {
            pendingQueue.add(req);
        }
    }

    System.out.println("Pending Requests (sorted by priority):");
    while (!pendingQueue.isEmpty()) {
        System.out.println(pendingQueue.poll());
    }
    break;


            case 6:
                return;
            
            

            default:
                System.out.println("Invalid choice.");
        }
    }
    }
}
