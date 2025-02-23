package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Country {
    private String name;
    private String language;
    private String date;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return Objects.equals(getName(), country.getName()) && Objects.equals(getLanguage(), country.getLanguage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getLanguage());
    }
}
