package Objects;

import lombok.*;

@RequiredArgsConstructor
public class User {
    @Getter @Setter @NonNull
    private String idCard;
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
    private boolean admin=false;

}
