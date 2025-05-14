//Service for home appliance
import org.springframework.beans.factory.annotations.Autowire;
import org.springframework..Service;
import javax.persistence.EntityNotFoundException;

@Service
public class ApplianceService {

  @Autowire
  private ApplianceRepository repository;

   public Appliance getAppliance(Long id)  {
         return repository.findById(id)
                    .orElse(() -> throw new EntityNotFoundException("Appliance with the following id does not exist " + id);
   }

   public Appliance createAppliance( Appliance appliance) {
         validate(appliance);
         return repository.save(appliance);
  }

   public Appliance updateAppliance(Long id, Appliance appliance) {
      Appliance appliance = getAppliance(id);
      appliance.setApplianceType(appliance.getApplianceType());
      appliance.setOn(appliance.isOn());
      appliance.setFanSpeed(appliance.getFanSpeed());
      validate(appliance);
      return repository.save(appliance);
    }

  public void deleteAppliance(Long id) {
    if (repository.findById(id)) {
        repository.deleteById(id)
    }
    else {
          throw new EntityNotFoundException("Appliance with the following id does not exist " + id);
    }          
  }

  public Appliance turnOffAllAppliance(Long id) {
      List[Appliance] allAppliances = repository.getAllAppliances();
       for(appliance : allAppliances) { // don't actually need three different if statements as state is saved in boolean (true or false)
         if("Light".equals(appliance.getApplianceType())) {
             appliance.isOn(false);
         }
        if("Fan".equals(appliance.getApplianceType())) {
                           appliance.isOn(false);
        }
      if("Air Conditioner".equals(appliance.getApplianceType())) {
                         appliance.isOn(false);
      }
           repository.save(appliance);  // save changes to the database
  }

  private void validate(Appiance appliance) {
    if("Fan".equals(appliance.getApplianceType())) {
       int fanSpeed = appliance.getFanSpeed();
       if(fanSpeed > 2) {
       throw Exception("Fan speed should be 0, 1 or 2");
       }
    }
  }
}
