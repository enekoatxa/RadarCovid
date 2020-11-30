package Objects;

import lombok.*;
import javax.jdo.annotations.*;
@PersistenceCapable
@AllArgsConstructor
  public class Positive {
    public Positive(User patient, double latitude, double longitude, int year, int month, int day) {
        this.patient=patient;
		this.latitude=latitude;
		this.longitude=longitude;
		this.year=year;
		this.month=month;
		this.day=day;
	}
	// This class uses the Lombok plugin
	@Getter @Setter
	@PrimaryKey
    @Persistent(valueStrategy=IdGeneratorStrategy.INCREMENT)
    private long idPositive;
    @Getter @Setter
    private double latitude;
    @Getter @Setter
    private double longitude;
    @Getter @Setter
    private int year;
    @Getter @Setter
    private int month;
    @Getter @Setter
    private int day;
    @Getter @Setter
    @Column(name="idCard")
    private User patient;
}
