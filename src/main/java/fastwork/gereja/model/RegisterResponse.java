package fastwork.gereja.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterResponse {

    private String id;

    private String username;

    private String name;

    private String numberKK;

    private String sector;

    private String phoneNumber;
}
