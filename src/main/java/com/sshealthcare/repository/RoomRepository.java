package com.sshealthcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sshealthcare.model.Room;

public interface RoomRepository extends JpaRepository<Room, Integer>{

	@Query(value = "select count(type) from room ",nativeQuery = true)
	long countRoomsByType(String type);

}
