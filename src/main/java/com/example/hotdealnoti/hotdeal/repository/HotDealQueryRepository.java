package com.example.hotdealnoti.hotdeal.repository;

import com.example.hotdealnoti.hotdeal.dto.HotDealDto;
import com.example.hotdealnoti.messagequeue.domain.QHotDeal;
import com.example.hotdealnoti.messagequeue.domain.QHotDealCandidate;
import com.example.hotdealnoti.product.domain.QProduct;
import com.querydsl.core.types.ConstructorExpression;
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

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class HotDealQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;
    private final QHotDeal hotDeal = QHotDeal.hotDeal;
    private final QHotDealCandidate hotDealCandidate = QHotDealCandidate.hotDealCandidate;
    private final QProduct product = QProduct.product;
    public Page<HotDealDto.HotDealPreview> findHotDeals(HotDealDto.GetHotDealsRequest getHotDealsRequest, Pageable pageable) {
        List<HotDealDto.HotDealPreview> hotDealPreviews = jpaQueryFactory
                .select(
                        getHotDealPreviewConstructorExpression()
                )
                .from(hotDeal)
                .where(
                        getCondition(getHotDealsRequest)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(getAllOrderSpecifiers(pageable).stream().toArray(OrderSpecifier[]::new))
                .fetch();

        Long count = jpaQueryFactory
                .select(hotDeal.count())
                .from(hotDeal)
                .where(getCondition(getHotDealsRequest))
                .fetchOne();

        return new PageImpl(hotDealPreviews, pageable, count);

    }

    public List<HotDealDto.NotClassifiedHotDeal> findNotClassifiedHotDeals(){

        return jpaQueryFactory
                .select(
                        Projections.constructor(HotDealDto.NotClassifiedHotDeal.class,
                                hotDeal.hotDealId,
                                hotDeal.hotDealTitle,
                                hotDeal.hotDealOriginalPrice,
                                hotDeal.hotDealDiscountPrice,
                                hotDeal.hotDealDiscountRate,
                                hotDeal.hotDealLink,
                                hotDeal.hotDealUploadTime,
                                product.modelName
                                )
                )
                .from(hotDeal)
                .where(
                        hotDeal.isDelete.eq(false),
                        hotDeal.product.productId.eq(1l)
                )
                .leftJoin(hotDealCandidate).on(hotDealCandidate.hotDealId.eq(hotDeal.hotDealId))
                .leftJoin(product).on(product.productId.eq(hotDealCandidate.candidateProductId))
                .orderBy(hotDeal.hotDealId.desc())
                .limit(30)
                .fetch();

    }

    public HotDealDto.HotDealPreview findHotDealByHotDealId(Long hotDealId) {
        return jpaQueryFactory
                .select(
                        getHotDealPreviewConstructorExpression()
                )
                .from(hotDeal)
                .where(
                        hotDeal.hotDealId.eq(hotDealId)
                )
                .fetchOne();



    }

    public Page<HotDealDto.HotDealPreview> findHotDealsByProductId(Long productId, Pageable pageable) {

        List<HotDealDto.HotDealPreview> hotDealPreviews = jpaQueryFactory
                .select(
                        getHotDealPreviewConstructorExpression()
                )
                .from(hotDeal)
                .where(
                        getProductIdCondition(productId)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(Arrays.asList(new OrderSpecifier(Order.ASC, hotDeal.isDelete),new OrderSpecifier(Order.ASC,hotDeal.hotDealDiscountPrice)).stream().toArray(OrderSpecifier[]::new))
                .fetch();

        Long count = jpaQueryFactory
                .select(hotDeal.count())
                .from(hotDeal)
                .where(getProductIdCondition(productId))
                .fetchOne();

        return new PageImpl(hotDealPreviews, pageable, count);

    }


    private BooleanExpression getProductIdCondition(Long productId) {
        if (productId == null) {
            return null;
        }

        return hotDeal.product.productId.eq(productId);
    }


    public Page<HotDealDto.HotDealPreview> findWeeklyPopularHotDeals(HotDealDto.GetHotDealsRequest getHotDealsRequest, Pageable pageable) {

        List<HotDealDto.HotDealPreview> hotDealPreviews = jpaQueryFactory
                .select(
                        getHotDealPreviewConstructorExpression()
                )
                .from(hotDeal)
                .where(
                        getWeeklyPopularCondition()
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(getAllOrderSpecifiers(pageable).stream().toArray(OrderSpecifier[]::new))
                .fetch();

        Long count = jpaQueryFactory
                .select(hotDeal.count())
                .from(hotDeal)
                .where(getWeeklyPopularCondition())
                .fetchOne();

        return new PageImpl(hotDealPreviews, pageable, count);

    }

    private BooleanExpression getWeeklyPopularCondition() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_WEEK,-7);
        return hotDeal.isDelete.eq(Boolean.FALSE)
                .and(hotDeal.hotDealViewCount.goe(4))
                .and(hotDeal.hotDealUploadTime.after(new Timestamp(calendar.getTimeInMillis())));
    }

    private ConstructorExpression<HotDealDto.HotDealPreview> getHotDealPreviewConstructorExpression() {
        return Projections.constructor(HotDealDto.HotDealPreview.class,
                hotDeal.hotDealId,
                hotDeal.hotDealTitle,
                hotDeal.hotDealOriginalPrice,
                hotDeal.hotDealDiscountPrice,
                hotDeal.hotDealDiscountRate,
                hotDeal.hotDealLink,
                hotDeal.hotDealUploadTime,
                hotDeal.hotDealViewCount,
                hotDeal.sourceSite,

                hotDeal.product.productId,
                hotDeal.product.modelName,
                hotDeal.product.manufacturer.manufacturerName,
                hotDeal.product.productPurpose.productPurposeName,

                hotDeal.isDelete
        );
    }


    private BooleanExpression getCondition(HotDealDto.GetHotDealsRequest getHotDealsRequest) {
        if (getHotDealsRequest == null) {
            return hotDeal.isDelete.eq(Boolean.FALSE);
        }

        return hotDeal.isDelete.eq(Boolean.FALSE)
                .and(getSearchCondition(getHotDealsRequest.getSearchBody()))
                .and(getSourceSitesCondition(getHotDealsRequest.getSourceSites()))
                .and(getManufacturerCondition(getHotDealsRequest.getManufacturerId()))
                .and(getProductPurposeCondition(getHotDealsRequest.getProductPurposeId()));
    }

    private BooleanExpression getManufacturerCondition(Long manufacturerId) {
        if (manufacturerId == null) {
            return null;
        }

        return hotDeal.product.manufacturer.manufacturerId.eq(manufacturerId);
    }

    private BooleanExpression getProductPurposeCondition(Long productPurposeId) {
        if (productPurposeId == null) {
            return null;
        }

        return hotDeal.product.productPurpose.productPurposeId.eq(productPurposeId);
    }

    private BooleanExpression getSearchCondition(String searchBody) {
        if (searchBody == null) {
            return null;
        }
        if (searchBody == "") {
            return null;
        }

        return hotDeal.hotDealTitle.toLowerCase().contains(searchBody.toLowerCase())
                .or(hotDeal.product.modelNameSearch.toLowerCase().contains(searchBody.toLowerCase()));
    }

    private BooleanExpression getSourceSitesCondition(List<String> sourceSites) {
        if (sourceSites == null) {
            return null;
        }
        if (sourceSites.size() <= 0) {
            return null;
        }
        return hotDeal.sourceSite.in(sourceSites);
    }

    private List<OrderSpecifier> getAllOrderSpecifiers(Pageable pageable) {
        if (pageable.getSort().isEmpty()) {
            return null;
        }
        List<OrderSpecifier> orderSpecifiers = new ArrayList<>();
        for (Sort.Order order : pageable.getSort()) {
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
