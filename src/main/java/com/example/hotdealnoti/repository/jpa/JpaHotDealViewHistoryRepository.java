package com.example.hotdealnoti.repository.jpa;

import com.example.hotdealnoti.hotdeal.domain.ConnectionHistory;
import com.example.hotdealnoti.hotdeal.domain.HotDealViewHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaHotDealViewHistoryRepository extends JpaRepository<HotDealViewHistory,Long> {
}
