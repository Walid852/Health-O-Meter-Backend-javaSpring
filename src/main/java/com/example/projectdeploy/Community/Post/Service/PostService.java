package com.example.projectdeploy.Community.Post.Service;

import com.example.projectdeploy.Community.Comment.Model.Comment;
import com.example.projectdeploy.Community.Like.Model.Likee;
import com.example.projectdeploy.Community.Post.DTO.CommentResponse;
import com.example.projectdeploy.Community.Post.Repo.PostRepo;
import com.example.projectdeploy.Shared.Response;
import com.example.projectdeploy.Shared.StaticsText;
import com.example.projectdeploy.User.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PostService {

    @Autowired
    PostRepo postRepo;

    @Autowired
    UserRepo userRepo;

    @Transactional
    public List<Likee> getMyLike(UUID postId){
        return postRepo.getPostLikes(postId);
    }

    @Transactional
    public Response<CommentResponse> getMyComments(UUID postId){
        List<CommentResponse> commentResponses=new ArrayList<>();
        List<Comment> comments=postRepo.getPostComments(postId);
        for(Comment comment:comments){
            CommentResponse commentResponse= new CommentResponse();
            commentResponse.setCommentId(comment.getId());
            commentResponse.setContent(comment.getComment());
            commentResponse.setPhoto(comment.getUser().getPhoto());
            commentResponse.setUserId(comment.getUser().getId());
            commentResponse.setName(comment.getUser().getName());
            commentResponse.setShowReplies(!comment.getReplies().isEmpty());
            commentResponses.add(commentResponse);
        }
        return new Response<>(true, StaticsText.MessageForTest("comments", "are retrieved"), commentResponses);
    }

}
