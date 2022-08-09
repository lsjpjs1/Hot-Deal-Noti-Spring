package com.example.hotdealnoti.hotdeal.repository;

import com.example.hotdealnoti.hotdeal.dto.HotDealDto;
import com.example.hotdealnoti.messagequeue.domain.QHotDeal;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class HotDealQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;
    private final QHotDeal hotDeal = QHotDeal.hotDeal;

    public Page<HotDealDto.HotDealPreview> findHotDeals(HotDealDto.GetHotDealsRequest getHotDealsRequest, Pageable pageable) {
        List<HotDealDto.HotDealPreview> hotDealPreviews = jpaQueryFactory
                .select(
                        Projections.constructor(HotDealDto.HotDealPreview.class,
                                hotDeal.hotDealId,
                                hotDeal.hotDealTitle,
                                hotDeal.hotDealOriginalPrice,
                                hotDeal.hotDealDiscountPrice,
                                hotDeal.hotDealDiscountRate,
                                hotDeal.hotDealLink,
                                hotDeal.hotDealUploadTime
                        )
                )
                .from(hotDeal)
                .where(
                        getCondition(getHotDealsRequest),
                        hotDeal.isDelete.eq(Boolean.FALSE)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(getAllOrderSpecifiers(pageable).stream().toArray(OrderSpecifier[]::new))
                .fetch();

        Long count = jpaQueryFactory
                .select(hotDeal.count())
                .from(hotDeal)
                .where(getCondition(getHotDealsRequest),
                        hotDeal.isDelete.eq(Boolean.FALSE))
                .fetchOne();

        return new PageImpl(hotDealPreviews,pageable,count);

    }

    private BooleanExpression getCondition(HotDealDto.GetHotDealsRequest getHotDealsRequest){
        if (getHotDealsRequest == null){
            return null;
        }
        if (getHotDealsRequest.getSearchBody()==null){
            return null;
        }
        if (getHotDealsRequest.getSearchBody()==""){
            return null;
        }
        return hotDeal.hotDealTitle.toLowerCase().contains(getHotDealsRequest.getSearchBody().toLowerCase());
    }

    private List<OrderSpecifier> getAllOrderSpecifiers(Pageable pageable){
        if(pageable.getSort().isEmpty()){
            return null;
        }
        List<OrderSpecifier> orderSpecifiers = new ArrayList<>();
        for(Sort.Order order: pageable.getSort()){
            Order direction = order.getDirection().isAscending() ? Order.ASC : Order.DESC;

            switch (order.getProperty()) {
                case "DISCOUNT_RATE":
                    orderSpecifiers.add(new OrderSpecifier(direction, hotDeal.hotDealDiscountRate));
                    break;
                case "VIEW_COUNT":
                    orderSpecifiers.add(new OrderSpecifier(direction, hotDeal.hotDealViewCount));
                    break;
                case "PRICE":
                    orderSpecifiers.add(new OrderSpecifier(direction, hotDeal.hotDealDiscountPrice));
                    break;
                case "UPLOAD_TIME":
                    orderSpecifiers.add(new OrderSpecifier(direction, hotDeal.hotDealUploadTime));
                    break;
                default:
                    break;
            }
        }
        return orderSpecifiers;
    }

}
