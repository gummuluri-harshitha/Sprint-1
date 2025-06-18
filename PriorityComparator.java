import java.util.Comparator;

public class PriorityComparator implements Comparator<Request> {
    @Override
    public int compare(Request r1, Request r2) {
        return r1.getPriority().compareTo(r2.getPriority());
    }
}
