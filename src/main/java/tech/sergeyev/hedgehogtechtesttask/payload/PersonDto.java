package tech.sergeyev.hedgehogtechtesttask.payload;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PersonDto {
    @NotBlank(message = "Name cannot be empty")
    String name;

    @NotBlank(message = "Surname cannot be empty")
    String surname;
}
