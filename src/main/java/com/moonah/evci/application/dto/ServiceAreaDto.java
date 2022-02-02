package com.moonah.evci.application.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceAreaDto {
    private String serviceAreaCode; // 영업부대시설코드
    private String stdRestCd; // 휴게소 / 주유소 코드
    private String unitCode; // 휴게소 코드
    private String unitName; // 휴게소명
    private String routeName; // 노선명
    private String routeNo; // 노선 번호
    private double lat; // 위도 (= yValue)
    private double lng; // 경도 (= xValue)
}
