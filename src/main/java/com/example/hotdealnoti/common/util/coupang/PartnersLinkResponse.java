package com.example.hotdealnoti.common.util.coupang;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
@ToString
public class PartnersLinkResponse {
    private String rCode;
    private String rMessage;
    private List<OriginalAndShortenUrl> data;



}

@Getter
@AllArgsConstructor
@Builder
@Setter
@ToString
class OriginalAndShortenUrl{
    private String originalUrl;
    private String shortenUrl;
}
