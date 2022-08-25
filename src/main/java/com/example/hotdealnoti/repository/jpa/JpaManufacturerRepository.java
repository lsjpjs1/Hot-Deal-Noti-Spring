package com.example.hotdealnoti.repository.jpa;

import com.example.hotdealnoti.product.domain.Manufacturer;
import com.example.hotdealnoti.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaManufacturerRepository extends JpaRepository<Manufacturer,Long> {
}
