package org.zerock.ex2.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
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
}
