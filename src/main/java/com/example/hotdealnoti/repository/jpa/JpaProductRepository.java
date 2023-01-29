package com.example.hotdealnoti.repository.jpa;

import com.example.hotdealnoti.auth.domain.Account;
import com.example.hotdealnoti.enums.AccountType;
import com.example.hotdealnoti.product.domain.Product;
import com.example.hotdealnoti.product.dto.ProductDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface JpaProductRepository extends JpaRepository<Product,Long> {

    @Query(nativeQuery = true,value = "select " +
            "    max(product.product_id) productId, " +
            "       max(product.product_purpose_id) productPurposeId, " +
            "       row_number() over (order by (case when count(hd.hot_deal_id) = 1 then round((float8(sum(hd.hot_deal_view_count)-max(hd.hot_deal_view_count))/float8(count(hd.hot_deal_id)))::::numeric,2)*sum(hd.hot_deal_view_count)-max(hd.hot_deal_view_count) " +
            "                                         else round((float8(sum(hd.hot_deal_view_count)-max(hd.hot_deal_view_count))/log(float8(count(hd.hot_deal_id))))::::numeric,2)*sum(hd.hot_deal_view_count)-max(hd.hot_deal_view_count) " +
            "           end) desc) productRankingNumber " +
            "from product " +
            "         left join product_purpose pp on pp.product_purpose_id = product.product_purpose_id " +
            "         left join hot_deal hd on product.product_id = hd.product_id " +
            "where pp.product_purpose_id = (:productPurposeId) " +
            "group by product.product_id " +
            "order by (case when count(hd.hot_deal_id) = 1 then round((float8(sum(hd.hot_deal_view_count)-max(hd.hot_deal_view_count))/float8(count(hd.hot_deal_id)))::::numeric,2)*sum(hd.hot_deal_view_count)-max(hd.hot_deal_view_count) " +
            "    else round((float8(sum(hd.hot_deal_view_count)-max(hd.hot_deal_view_count))/log(float8(count(hd.hot_deal_id))))::::numeric,2)*sum(hd.hot_deal_view_count)-max(hd.hot_deal_view_count) " +
            "    end) desc " +
            "limit 100")
    List<ProductDto.ProductRankingInfo> findTop100RankingProduct(@Param("productPurposeId") Long productPurposeId);

}
