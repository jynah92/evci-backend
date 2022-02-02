package com.moonah.evci.application.service;

import com.moonah.evci.model.domain.ServiceArea;

import java.util.List;
import java.util.Optional;

public interface ServiceAreaService {
    // C
    void saveServiceArea(ServiceArea restArea);

    void saveServiceAreas(List<ServiceArea> restAreaList);

    // R
    List<ServiceArea> findAllServiceAreasFromApi();

    List<ServiceArea> findAllServiceAreas();

    Optional<ServiceArea> findServiceAreaByServiceAreaCode(String serviceAreaCode);

    Optional<ServiceArea> findServiceAreaByStdRestCd(String stdRestCd);

    Optional<ServiceArea> findServiceAreaByUnitCode(String unitCode);

    List<ServiceArea> findServiceAreasByRouteName(String routeName);

    List<ServiceArea> findServiceAreasByRouteNo(String routeNo);

    List<ServiceArea> findServiceAreasByUnitName(String unitName);

    List<ServiceArea> findServiceAreasByLocInfo(double lat, double lng, double tolerance);

    int countAllServiceAreasAtApi();

    int countAllServiceAreasInRepository();

    // U

    void replaceAllServiceAreas(List<ServiceArea> restAreaList);

    boolean modifyServiceAreaByServiceAreaCode(String serviceAreaCode, ServiceArea serviceArea);

    boolean modifyServiceAreaByStdRestCd(String stdRestCd, ServiceArea serviceArea);

    boolean modifyServiceAreaByUnitCode(String unitCode, ServiceArea serviceArea);
    // D

    boolean deleteServiceAreaByServiceAreaCode(String serviceAreaCode);

    boolean deleteServiceAreaByStdRestCd(String stdRestCd);

    boolean deleteServiceAreaByUnitCode(String unitCode);
}
