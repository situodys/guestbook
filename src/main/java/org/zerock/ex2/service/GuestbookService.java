package org.zerock.ex2.service;

import org.zerock.ex2.dto.GuestBookDTO;
import org.zerock.ex2.dto.PageRequestDTO;
import org.zerock.ex2.dto.PageResponseDTO;
import org.zerock.ex2.entity.Guestbook;

public interface GuestbookService {
    Long register(GuestBookDTO dto);

    PageResponseDTO<GuestBookDTO, Guestbook> getList(PageRequestDTO requestDTO);

    GuestBookDTO read(Long gno);

    void modify(GuestBookDTO dto);

    void remove(Long gno);
}
