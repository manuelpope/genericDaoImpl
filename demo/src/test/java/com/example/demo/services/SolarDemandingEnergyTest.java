package com.example.demo.services;

import com.example.demo.models.dto.Load;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnit4.class)
class SolarDemandingEnergyTest {


    private Load load1;
    private Load load2;
    private SolarDemandingEnergyService demandingEnergy;

    @Test
    void calculateTotalDemandWh() {


        demandingEnergy.setLoadList(Arrays.asList(load1, load2));
        Gson gson = new Gson();
        // demandingEnergy.getLoadList().forEach(s -> System.out.println(gson.toJson(s)));
        assertThat(demandingEnergy.calculateTotalDemandWh()).isEqualTo(220);
    }

    @BeforeEach
    public void init() {
        load1 = Load.builder()
                .powerAc(0)
                .quantity(20)
                .powerDC(1)
                .voltage(12)
                .usageDay(0)
                .usageNight(8)
                .dayEnergy(0)
                .nightEnergy(0)
                .totalEnergy(0)
                .build();

        load2 = Load.builder().
                quantity(2)
                .powerAc(15)
                .voltage(110)
                .usageDay(0)
                .usageNight(2)
                .totalEnergy(0)
                .dayEnergy(0)
                .nightEnergy(0)
                .powerDC(0)
                .build();


        demandingEnergy = new SolarDemandingEnergyService();
    }
}