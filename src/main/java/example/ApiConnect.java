package example;

import example.pojos.Post;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;



public class ApiConnect {

    private final String url = "https://jsonplaceholder.typicode.com/posts?userId=";

    public ApiConnect() {

    }

    public Post[] getPosts(String id) {
        RestTemplate restTemplate = new RestTemplate();
        Post[] response = restTemplate.getForObject(this.url + id, Post[].class);

        return response;
    }
}
