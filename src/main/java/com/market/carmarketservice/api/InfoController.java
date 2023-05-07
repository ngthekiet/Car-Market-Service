package com.market.carmarketservice.api;

import com.market.carmarketservice.model.info.Info;
import com.market.carmarketservice.service.info.InfoService;
import com.market.carmarketservice.service.message.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping(value = "/api/pri")
public class InfoController {
    private final InfoService infoService;
    private final MessageService messageService;

    @RequestMapping(value = "/infos", method = RequestMethod.GET)
    public ResponseEntity<Object> getAllInfo() {
        try {
            return new ResponseEntity<>(infoService.getInfos(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(messageService.notFound(), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/info/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getInfo(@PathVariable("id") int id) {
        if (infoService.getInfo(id) != null)
            return new ResponseEntity<>(infoService.getInfo(id), HttpStatus.OK);
        return new ResponseEntity<>(messageService.notFound(), HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/info", method = RequestMethod.POST)
    public ResponseEntity<Object> createInfo(@RequestBody Info info) {
        if (infoService.createInfo(info))
            return new ResponseEntity<>(messageService.successes(), HttpStatus.OK);
        return new ResponseEntity<>(messageService.fail(), HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/info/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateInfo(@RequestBody Info info, @PathVariable("id") int id) {
        if (infoService.updateInfo(info, id))
            return new ResponseEntity<>(messageService.successes(), HttpStatus.OK);
        return new ResponseEntity<>(messageService.fail(), HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/info/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteInfo(@PathVariable("id") int id) {
        if (infoService.deleteInfo(id))
            return new ResponseEntity<>(messageService.successes(), HttpStatus.OK);
        return new ResponseEntity<>(messageService.fail(), HttpStatus.BAD_REQUEST);
    }
}
