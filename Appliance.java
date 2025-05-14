// Model for home appliance
import javax.persistence.*
import Lombok.Data;

@Entity
@Table
@Data
public class Appliance  {
    
        @Id
        @GeneratedId(GenerationType = "IDENTITY")
        private Long id;

        @Column(Nullable = False)
        private String ApplianceType;  // Light, Fan or Air Conditioner

        @Column(Nullable = False)
        private boolean isOn;

        @Column
        private int fanSpeed;  // 0, 1 or 2

}
