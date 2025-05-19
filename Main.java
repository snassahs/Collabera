public class Main {
    
    public static void main(String args[]) {
        ApplianceService service = new ApplianceService();
        Appliance appliance = new Appliance();
        appliance.setApplianceType("Fan");
        appliance.setFanSpeed(3);
    }
}