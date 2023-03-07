package com.example.hotdealnoti.hotdeal.repository;

import com.example.hotdealnoti.auth.domain.Account;
import com.example.hotdealnoti.hotdeal.domain.QFavoriteHotDeal;
import com.example.hotdealnoti.hotdeal.domain.QRecommendationHotDeal;
import com.example.hotdealnoti.hotdeal.dto.HotDealDto;
import com.example.hotdealnoti.messagequeue.domain.QHotDeal;
import com.example.hotdealnoti.messagequeue.domain.QHotDealCandidate;
import com.example.hotdealnoti.product.domain.QProduct;
import com.example.hotdealnoti.product.domain.QProductAdditionalFunction;
import com.example.hotdealnoti.product.domain.QProductRanking;
import com.example.hotdealnoti.product.dto.ProductDto;
import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
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

import static com.querydsl.core.types.dsl.Expressions.list;
import static com.querydsl.core.types.dsl.Expressions.nullExpression;

@Repository
@RequiredArgsConstructor
@Slf4j
public class HotDealQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;
    private final QHotDeal hotDeal = QHotDeal.hotDeal;
    private final QHotDealCandidate hotDealCandidate = QHotDealCandidate.hotDealCandidate;
    private final QProduct product = QProduct.product;
    private final QRecommendationHotDeal recommendationHotDeal = QRecommendationHotDeal.recommendationHotDeal;
    private final QFavoriteHotDeal favoriteHotDeal = QFavoriteHotDeal.favoriteHotDeal;
    private final QProductRanking productRanking = QProductRanking.productRanking;
    private final QProductAdditionalFunction productAdditionalFunction = QProductAdditionalFunction.productAdditionalFunction;
    public <T extends Iterable<HotDealDto.HotDealPreview>> T setAdditionalFunction(Class<T> typeToken, T iterableObject) {
        iterableObject.forEach(hotDealPreview -> {
            hotDealPreview.setProductAdditionalFunctionDTOList(findProductAdditionalFunctions(hotDealPreview.getProductId()));
        });
        return typeToken.cast(iterableObject);
    }

    public List<ProductDto.ProductAdditionalFunctionDTO> findProductAdditionalFunctions(Long productId){
        return jpaQueryFactory
                .select(
                        Projections.constructor(
                                ProductDto.ProductAdditionalFunctionDTO.class,
                                productAdditionalFunction.productFunction.productFunctionType.productFunctionTypeId,
                                productAdditionalFunction.productFunction.productFunctionId,
                                productAdditionalFunction.productFunction.productFunctionType.productFunctionTypeName,
                                productAdditionalFunction.productFunction.productFunctionName
                        )
                )
                .from(productAdditionalFunction)
                .where(productAdditionalFunction.product.productId.eq(productId))
                .fetch();
    }

    public Page<HotDealDto.HotDealPreview> findHotDeals(HotDealDto.GetHotDealsRequest getHotDealsRequest, Pageable pageable) {
        List<HotDealDto.HotDealPreview> hotDealPreviews = jpaQueryFactory
                .select(
                        getHotDealPreviewConstructorExpression()
                )
                .from(hotDeal)
                .leftJoin(productRanking).on(productRanking.product.productId.eq(hotDeal.product.productId))
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
        Page<HotDealDto.HotDealPreview> hotDeals = new PageImpl(hotDealPreviews, pageable, count);
        Page page = setAdditionalFunction(Page.class, hotDeals);
        return hotDeals;

    }

    public List<HotDealDto.HotDealPreview> findRecommendationHotDeals() {
        List<HotDealDto.HotDealPreview> hotDealPreviews = jpaQueryFactory
                .select(
                        getHotDealPreviewConstructorExpression()
                )
                .from(recommendationHotDeal)
                .leftJoin(hotDeal).on(recommendationHotDeal.hotDeal.hotDealId.eq(hotDeal.hotDealId))
                .leftJoin(productRanking).on(productRanking.product.productId.eq(hotDeal.product.productId))
                .where(
                        hotDeal.isDelete.eq(false)
                )
                .orderBy(hotDeal.hotDealUploadTime.desc())
                .fetch();
        return setAdditionalFunction(List.class, hotDealPreviews);

    }


    public List<HotDealDto.HotDealPreview> findFavoriteHotDeals(Account account) {
        List<HotDealDto.HotDealPreview> hotDealPreviews = jpaQueryFactory
                .select(
                        getHotDealPreviewConstructorExpression()
                )
                .from(favoriteHotDeal)
                .leftJoin(hotDeal).on(favoriteHotDeal.hotDeal.hotDealId.eq(hotDeal.hotDealId))
                .leftJoin(productRanking).on(productRanking.product.productId.eq(hotDeal.product.productId))
                .where(
                        favoriteHotDeal.isDelete.eq(false),
                        favoriteHotDeal.account.accountId.eq(account.getAccountId())
                )
                .fetch();

        return setAdditionalFunction(List.class, hotDealPreviews);
    }


    public List<HotDealDto.NotClassifiedHotDeal> findNotClassifiedHotDeals() {

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
//                        hotDeal.isDelete.eq(false),
//                        hotDeal.product.productId.ne(1l)
                        hotDeal.returnItem.returnItemId.eq(0l)
                        , hotDeal.isCandidateProduct.eq(true)
                )
                .leftJoin(product).on(product.productId.eq(hotDeal.product.productId))
                .orderBy(hotDeal.hotDealId.desc())
                .limit(30)
                .fetch();

    }

    public HotDealDto.HotDealPreview findHotDealByHotDealId(Long hotDealId) {
        HotDealDto.HotDealPreview hotDealPreview = jpaQueryFactory
                .select(
                        getHotDealPreviewConstructorExpression()
                )
                .from(hotDeal)
                .leftJoin(productRanking).on(productRanking.product.productId.eq(hotDeal.product.productId))
                .where(
                        hotDeal.hotDealId.eq(hotDealId)
                )
                .fetchOne();

        hotDealPreview.setProductAdditionalFunctionDTOList(findProductAdditionalFunctions(hotDealPreview.getProductId()));
        return hotDealPreview;

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
                .leftJoin(productRanking).on(productRanking.product.productId.eq(hotDeal.product.productId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(Arrays.asList(new OrderSpecifier(Order.ASC, hotDeal.isDelete), new OrderSpecifier(Order.ASC, hotDeal.hotDealDiscountPrice)).stream().toArray(OrderSpecifier[]::new))
                .fetch();

        Long count = jpaQueryFactory
                .select(hotDeal.count())
                .from(hotDeal)
                .where(getProductIdCondition(productId))
                .fetchOne();

        Page page = new PageImpl(hotDealPreviews, pageable, count);
        return setAdditionalFunction(Page.class, page);

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
                .leftJoin(productRanking).on(productRanking.product.productId.eq(hotDeal.product.productId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(getAllOrderSpecifiers(pageable).stream().toArray(OrderSpecifier[]::new))
                .fetch();

        Long count = jpaQueryFactory
                .select(hotDeal.count())
                .from(hotDeal)
                .where(getWeeklyPopularCondition())
                .fetchOne();


        Page page = new PageImpl(hotDealPreviews, pageable, count);
        return setAdditionalFunction(Page.class, page);

    }

    public List<HotDealDto.HotDealPreview> findEntireWeeklyPopularHotDeal() {

        List<HotDealDto.HotDealPreview> hotDealPreviews = jpaQueryFactory
                .select(
                        getHotDealPreviewConstructorExpression()
                )
                .from(hotDeal)
                .leftJoin(productRanking).on(productRanking.product.productId.eq(hotDeal.product.productId))
                .where(
                        getWeeklyPopularCondition()
                )
                .fetch();

        return setAdditionalFunction(List.class, hotDealPreviews);
    }

    private BooleanExpression getWeeklyPopularCondition() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_WEEK, -7);
        return hotDeal.isDelete.eq(Boolean.FALSE)
                .and(hotDeal.hotDealViewCount.goe(10))
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

                hotDeal.isDelete,

                hotDeal.hotDealThumbnailUrl,

                hotDeal.returnItem.returnItemId,
                hotDeal.returnItem.returnItemQuality,
                hotDeal.returnItem.returnItemQualityDetail,
                hotDeal.returnItem.returnItemSaleStatus,

                productRanking.productRankingNumber,
                hotDeal.product.productPurpose.productPurposeId,

                hotDeal.isCandidateProduct
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
                .and(getProductPurposeCondition(getHotDealsRequest.getProductPurposeId()))
                .and(getShowReturnItemCondition(getHotDealsRequest.getIsShowReturnItem()))
                .and(getDiscountRateCondition(getHotDealsRequest.getMinDiscountRate(), getHotDealsRequest.getMaxDiscountRate()));
    }

    private BooleanExpression getDiscountRateCondition(Integer minDiscountRate, Integer maxDiscountRate) {
        Integer minQueryDiscountRate = 0;
        Integer maxQueryDiscountRate = 100;

        if (minDiscountRate != null) {
            minQueryDiscountRate = minDiscountRate;
        }

        if (maxDiscountRate != null) {
            maxQueryDiscountRate = maxDiscountRate;
        }

        return hotDeal.hotDealDiscountRate.goe(minQueryDiscountRate)
                .and(hotDeal.hotDealDiscountRate.loe(maxQueryDiscountRate));
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

    private BooleanExpression getShowReturnItemCondition(Boolean isShowReturnItem) {
        if (isShowReturnItem == null) {
            return hotDeal.returnItem.returnItemId.eq(0l);
        }
        if (isShowReturnItem) {
            return hotDeal.returnItem.returnItemId.eq(0l).not();
        } else {
            return hotDeal.returnItem.returnItemId.eq(0l);
        }
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
