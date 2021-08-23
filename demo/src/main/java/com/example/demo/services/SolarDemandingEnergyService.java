package com.example.demo.services;

import com.example.demo.models.dto.Load;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SolarDemandingEnergyService {

  public static final Predicate<Load> LOAD_DC_POWER =
      e -> e.getPowerDC() > 0 && e.getPowerAc() == 0;
  public static final Predicate<Load> LOAD_AC_POWER =
      e -> e.getPowerAc() > 0 && e.getPowerDC() == 0;
  public static final Function<Load, Load> MAPPER_DC =
      r -> {
        r.setDayEnergy(r.getUsageDay() * r.getQuantity() * r.getPowerDC());
        r.setNightEnergy(r.getUsageNight() * r.getQuantity() * r.getPowerDC());
        return r;
      };
  public static final Function<Load, Load> MAPPER_AC =
      r -> {
        r.setDayEnergy(r.getUsageDay() * r.getQuantity() * r.getPowerAc());
        r.setNightEnergy(r.getUsageNight() * r.getQuantity() * r.getPowerAc());
        return r;
      };
  public static final BiFunction<List<Load>, List<Load>, Integer> MAPPER_TOTAL_DEMAND_ENERGY =
      (listDC1, listAC1) ->
          listAC1.stream()
                  .reduce(0, (s, e) -> s + e.getDayEnergy() + e.getNightEnergy(), Integer::sum)
              + listDC1.stream()
                  .reduce(0, (s, e) -> s + e.getDayEnergy() + e.getNightEnergy(), Integer::sum);

  private List<Load> loadList = new ArrayList<>();

  public int calculateTotalDemandWh() {

    List<Load> listDC =
        this.loadList.stream().filter(LOAD_DC_POWER).map(MAPPER_DC).collect(Collectors.toList());

    List<Load> listAC =
        this.loadList.stream().filter(LOAD_AC_POWER).map(MAPPER_AC).collect(Collectors.toList());

    return MAPPER_TOTAL_DEMAND_ENERGY.apply(listDC, listAC);
  }
}
