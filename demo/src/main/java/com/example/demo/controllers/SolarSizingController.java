package com.example.demo.controllers;

import com.example.demo.models.dto.Load;
import com.example.demo.services.SolarDemandingEnergyService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class SolarSizingController {

  private SolarDemandingEnergyService solarDemandingEnergyService;

  @PostMapping("/solar/sizing")
  public int getCalculationMapping(@RequestBody List<Load> loadList) {
    solarDemandingEnergyService.setLoadList(loadList);
    return solarDemandingEnergyService.calculateTotalDemandWh();
  }
}
