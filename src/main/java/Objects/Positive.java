package Objects;

import lombok.*;

@AllArgsConstructor
public class Positive {
    @Getter @Setter
    private User patient;
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

}
