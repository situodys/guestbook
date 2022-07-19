package org.zerock.ex2.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.zerock.ex2.dto.PageRequestDTO;
import org.zerock.ex2.dto.SearchType;
import org.zerock.ex2.entity.Guestbook;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class GuestBookRepositoryTests {

    @Autowired
    private GuestbookRepository guestbookRepository;

    @Autowired
    private GuestbookRepositorySupport guestbookRepositorySupport;

    @Test
    public void insertDummies() throws Exception {
        //give
        IntStream.rangeClosed(1, 300).forEach(i -> {
            Guestbook guestbook = Guestbook.builder()
                    .title("Title..." + i)
                    .content("Content..." + i)
                    .writer("user" + i)
                    .build();
            System.out.println(guestbookRepository.save(guestbook));
        });
        //when

        //then
    }

    @Test
    public void updateTest() throws Exception {
        //give
        Optional<Guestbook> result = guestbookRepository.findById(300L);
        //when
        if (result.isPresent()) {
            Guestbook guestbook = result.get();

            guestbook.modifyTitle("modified Title...");
            guestbook.modifyContent("modified Content...");

            guestbookRepository.save(guestbook);
        }
        //then
    }

    @Test
    public void querydslTest() throws Exception {
        //give

        //when
        List<Guestbook> result = guestbookRepository.findByWriter("user123");
        //then
        System.out.println(result.size() + " " + result.get(0));
    }

    @Test
    public void searchTest() throws Exception {
        //give
        SearchType[] searchTypes = {SearchType.Title, SearchType.Content};
        PageRequestDTO request = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .type(Arrays.asList(searchTypes))
                .keyword("16")
                .build();

        Pageable pageable = request.getPageable(Sort.by("gno").descending());


        //when
        Page<Guestbook> guestbooks = guestbookRepository.searchAll(request, pageable);
        //then

        for (Guestbook guestbook : guestbooks) {
            System.out.println(guestbook);
        }

    }
}
