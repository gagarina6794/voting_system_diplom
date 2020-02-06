package com.voting.system.project.to;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MenuWithDishesTo extends BaseTo {

    @NotNull
    private LocalDate registered;

    @NotNull
    private List<DishTo> dishes;

    public MenuWithDishesTo(Integer id, @NotNull LocalDate registered, @NotNull List<DishTo> dishes) {
        super(id);
        this.registered = registered;
        this.dishes = dishes;
    }
}
