package enums;

public enum TestType {
    RenewalTest("Renewal Test"),
    RegistrationTest("Registration Test"),
    TransferTest("Transfer Test"),
    ExportTest("Export Test"),
    VehicleDataTest("Vehicle Data Test"),
    DefectFullService("Defect Full Service"),
    DefectPartialService("Defect Partial Service"),
    RoadsideFineClearance("Roadside Fine Clearance"),
    VehicleInspectionAndLicensingService("Vehicle Inspection and Licensing Service"),
    LicensingServices("Licensing services"),
    VehicleInspectionServices("Vehicle Inspection Services"),
    OldSystemRetest("Old System Retest"),
    SpecialTrailerRegistrationService("Special Trailer Registration Service"),
    ForeignTestService("Foreign Test Service"),
    SchoolBusesService("School Buses Service"),
    SpecialVisualInspection("Special Visual Inspection"),
    ExclusiveTest("Exclusive Test");

    public final String Test_Type;

    public String Test_Type(){return Test_Type;}

    @Override
    public String toString() {
        return Test_Type;
    }

    TestType(String testType) {
        this.Test_Type = testType;
    }
}
