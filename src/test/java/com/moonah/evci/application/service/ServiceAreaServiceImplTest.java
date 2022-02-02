package com.moonah.evci.application.service;

import com.moonah.evci.model.domain.ServiceArea;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ServiceAreaServiceImplTest {
    @Autowired
    private ServiceAreaService serviceAreaService;

    @Test
    @DisplayName("API로부터 모든 휴게소가 잘 가져와지는지 개수 확인")
    void findAllServiceAreaFromApi() {
        List<ServiceArea> serviceAreaList = serviceAreaService.findAllServiceAreasFromApi();
        int countServiceAreaApi = serviceAreaService.countAllServiceAreasAtApi();

        assertThat(serviceAreaList.size()).isEqualTo(countServiceAreaApi);
    }
}