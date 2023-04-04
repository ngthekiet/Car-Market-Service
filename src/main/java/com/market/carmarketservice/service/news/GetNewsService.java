package com.market.carmarketservice.service.news;

public interface GetNewsService {
    public abstract Object getPost(String link);

    public abstract Object getRss(String link);
}
