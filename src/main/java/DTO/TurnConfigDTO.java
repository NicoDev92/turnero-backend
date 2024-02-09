package DTO;

import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class TurnConfigDTO {
    private String day;
    private List<String> turnHours;
}
