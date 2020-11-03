package SpringApplication.AppointmentScheduling.AppModel;

public class PoDetails {
    private int poNumber;
    private String poDate;
    private String poAddress;
    private long poLineNumber;
    private int upcNumber;
    private String upcName;
    private int orderQuantity;

    public PoDetails(int poNumber, String poDate, String poAddress, long poLineNumber, int upcNumber, String upcName, int orderQuantity) {
        this.poNumber = poNumber;
        this.poDate = poDate;
        this.poAddress = poAddress;
        this.poLineNumber = poLineNumber;
        this.upcNumber = upcNumber;
        this.upcName = upcName;
        this.orderQuantity = orderQuantity;
    }

    public String getPoAddress() {
        return poAddress;
    }

    public void setPoAddress(String poAddress) {
        this.poAddress = poAddress;
    }

    public int getPoNumber() {
        return poNumber;
    }

    public void setPoNumber(int poNumber) {
        this.poNumber = poNumber;
    }

    public String getPoDate() {
        return poDate;
    }

    public void setPoDate(String poDate) {
        this.poDate = poDate;
    }

    public long getPoLineNumber() {
        return poLineNumber;
    }

    public void setPoLineNumber(long poLineNumber) {
        this.poLineNumber = poLineNumber;
    }

    public int getUpcNumber() {
        return upcNumber;
    }

    public void setUpcNumber(int upcNumber) {
        this.upcNumber = upcNumber;
    }

    public String getUpcName() {
        return upcName;
    }

    public void setUpcName(String upcName) {
        this.upcName = upcName;
    }

    public int getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(int orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public PoDetails() {

    }

    @Override
    public String toString() {
        return "PoDetails{" +
                "poNumber=" + poNumber +
                ", poDate='" + poDate + '\'' +
                ", poAddress='" + poAddress + '\'' +
                ", poLineNumber=" + poLineNumber +
                ", upcNumber=" + upcNumber +
                ", upcName='" + upcName + '\'' +
                ", orderQuantity=" + orderQuantity +
                '}';
    }
}
