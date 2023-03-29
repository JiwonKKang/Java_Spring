package jiwon.selfmade.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
public class ItemEditDTO {

    @NotNull
    private Long id;

    @NotEmpty
    private String itemName;

    @NotNull
    @Range(min = 10000, max = 1000000)
    private Integer price;

    @NotNull
    private Integer quantity;
}
