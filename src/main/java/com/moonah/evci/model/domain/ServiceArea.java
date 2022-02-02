package com.moonah.evci.model.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceArea {
    private String serviceAreaCode; // 영업부대시설코드
    private String stdRestCd; // 휴게소 / 주유소 코드
    private String unitCode; // 휴게소 코드
    private String unitName; // 휴게소명
    private String routeName; // 노선명
    private String routeNum; // 노선 번호
    private double lat; // 위도 (= yValue)
    private double lng; // 경도 (= xValue)
}
