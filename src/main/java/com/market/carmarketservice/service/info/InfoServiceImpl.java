package com.market.carmarketservice.service.info;

import com.market.carmarketservice.model.info.Info;
import com.market.carmarketservice.model.info.InfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InfoServiceImpl implements InfoService {
    private final InfoRepository infoRepository;

    @Override
    public List<Info> getInfos() {
        return (List<Info>) infoRepository.findAll();
    }

    @Override
    public Info getInfo(int id) {
        if (existInfo(id)) {
            Optional<Info> optional = infoRepository.findById(id);
            Info info = optional.get();
            return info;
        }
        return null;
    }

    @Override
    public boolean createInfo(Info other) {
        try {
            infoRepository.save(other);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateInfo(Info info, int id) {
        if (existInfo(id)) {
            info.setId(id);
            infoRepository.save(info);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteInfo(int id) {
        if (existInfo(id)) {
            infoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean existInfo(int id) {
        return infoRepository.existsInfoById(id);
    }
}
