public class ServiceType {
    private int serviceId;
    private String serviceName;

    // Constructor
    public ServiceType(int serviceId, String serviceName) {
        this.serviceId = serviceId;
        this.serviceName = serviceName;
    }

    // Getters
    public int getServiceId() {
        return serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    // toString
    @Override
    public String toString() {
        return "ServiceType{" +
                "serviceId=" + serviceId +
                ", serviceName='" + serviceName + '\'' +
                '}';
    }
}
