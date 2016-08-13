package mx.hercarr.chatsapp.rest;

import java.util.List;

import mx.hercarr.chatsapp.model.User;

public class UserResponse {

    private List<User> results;

    public List<User> getResults() {
        return results;
    }

    public void setResults(List<User> results) {
        this.results = results;
    }

}