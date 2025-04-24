package com.TourConnect.TourConnect.infrastructure.repositories.jpa;


import com.TourConnect.TourConnect.domain.entities.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface JpaRoomTypeRepository extends JpaRepository<RoomType, UUID> {
    List<RoomType> findByHotel_Id(UUID hotelId);

}