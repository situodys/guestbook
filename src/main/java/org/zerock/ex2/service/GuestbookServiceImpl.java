package org.zerock.ex2.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.zerock.ex2.dto.GuestBookDTO;
import org.zerock.ex2.dto.PageRequestDTO;
import org.zerock.ex2.dto.PageResponseDTO;
import org.zerock.ex2.dto.SearchType;
import org.zerock.ex2.entity.Guestbook;
import org.zerock.ex2.repository.GuestbookRepository;

import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Function;

@RequiredArgsConstructor
@Service
@Log4j2
public class GuestbookServiceImpl implements GuestbookService {

    private final GuestbookRepository repository;

    @Override
    public Long register(GuestBookDTO dto) {

        log.info("DTO-------------");
        log.info(dto);

        Guestbook entity = dto.toEntity();

        log.info(entity);
        repository.save(entity);
        return entity.getGno();
    }

    @Override
    public PageResponseDTO<GuestBookDTO, Guestbook> getList(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("gno").descending());

        Page<Guestbook> result = repository.findAll(pageable);

        Function<Guestbook, GuestBookDTO> fn = (entity -> entity.toDTO());

        return new PageResponseDTO<>(result, fn);
    }

    @Override
    public GuestBookDTO read(Long gno) {
        Optional<Guestbook> result = repository.findById(gno);

        if (result.isPresent()) {
            return result.get().toDTO();
        }
        return null;
    }

    @Override
    public void modify(GuestBookDTO dto) {
        repository.save(dto.toEntity());
    }

    @Override
    public void remove(Long gno) {
        repository.deleteById(gno);
    }

    @Override
    public PageResponseDTO<GuestBookDTO, Guestbook> getSearch(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("gno").descending());
        requestDTO.setType(new ArrayList<>());

        if (requestDTO.getTypeSignal().contains("t")) {
            requestDTO.getType().add(SearchType.Title);
        }
        if (requestDTO.getTypeSignal().contains("c")) {
            requestDTO.getType().add(SearchType.Content);
        }
        if (requestDTO.getTypeSignal().contains("w")) {
            requestDTO.getType().add(SearchType.Writer);
        }

        Page<Guestbook> result = repository.searchAll(requestDTO,pageable);

        Function<Guestbook, GuestBookDTO> fn = (entity -> entity.toDTO());

        return new PageResponseDTO<>(result, fn);

    }
}
