package Objects;

import javax.jdo.annotations.*;

import lombok.*;
@PersistenceCapable
@AllArgsConstructor
public class User {
//    public User(int idCard, String password, String email, int age, String gender, String occupation, boolean admin) {
//		this.idCard=idCard;
//		this.password=password;
//		this.email=email;
//		this.age=age;
//		this.gender=gender;
//		this.occupation=occupation;
//		this.admin=admin;
//	}
	@Getter @Setter @NonNull
    @PrimaryKey
    private int idCard;
	
    public int getIdCard() {
		return idCard;
	}
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
