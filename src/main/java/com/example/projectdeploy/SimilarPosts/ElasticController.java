package com.example.projectdeploy.SimilarPosts;

import com.example.projectdeploy.Community.Post.Model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
public class ElasticController {
    private final ElasticService elasticService;
    @Value("${token}")
    private String token;

    @Autowired
    public ElasticController(ElasticService elasticService) {
        this.elasticService = elasticService;
    }
    @GetMapping(value = "/posts/similar")
    public @ResponseBody
    List<Post> findSimilarPosts(@RequestParam String description) {
        HttpHeaders headers = new HttpHeaders();

        headers.set("Authorization", "Bearer " + token);
        UriComponents uri = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("healthometer-d783e1.ent.uksouth.azure.elastic-cloud.com")
                .path("/api/as/v1/engines/postsearch/search").
                queryParam("query", description)
                .build();
        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<Response> response = new RestTemplate().exchange(uri.toUriString(), HttpMethod.POST, entity, Response.class);
        ArrayList<JsonParsing> results= Objects.requireNonNull(response.getBody()).getResults();
        List<Post> similarPosts=new ArrayList<>();
        for(JsonParsing codebeautify:results){
            Post post;
            post= elasticService.getPost(codebeautify.PostidObject.getRaw());
            if(post!=null) {
                if (!post.isDeleted()) {
                    similarPosts.add(post);
                }
            }
        }

        return similarPosts;
    }
}
