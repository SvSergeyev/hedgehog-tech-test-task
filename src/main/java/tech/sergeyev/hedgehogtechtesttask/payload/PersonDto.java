package tech.sergeyev.hedgehogtechtesttask.payload;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Setter
@Getter
@ToString
public class PersonDto {
    @NotBlank(message = "Name cannot be empty")
    @Size(max = 25)
    String name;

    @NotBlank(message = "Surname cannot be empty")
    @Size(max = 25)
    String surname;
}

