package org.zerock.ex2.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.zerock.ex2.dto.PageRequestDTO;
import org.zerock.ex2.entity.Guestbook;

import java.util.List;

public interface GuestbookRepositoryCustom {
    List<Guestbook> findByWriter(String writer);

    Page<Guestbook> searchAll(PageRequestDTO pageRequestDTO, Pageable pageable);
}
