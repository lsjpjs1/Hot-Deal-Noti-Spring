package com.example.hotdealnoti.hotdeal.service;

import com.example.hotdealnoti.exception.CustomException;
import com.example.hotdealnoti.exception.ErrorCode;
import com.example.hotdealnoti.hotdeal.domain.FavoriteHotDeal;
import com.example.hotdealnoti.hotdeal.dto.HotDealDto;
import com.example.hotdealnoti.messagequeue.domain.HotDeal;
import com.example.hotdealnoti.product.domain.Product;
import com.example.hotdealnoti.repository.jpa.JpaFavoriteHotDealRepository;
import com.example.hotdealnoti.repository.jpa.JpaHotDealRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteHotDealService {

    private final JpaHotDealRepository jpaHotDealRepository;
    private final JpaFavoriteHotDealRepository jpaFavoriteHotDealRepository;

    @Transactional
    public void deletePermanentHotDeal(Long hotDealId) {

        HotDeal hotDeal = jpaHotDealRepository.findById(hotDealId).get();
        hotDeal.setIsDelete(true);
        hotDeal.setIsPermanentDelete(true);
        if (hotDeal.getIsCandidateProduct()){
            hotDeal.setProduct(Product.builder().productId(1l).build());
            hotDeal.setIsCandidateProduct(false);
        }
        jpaHotDealRepository.save(hotDeal);
    }

    @Transactional
    public void deleteHotDeal(Long hotDealId) {

        HotDeal hotDeal = jpaHotDealRepository.findById(hotDealId).get();
        hotDeal.setIsDelete(true);
        if (hotDeal.getIsCandidateProduct()){
            hotDeal.setProduct(Product.builder().productId(1l).build());
            hotDeal.setIsCandidateProduct(false);
        }
        jpaHotDealRepository.save(hotDeal);
    }

    @Transactional
    public void deleteFavoriteHotDeal(HotDealDto.DeleteFavoriteHotDealRequest deleteFavoriteHotDealRequest) {

        FavoriteHotDeal favoriteHotDeal = jpaFavoriteHotDealRepository.findByAccount_AccountIdAndHotDeal_HotDealId(deleteFavoriteHotDealRequest.getAccountId(), deleteFavoriteHotDealRequest.getHotDealId())
                .orElseThrow(() -> new CustomException(ErrorCode.FAVORITE_HOT_DEAL_NOT_FOUND));
        favoriteHotDeal.setIsDelete(true);
        jpaFavoriteHotDealRepository.save(favoriteHotDeal);
    }

    @Transactional
    public void deleteProductIdHotDeal(Long hotDealId) {

        HotDeal hotDeal = jpaHotDealRepository.findById(hotDealId).get();
        hotDeal.setProduct(Product.builder().productId(1l).build());
        hotDeal.setIsCandidateProduct(true);
        jpaHotDealRepository.save(hotDeal);
    }



}
