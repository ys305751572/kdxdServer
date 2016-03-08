package com.leoman.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.leoman.entity.Member;

/**
 * Created by wangbin on 2015/8/10.
 */
public interface MemberDao extends JpaRepository<Member, Integer> {

    @Query("select a from Member a where a.username = ?1 and a.password = ?2")
    public Member findByUnameAndPword(String username,String password);

    @Query("select a from Member a where a.type = 'BUSINESS'")
    public Page<Member> findByBusiness(Pageable pageable);
    
    @Query("select a from Member a where a.type = 'GLOBLE'")
    public Page<Member> findByAdmin(Pageable pageable);

    @Query("select a from Member a where a.username = ?1 and a.password = ?2 and a.type = ?3")
    public  Member findByUnameAndPword(String username,String password,String type);
}
