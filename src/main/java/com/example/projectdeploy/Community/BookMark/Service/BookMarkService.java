package com.example.projectdeploy.Community.BookMark.Service;

import com.example.projectdeploy.Community.BookMark.Model.BookMark;
import com.example.projectdeploy.Community.BookMark.Repo.BookMarkRepo;
import com.example.projectdeploy.Community.BookMark.dto.BookMarkRequest;
import com.example.projectdeploy.Community.Post.Model.Post;
import com.example.projectdeploy.Community.Post.Repo.PostRepo;
import com.example.projectdeploy.User.Model.User;
import com.example.projectdeploy.User.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class BookMarkService {
    @Autowired
    BookMarkRepo bookMarkRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    PostRepo postRepo;

    @Transactional
    public BookMark AddBookMark(BookMarkRequest bookMark) throws IOException {
        BookMark bookMarkObject=new BookMark();
        User user=userRepo.findByUserId(bookMark.userId);
        if(postRepo.findById(bookMark.postId).isPresent()) {
            if(bookMarkRepo.isReported(user.getId(),bookMark.postId)==null) {
                Post post = postRepo.findById(bookMark.postId).get();
                bookMarkObject.setPost(post);
            }else {
                return null;
            }
        }
        bookMarkObject.setUser(user);
        bookMarkRepo.save(bookMarkObject);
        return bookMarkObject;
    }
    public String DeleteBookMark(UUID bookMarkId) throws IOException {
        BookMark bookMarkObject=bookMarkRepo.findBookMarkById(bookMarkId);
        bookMarkRepo.delete(bookMarkObject);
        return "bookMark deleted";
    }
    public BookMark findBookMarkById(UUID bookMarkId) throws IOException {
        return bookMarkRepo.findBookMarkById(bookMarkId);
    }
    public List<BookMark> MyBookMark(UUID userId) throws IOException {
        return bookMarkRepo.MyBookMark(userId);
    }
}
