package com.example.projectdeploy.SimilarPosts;

import com.example.projectdeploy.Community.Post.Model.Post;
import com.example.projectdeploy.Community.Post.Repo.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@Service
public class ElasticService {
    @Autowired
    PostRepo postRepo;

    @Value("${token}")
    private String token;

    public Post getPost(String postId) {
        UUID uuid = UUID.fromString(postId);
        return postRepo.findPostById(uuid);
    }


    public void addToElastic(Post post)  {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        headers.set("Content-Type", "application/json");
        MyPayload payload = new MyPayload(
                post.getDescription(),
                post.getId().toString()
        );

        UriComponents uri = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("healthometer-d783e1.ent.uksouth.azure.elastic-cloud.com")
                .path("/api/as/v1/engines/postsearch/documents")
                .build();
        HttpEntity<?> entity = new HttpEntity<>(payload,headers);

        ResponseEntity<String> response = new RestTemplate().exchange(uri.toUriString(), HttpMethod.POST, entity, String.class);

        System.out.println(response.getBody());




    }
}
