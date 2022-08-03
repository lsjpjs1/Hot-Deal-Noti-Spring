package com.example.hotdealnoti.repository.jpa;

import com.example.hotdealnoti.hotdeal.domain.ConnectionHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaConnectionHistoryRepository extends JpaRepository<ConnectionHistory,Long> {
}
