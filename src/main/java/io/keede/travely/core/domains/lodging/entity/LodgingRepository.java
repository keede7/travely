package io.keede.travely.core.domains.lodging.entity;

import org.springframework.data.jpa.repository.JpaRepository;

/**
* @author keede
* Created on 2023/10/21
*/
public interface LodgingRepository extends JpaRepository<Lodging, Long> {
}
