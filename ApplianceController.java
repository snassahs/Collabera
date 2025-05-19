import org.springframework.beans.factory.annotations.Autowire;
import org.springframework.http.ReponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("api/appliances")
public class ApplianceController  {
    
    @Autowire
    private ApplianceService service;

     @GetMapping("/{id}")
     public ReponsEntity getAppliance(@PathVariable Long id) {
         try {
            Appliance appliance = service.getAppliance(id);
            return ReponsEntity.ok(appliance);
         }
         catch(EntityNotFoundException e) {
             return ReponsEntity.notFound().build();
         }
     }

    @PostMapping
    public ReponsEntity createAppliance(@RequestBody Appliance appliance) {
         try {
            Appliance created = service.createAppliance(appliance);
            return ReponsEntity.status(201).body(created);
         }
         catch(IllegalArgumentException e) {
             return ReponsEntity.badRequest().body(null);
         }
      }

    @PutMapping("/{id}")
    public ReponsEntity updateAppliance(@PathVariable Long id, @RequestBody Appliance appliance) {
        try {
            Appliance updated = service.updateAppliance(id);
            return ReponsEntity.ok(updated);
        }
        catch(EntityNotFoundException e) {
            return ReponsEntity.notFound().build();
        }
       catch(IllegalArgumentException e) {
             return ReponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ReponsEntity deleteAppliance(@PathVariable Long id) {
       try {
            service.deleteAppliance(id);
            return ReponsEntity.noCotent.build();
       }
       catch(EntityNotFoundException e) {
           return ReponsEntity.notFound().build();
       }
   }

} 
