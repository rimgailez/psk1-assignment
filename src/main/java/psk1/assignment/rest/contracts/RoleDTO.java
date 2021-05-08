package psk1.assignment.rest.contracts;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleDTO {

    private String name;

    private String description;

    private String actor;

    private Integer movieId;
}
