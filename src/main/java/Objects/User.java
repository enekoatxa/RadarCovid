package Objects;

import javax.jdo.annotations.*;

import lombok.*;
@PersistenceCapable
@AllArgsConstructor
public class User {

	@Getter @Setter @NonNull
    @PrimaryKey
    private int idCard;
    @Getter @Setter @NonNull
    private String username;
	@Getter @Setter @NonNull
    private String password;
    @Getter @Setter @NonNull
    private String email;
    @Getter @Setter @NonNull
    private int age;
    @Getter @Setter @NonNull
    private String gender;
    @Getter @Setter @NonNull
    private String occupation;
    @Getter @Setter @NonNull
    private boolean admin=false;

}
