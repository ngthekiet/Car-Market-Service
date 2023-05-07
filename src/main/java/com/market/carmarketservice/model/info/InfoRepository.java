package com.market.carmarketservice.model.info;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InfoRepository extends JpaRepository<Info, Integer> {
    boolean existsInfoById(int id);
}
