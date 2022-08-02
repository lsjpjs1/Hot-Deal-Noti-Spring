package com.example.hotdealnoti.hotdeal.repository;

import com.example.hotdealnoti.messagequeue.domain.HotDeal;
import org.springframework.data.repository.CrudRepository;

public interface RedisHotDealRepository extends CrudRepository<HotDeal,String> {
}
