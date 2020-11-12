package Objects;

import javax.jdo.annotations.*;

import lombok.*;
@PersistenceCapable
@AllArgsConstructor
public class User {

    //This class uses Lombok
	@Getter @Setter @NonNull
    @PrimaryKey
    public int idCard;
    @Getter @Setter @NonNull
    public String username;
	@Getter @Setter @NonNull
    public String password;
    @Getter @Setter @NonNull
    public String email;
    @Getter @Setter @NonNull
    public int age;
    @Getter @Setter @NonNull
    public String gender;
    @Getter @Setter @NonNull
    public String occupation;
    @Getter @Setter @NonNull
    public boolean admin=false;

}
