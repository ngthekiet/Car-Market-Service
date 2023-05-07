package com.market.carmarketservice.service.info;

import com.market.carmarketservice.model.info.Info;

import java.util.List;

public interface InfoService {
    public List<Info> getInfos();

    public Info getInfo(int id);

    public boolean createInfo(Info info);

    public boolean updateInfo(Info info, int id);

    public boolean deleteInfo(int id);

    public boolean existInfo(int id);
}
