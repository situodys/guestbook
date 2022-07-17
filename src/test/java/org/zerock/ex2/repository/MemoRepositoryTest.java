package org.zerock.ex2.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.zerock.ex2.entity.Memo;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemoRepositoryTest {

    @Autowired
    MemoRepository memoRepository;

    @Test
    public void testClass() throws Exception{
        //give

        //when

        //then
        System.out.println(memoRepository.getClass().getName());
    }

    @Test
    public void testInsertDummies() throws Exception{
        //give
        IntStream.rangeClosed(1,100).forEach(i->{
            Memo memo = Memo.builder().memoText("Sample..." + i).build();
            memoRepository.save(memo);
        });
        //when

        //then
    }

    @Test
    public void testSelect() throws Exception{
        //give
        Long mno = 100L;

        Optional<Memo> result = memoRepository.findById(mno);
        System.out.println("===========================");

        //when
        if (result.isPresent()) {
            Memo memo = result.get();
            System.out.println(memo);
        }

        //then
    }

    @Test
    public void testPageDefault() throws Exception{
        //give
        Pageable pageable = PageRequest.of(9, 10);
        //when

        Page<Memo> result = memoRepository.findAll(pageable);
        //then
        System.out.println(result);

        System.out.println("-------------------------------");

        System.out.println("Total pages: "+result.getTotalPages());
        System.out.println("Total Count: "+result.getTotalElements());
        System.out.println("Current Page number : "+ result.getNumber());
        System.out.println("Page size: " + result.getSize());
        System.out.println("Has next Page? "+ result.hasNext());
        System.out.println("First page? " + result.isFirst());

        System.out.println("-------------------------------");
        result.getContent().stream().forEach(System.out::println);
    }

    @Test
    public void testSort() throws Exception{
        //give
        Sort sort1 = Sort.by("id").descending();
        PageRequest pageRequest = PageRequest.of(0, 10, sort1);
        //when
        Page<Memo> result = memoRepository.findAll(pageRequest);
        //then
        result.get().forEach(System.out::println);

        /*
        sort1, sort2, sortAll = sort1.and(sort2);
         */
    }

    @Test
    public void testQueryMethods() throws Exception{
        //give
        List<Memo> memos = memoRepository.findByIdBetweenOrderByIdDesc(70l, 80L);
        //when

        //then
        memos.stream().forEach(System.out::println);

    }

}