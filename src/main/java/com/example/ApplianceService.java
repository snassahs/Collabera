package com.example;

import org.springframework.beans.factory.annotations.Autowire;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;

@Service
public class ApplianceService {

  @Autowire
  private ApplianceRepository repository;

   public Appliance getAppliance(Long id)  {
         return repository.findById(id)
                    .orElse(() -> new EntityNotFoundException("Appliance with the following id does not exist " + id));
   }

   public Appliance createAppliance( Appliance appliance) {
         validate(appliance);
         return repository.save(appliance);
  }

   public Appliance updateAppliance(Long id, Appliance appliance) {
      Appliance updatedAppliance = getAppliance(id);
      updatedAppliance.setApplianceType(appliance.getApplianceType());
      updatedAppliance.setOn(appliance.isOn());
      updatedAppliance.setFanSpeed(appliance.getFanSpeed());
      validate(updatedAppliance);
      return repository.save(updatedAppliance);
    }

  public void deleteAppliance(Long id) {
    if (repository.findById(id)) {
        repository.deleteById(id);
    }
    else {
          throw new EntityNotFoundException("Appliance with the following id does not exist " + id);
    }          
  }

  public Appliance turnOffAllAppliance(Long id) {
      List[] allAppliances = repository.getReferenceById(id);
       for(Appliance appliance : allAppliances) {
          appliance.isOn(false);
          repository.save(appliance); 
       }
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
