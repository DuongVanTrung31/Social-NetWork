package com.codegym.backendjavasocialnetwork.service.impl;

import com.codegym.backendjavasocialnetwork.entity.RelationalShip;
import com.codegym.backendjavasocialnetwork.repository.FriendShipRepository;
import com.codegym.backendjavasocialnetwork.service.FriendShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FriendShipServiceImpl implements FriendShipService {

    @Autowired
    private FriendShipRepository friendShipRepository;


    @Override
    public Optional<RelationalShip> findRelationshipByUser1AndUser2(Long user1, Long user2) {
        return friendShipRepository.findRelationshipByUser1_IdAndUser2_Id(user1, user2);

    }

    @Override
    public Iterable<RelationalShip> findAll() {
        return friendShipRepository.findAll();
    }

    @Override
    public Optional<RelationalShip> findById(Long id) {
        return friendShipRepository.findById(id);
    }

    @Override
    public void save(RelationalShip relationalShip) {
        friendShipRepository.save(relationalShip);
    }

    @Override
    public void remove(Long id) {
        friendShipRepository.deleteById(id);
    }
}
