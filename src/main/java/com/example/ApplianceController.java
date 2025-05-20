package com.example;
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
     public ReponseEntity getAppliance(@PathVariable Long id) {
         try {
            Appliance appliance = service.getAppliance(id);
            return ReponseEntity.ok(appliance);
         }
         catch(EntityNotFoundException e) {
             return ReponseEntity.notFound().build();
         }
     }

    @PostMapping
    public ReponsEntity createAppliance(@RequestBody Appliance appliance) {
         try {
            Appliance created = service.createAppliance(appliance);
            return ReponseEntity.status(201).body(created);
         }
         catch(IllegalArgumentException e) {
             return ReponseEntity.badRequest().body(null);
         }
      }

    @PutMapping("/{id}")
    public ReponseEntity updateAppliance(@PathVariable Long id, @RequestBody Appliance appliance) {
        try {
            Appliance updated = service.updateAppliance(id);
            return ReponseEntity.ok(updated);
        }
        catch(EntityNotFoundException e) {
            return ReponseEntity.notFound().build();
        }
       catch(IllegalArgumentException e) {
             return ReponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ReponseEntity deleteAppliance(@PathVariable Long id) {
       try {
            service.deleteAppliance(id);
            return ReponseEntity.noCotent.build();
       }
       catch(EntityNotFoundException e) {
           return ReponseEntity.notFound().build();
       }
   }

} 
