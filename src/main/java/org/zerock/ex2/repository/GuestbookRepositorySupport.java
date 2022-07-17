package org.zerock.ex2.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import org.zerock.ex2.entity.Guestbook;
import static org.zerock.ex2.entity.QGuestbook.guestbook;

import java.util.List;

@Repository
public class GuestbookRepositorySupport extends QuerydslRepositorySupport {
    private final JPAQueryFactory jpaQueryFactory;

    public GuestbookRepositorySupport(JPAQueryFactory jpaQueryFactory) {
        super(Guestbook.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public List<Guestbook> findByName(String name) {
        return jpaQueryFactory
                .selectFrom(guestbook)
                .where(guestbook.writer.eq(name))
                .fetch();
    }
}
