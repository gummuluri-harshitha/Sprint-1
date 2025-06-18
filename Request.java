import java.time.LocalDate;

public class Request {
    private int requestId;
    private int citizenId;
    private int technicianId;
    private int serviceId;
    private Status status;
    private Priority priority;
    private LocalDate submissionDate;

public Request(int requestId, int citizenId, int technicianId, int serviceId,
               Status status, Priority priority, LocalDate submissionDate) {
    this.requestId = requestId;
    this.citizenId = citizenId;
    this.technicianId = technicianId;
    this.serviceId = serviceId;
    this.status = status;
    this.priority = priority;
    this.submissionDate = submissionDate;}

    // Getters
    public int getRequestId() {
        return requestId;
    }

    public int getCitizenId() {
        return citizenId;
    }

    public int getTechnicianId() {
        return technicianId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public Status getStatus() {
        return status;
    }

    public Priority getPriority() {
    return priority;
    }

    public LocalDate getSubmissionDate() {
        return submissionDate;
    }

    // Optional: toString() for printing
    @Override
    public String toString() {
        return "Request{" +
                "requestId=" + requestId +
                ", citizenId=" + citizenId +
                ", technicianId=" + technicianId +
                ", serviceId=" + serviceId +
                ", status='" + status + '\'' +
                ", priority=" + priority.name() +
                ", submissionDate=" + submissionDate +
                '}';
    }
}
