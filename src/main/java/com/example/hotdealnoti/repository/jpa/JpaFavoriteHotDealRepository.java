package com.example.hotdealnoti.repository.jpa;

import com.example.hotdealnoti.auth.domain.Account;
import com.example.hotdealnoti.enums.AccountType;
import com.example.hotdealnoti.hotdeal.domain.FavoriteHotDeal;
import com.example.hotdealnoti.messagequeue.domain.HotDeal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaFavoriteHotDealRepository extends JpaRepository<FavoriteHotDeal,Long> {


    Optional<FavoriteHotDeal> findByAccount_AccountIdAndHotDeal_HotDealId(Long accountId,Long hotDealId);
}
