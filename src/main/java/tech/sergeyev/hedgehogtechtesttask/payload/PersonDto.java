package tech.sergeyev.hedgehogtechtesttask.payload;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

//@FieldDefaults(level = AccessLevel.PRIVATE)
public class PersonDto {
    @NotBlank(message = "Name cannot be empty")
    private
    String name;

    @NotBlank(message = "Surname cannot be empty")
    private
    String surname;

    public @NotBlank(message = "Name cannot be empty") @Size(max = 25) String getName() {
        return this.name;
    }

    public @NotBlank(message = "Surname cannot be empty") @Size(max = 25) String getSurname() {
        return this.surname;
    }

    public void setName(@NotBlank(message = "Name cannot be empty") String name) {
        this.name = name;
    }

    public void setSurname(@NotBlank(message = "Surname cannot be empty") String surname) {
        this.surname = surname;
    }

    @Override
    public String toString() {
        return "PersonDto{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
