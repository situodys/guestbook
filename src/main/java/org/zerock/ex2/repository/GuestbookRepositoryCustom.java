package org.zerock.ex2.repository;

import org.zerock.ex2.entity.Guestbook;

import java.util.List;

public interface GuestbookRepositoryCustom {
    List<Guestbook> findByWriter(String writer);
}
