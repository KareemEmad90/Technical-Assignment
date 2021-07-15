package enums;

public enum SearchVehicleType {
    ChasisNumber("Chassis Number"),
    PlateNumber("Plate Number"),
    Certificate("Certificate"),
    Appointment("Appointment"),
    TransactionNumber("Transaction No.");

    public final String SearchVhcleType;

    public String SearchVhcleType(){return SearchVhcleType;}

    @Override
    public String toString() {
        return super.toString();
    }

    SearchVehicleType(String SearchVehicleType){
        this.SearchVhcleType = SearchVehicleType;
    }
}
