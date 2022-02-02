package com.moonah.evci.application.service;

import com.moonah.evci.application.dto.ServiceAreaDto;
import com.moonah.evci.model.domain.ServiceArea;
import com.moonah.evci.model.repository.ServiceAreaRepository;
import com.moonah.evci.util.api.ApiXmlParser;
import com.moonah.evci.util.api.Pair;
import com.moonah.evci.util.api.XmlItem;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.moonah.evci.util.api.Constants.KEC_SERVICE_KEY;

@Service
public class ServiceAreaServiceImpl implements ServiceAreaService {
    private final ServiceAreaRepository serviceAreaRepository;
    private final ApiXmlParser apiXmlParser;
    private final ModelMapper modelMapper;

    @Autowired
    public ServiceAreaServiceImpl(ServiceAreaRepository serviceAreaRepository, ApiXmlParser apiXmlParser, ModelMapper modelMapper) {
        this.serviceAreaRepository = serviceAreaRepository;
        this.apiXmlParser = apiXmlParser;
        this.modelMapper = modelMapper;
    }

    @Override
    public void saveServiceArea(ServiceArea restArea) {

    }

    @Override
    public void saveServiceAreas(List<ServiceAreaDto> restAreaList) {

    }

    @Override
    public List<ServiceAreaDto> findAllServiceAreasFromApi() {
        int numOfRows = 50;
        int totalCount = countAllServiceAreasAtApi();
        int quotient = totalCount / numOfRows;
        int remainder = totalCount % numOfRows;

        List<ServiceAreaDto> serviceAreaList = new ArrayList<>();

        for (int i = 1; i <= quotient; i++) {
            List<XmlItem> parsed = apiXmlParser.parse(
                    "https://data.ex.co.kr/openapi/locationinfo/locationinfoRest",
                    "//list",
                    new Pair("key", KEC_SERVICE_KEY),
                    new Pair("type", "xml"),
                    new Pair("pageNo", i),
                    new Pair("numOfRows", numOfRows));

            addAllServiceAreasFromApiInList(serviceAreaList, parsed);
        }

        if (remainder != 0) {
            List<XmlItem> parsed = apiXmlParser.parse(
                    "https://data.ex.co.kr/openapi/locationinfo/locationinfoRest",
                    "//list",
                    new Pair("key", KEC_SERVICE_KEY),
                    new Pair("type", "xml"),
                    new Pair("numOfRows", remainder));

            addAllServiceAreasFromApiInList(serviceAreaList, parsed);
        }

        return serviceAreaList;
    }

    private void addAllServiceAreasFromApiInList(List<ServiceAreaDto> serviceAreaList, List<XmlItem> parsed) {
        for (XmlItem xmlItem : parsed) {
            ServiceAreaDto serviceAreaDto = ServiceAreaDto.builder()
                    .serviceAreaCode(xmlItem.get("serviceAreaCode"))
                    .stdRestCd(xmlItem.get("stdRestCd"))
                    .unitCode(xmlItem.get("unitCode"))
                    .unitName(xmlItem.get("unitName"))
                    .routeName(xmlItem.get("routeName"))
                    .routeNo(xmlItem.get("routeNo"))
                    .lat(Double.parseDouble(xmlItem.get("yValue")))
                    .lng(Double.parseDouble(xmlItem.get("xValue")))
                    .build();

            serviceAreaList.add(serviceAreaDto);
        }
    }

    @Override
    public List<ServiceAreaDto> findAllServiceAreas() {
        return null;
    }

    @Override
    public Optional<ServiceAreaDto> findServiceAreaByServiceAreaCode(String serviceAreaCode) {
        return Optional.empty();
    }

    @Override
    public Optional<ServiceAreaDto> findServiceAreaByStdRestCd(String stdRestCd) {
        return Optional.empty();
    }

    @Override
    public Optional<ServiceAreaDto> findServiceAreaByUnitCode(String unitCode) {
        return Optional.empty();
    }

    @Override
    public List<ServiceAreaDto> findServiceAreasByRouteName(String routeName) {
        return null;
    }

    @Override
    public List<ServiceAreaDto> findServiceAreasByRouteNo(String routeNo) {
        return null;
    }

    @Override
    public List<ServiceAreaDto> findServiceAreasByUnitName(String unitName) {
        return null;
    }

    @Override
    public List<ServiceAreaDto> findServiceAreasByLocInfo(double lat, double lng, double tolerance) {
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
    public void replaceAllServiceAreasWithApi() {
        List<ServiceAreaDto> forReplace = findAllServiceAreasFromApi();

        serviceAreaRepository.replaceAllServiceAreas(forReplace.stream()
                .map(serviceAreaDto -> modelMapper.map(serviceAreaDto, ServiceArea.class))
                .collect(Collectors.toList()));
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
