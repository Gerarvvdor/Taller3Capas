package com.nalas.pnccontrollers.repositories;

import com.nalas.pnccontrollers.domain.entities.Token;
import com.nalas.pnccontrollers.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TokenRepository extends JpaRepository<Token, UUID> {

    List<Token> findByUserAndActive(User user, Boolean active);

}
