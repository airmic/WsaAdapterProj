package ru.mk.mkservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClientInfo {
    private String lastName;
    private String firstName;
    private String middleName;
    private String birthDate;
    private String photo;

    @JsonIgnore
    private Long mdmID;
}
