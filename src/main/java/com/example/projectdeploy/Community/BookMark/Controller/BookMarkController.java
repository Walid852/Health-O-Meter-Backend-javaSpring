package com.example.projectdeploy.Community.BookMark.Controller;

import com.example.projectdeploy.Community.BookMark.Model.BookMark;
import com.example.projectdeploy.Community.BookMark.Service.BookMarkService;
import com.example.projectdeploy.Community.BookMark.dto.BookMarkRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
public class BookMarkController {
    @Autowired
    BookMarkService bookMarkService;
    @PostMapping(path="/AddBookMark")
    public @ResponseBody
    BookMark AddBookMark(@RequestBody BookMarkRequest bookMark) throws IOException {
        return bookMarkService.AddBookMark(bookMark);
    }
    @DeleteMapping(path="/DeleteBookMark")
    public @ResponseBody
    String DeleteBookMark(@RequestParam UUID bookMarkId) throws IOException {
        return bookMarkService.DeleteBookMark(bookMarkId);
    }
    @GetMapping(path="/findBookMarkById")
    public @ResponseBody
    BookMark findBookMarkById(@RequestParam UUID bookMarkId) throws IOException {
        return bookMarkService.findBookMarkById(bookMarkId);
    }
    @GetMapping(path="/MyBookMark")
    public @ResponseBody
    List<BookMark> MyBookMark(@RequestParam UUID userId) throws IOException {
        return bookMarkService.MyBookMark(userId);
    }


}
