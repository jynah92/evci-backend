package com.moonah.evci.application.service;

import com.moonah.evci.application.dto.ServiceAreaDto;
import com.moonah.evci.model.domain.ServiceArea;

import java.util.List;
import java.util.Optional;

public interface ServiceAreaService {
    // C
    void saveServiceArea(ServiceArea restArea);

    void saveServiceAreas(List<ServiceAreaDto> restAreaList);

    // R
    List<ServiceAreaDto> findAllServiceAreasFromApi();

    List<ServiceAreaDto> findAllServiceAreas();

    Optional<ServiceAreaDto> findServiceAreaByServiceAreaCode(String serviceAreaCode);

    Optional<ServiceAreaDto> findServiceAreaByStdRestCd(String stdRestCd);

    Optional<ServiceAreaDto> findServiceAreaByUnitCode(String unitCode);

    List<ServiceAreaDto> findServiceAreasByRouteName(String routeName);

    List<ServiceAreaDto> findServiceAreasByRouteNo(String routeNo);

    List<ServiceAreaDto> findServiceAreasByUnitName(String unitName);

    List<ServiceAreaDto> findServiceAreasByLocInfo(double lat, double lng, double tolerance);

    int countAllServiceAreasAtApi();

    int countAllServiceAreasInRepository();

    // U

    void replaceAllServiceAreasWithApi();

    boolean modifyServiceAreaByServiceAreaCode(String serviceAreaCode, ServiceArea serviceArea);

    boolean modifyServiceAreaByStdRestCd(String stdRestCd, ServiceArea serviceArea);

    boolean modifyServiceAreaByUnitCode(String unitCode, ServiceArea serviceArea);
    // D

    boolean deleteServiceAreaByServiceAreaCode(String serviceAreaCode);

    boolean deleteServiceAreaByStdRestCd(String stdRestCd);

    boolean deleteServiceAreaByUnitCode(String unitCode);
}
