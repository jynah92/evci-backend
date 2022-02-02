package com.moonah.evci.model.repository;

import com.moonah.evci.model.domain.ServiceArea;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ServiceAreaRepositoryImpl implements ServiceAreaRepository {
    @Override
    public void saveServiceArea(ServiceArea restArea) {

    }

    @Override
    public void saveServiceAreas(List<ServiceArea> restAreaList) {

    }

    @Override
    public List<ServiceArea> findAllServiceAreas() {
        return null;
    }

    @Override
    public Optional<ServiceArea> findServiceAreaByServiceAreaCode(String serviceAreaCode) {
        return Optional.empty();
    }

    @Override
    public Optional<ServiceArea> findServiceAreaByStdRestCd(String stdRestCd) {
        return Optional.empty();
    }

    @Override
    public Optional<ServiceArea> findServiceAreaByUnitCode(String unitCode) {
        return Optional.empty();
    }

    @Override
    public List<ServiceArea> findServiceAreasByRouteName(String routeName) {
        return null;
    }

    @Override
    public List<ServiceArea> findServiceAreasByRouteNo(String routeNo) {
        return null;
    }

    @Override
    public List<ServiceArea> findServiceAreasByUnitName(String unitName) {
        return null;
    }

    @Override
    public List<ServiceArea> findServiceAreasByLocInfo(double lat, double lng, double tolerance) {
        return null;
    }

    @Override
    public int countAllServiceAreas() {
        return 0;
    }

    @Override
    public void replaceAllServiceAreas(List<ServiceArea> restAreaList) {

    }

    @Override
    public boolean modifyServiceAreaByServiceAreaCode(String serviceAreaCode, ServiceArea serviceArea) {
        return false;
    }

    @Override
    public boolean modifyServiceAreaByStdRestCd(String stdRestCd, ServiceArea serviceArea) {
        return false;
    }

    @Override
    public boolean modifyServiceAreaByUnitCode(String unitCode, ServiceArea serviceArea) {
        return false;
    }

    @Override
    public boolean deleteServiceAreaByServiceAreaCode(String serviceAreaCode) {
        return false;
    }

    @Override
    public boolean deleteServiceAreaByStdRestCd(String stdRestCd) {
        return false;
    }

    @Override
    public boolean deleteServiceAreaByUnitCode(String unitCode) {
        return false;
    }
}
