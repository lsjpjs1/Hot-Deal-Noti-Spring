package com.example.hotdealnoti.common.controller;

import com.example.hotdealnoti.hotdeal.dto.HotDealDto;
import com.example.hotdealnoti.hotdeal.service.GetHotDealService;
import com.example.hotdealnoti.repository.jpa.JpaProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class SiteMapController {

//    @GetMapping(value = "/sitemap.txt")
//    public ResponseEntity<Page<HotDealDto.HotDealPreview>> getHotDeals(@ModelAttribute HotDealDto.GetHotDealsRequest getHotDealsRequest, Pageable pageable, HttpServletRequest httpServletRequest) {
//
//        String ip = getIpFromRequest(httpServletRequest);
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .body(getHotDealService.getHotDeals(getHotDealsRequest,pageable,ip));
//
//    }

    private final JpaProductRepository jpaProductRepository;
    @GetMapping(value = "/sitemap.txt")
    public void downloadSitemap(HttpServletResponse response) throws IOException{
        String nowAsISO = ZonedDateTime.now( ZoneOffset.UTC ).format( DateTimeFormatter.ISO_INSTANT );
        String filename = "sitemap.txt";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<urlset\n" +
                "  xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\"\n" +
                "  xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "  xsi:schemaLocation=\"http://www.sitemaps.org/schemas/sitemap/0.9 http://www.sitemaps.org/schemas/sitemap/0.9/sitemap.xsd\"\n" +
                ">\n");
        stringBuilder.append("  <url>\n" +
                "    <loc>https://whendiscount.com</loc>\n" +
                "    <lastmod>"+nowAsISO+"</lastmod>\n" +
                "  </url>\n");

        jpaProductRepository.findAll().forEach(product -> {
            stringBuilder.append("  <url>\n" +
                    "    <loc>https://whendiscount.com/hot-deals/product/"+product.getProductId().toString()+"</loc>\n" +
                    "    <lastmod>"+nowAsISO+"</lastmod>\n" +
                    "  </url>\n");
        });
        stringBuilder.append("</urlset>");
        String content = stringBuilder.toString();

        byte[] fileByte = content.getBytes();

        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("UTF-8");
        response.setContentLength(fileByte.length);
        response.setHeader("Content-Disposition", "attachment; FileName=\"" + filename +"\";");
        response.setHeader("Access-Control-Expose-Headers", "X-Filename");
        response.setHeader("X-Filename", filename);
        response.setHeader("Content-Transfer-Encoding",  "binary");
        response.getOutputStream().write(fileByte);

        response.getOutputStream().flush();
        response.getOutputStream().close();

    }

}
