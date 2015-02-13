package com.thoersch.seeds.persistence.users;

import com.thoersch.seeds.representations.users.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<User,Long> {
}
