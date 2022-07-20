package org.zerock.ex2.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;
import org.zerock.ex2.dto.PageRequestDTO;
import org.zerock.ex2.dto.SearchType;
import org.zerock.ex2.entity.Guestbook;

import java.util.List;

import static org.zerock.ex2.entity.QGuestbook.guestbook;

@RequiredArgsConstructor
public class GuestbookRepositoryImpl implements GuestbookRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Guestbook> findByWriter(String writer) {
        return jpaQueryFactory
                .selectFrom(guestbook)
                .where(guestbook.writer.eq(writer))
                .fetch();
    }

    /*
    pageRequestDTO의 type,keyword에 따라
    동적인 쿼리를 처리하는게 목표
    type 종류 3가지: title,content,writer
    keyword 종류 2가지 : 있거나 null이거나.
     */



}
