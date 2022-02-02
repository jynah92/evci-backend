package com.moonah.evci.application.service;

import com.moonah.evci.model.domain.ServiceArea;
import com.moonah.evci.model.repository.ServiceAreaRepository;
import com.moonah.evci.util.api.ApiXmlParser;
import com.moonah.evci.util.api.Pair;
import com.moonah.evci.util.api.XmlItem;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.moonah.evci.util.api.Constants.KEC_SERVICE_KEY;

@Service
@RequiredArgsConstructor
public class ServiceAreaServiceImpl implements ServiceAreaService {
    private final ServiceAreaRepository serviceAreaRepository;
    private final ApiXmlParser apiXmlParser;
    private final ModelMapper modelMapper;

    @Override
    public void saveServiceArea(ServiceArea restArea) {

    }

    @Override
    public void saveServiceAreas(List<ServiceArea> restAreaList) {

    }

    @Override
    public List<ServiceArea> findAllServiceAreasFromApi() {
        int numOfRows = 10;
        int totalCount = countAllServiceAreasAtApi();
        int quotient = totalCount / numOfRows;
        int remainder = totalCount % numOfRows;

        List<ServiceArea> serviceAreaList = new ArrayList<>();

        for (int i = 1; i <= quotient; i++) {
            List<XmlItem> parsed = apiXmlParser.parse(
                    "https://data.ex.co.kr/openapi/locationinfo/locationinfoRest",
                    "//data/count",
                    new Pair("key", KEC_SERVICE_KEY),
                    new Pair("type", "xml"),
                    new Pair("pageNo", i),
                    new Pair("numOfRows", numOfRows));

            addAllServiceAreasFromApiInList(serviceAreaList, parsed);
        }

        if (remainder != 0) {
            List<XmlItem> parsed = apiXmlParser.parse(
                    "https://data.ex.co.kr/openapi/locationinfo/locationinfoRest",
                    "//data/count",
                    new Pair("key", KEC_SERVICE_KEY),
                    new Pair("type", "xml"),
                    new Pair("numOfRows", remainder));

            addAllServiceAreasFromApiInList(serviceAreaList, parsed);
        }

        return serviceAreaList;
    }

    private void addAllServiceAreasFromApiInList(List<ServiceArea> serviceAreaList, List<XmlItem> parsed) {
        for (XmlItem xmlItem : parsed) {
            serviceAreaList.add(ServiceArea.builder()
                    .serviceAreaCode(xmlItem.get("serviceAreaCode"))
                    .stdRestCd(xmlItem.get("stdRestCd"))
                    .unitCode(xmlItem.get("unitCode"))
                    .unitName(xmlItem.get("unitName"))
                    .routeName(xmlItem.get("routeName"))
                    .routeNum(xmlItem.get("routeNum"))
                    .lat(Double.parseDouble(xmlItem.get("yValue")))
                    .lng(Double.parseDouble(xmlItem.get("xValue")))
                    .build());
        }
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
    public int countAllServiceAreasAtApi() {
        return apiXmlParser.getCount(
                "https://data.ex.co.kr/openapi/locationinfo/locationinfoRest",
                "//data/count",
                new Pair("key", KEC_SERVICE_KEY),
                new Pair("type", "xml"));
    }

    @Override
    public int countAllServiceAreasInRepository() {
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
