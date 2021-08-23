package com.example.demo.models.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Load {

  private String name;
  private Integer voltage = 0;
  private Integer powerDC = 0;
  private Integer powerAc = 0;
  private Integer quantity = 0;
  private Integer usageDay = 0;
  private Integer usageNight = 0;
  private int dayEnergy = 0;
  private int nightEnergy = 0;
  private int totalEnergy = 0;
}
