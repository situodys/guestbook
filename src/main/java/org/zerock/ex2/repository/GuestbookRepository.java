package org.zerock.ex2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.zerock.ex2.entity.Guestbook;

public interface GuestbookRepository extends JpaRepository<Guestbook,Long>, GuestbookRepositoryCustom, QuerydslPredicateExecutor {
}
