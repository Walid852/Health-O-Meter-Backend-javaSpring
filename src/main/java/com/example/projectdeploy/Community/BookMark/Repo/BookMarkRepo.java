package com.example.projectdeploy.Community.BookMark.Repo;


import com.example.projectdeploy.Community.BookMark.Model.BookMark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookMarkRepo extends JpaRepository<BookMark, UUID> {
    @Query("select B from BookMark B where B.id=?1")
    public BookMark findBookMarkById(UUID id);
    @Query("select B from BookMark B where B.user.id=?1")
    public List<BookMark> MyBookMark(UUID userId);
}
