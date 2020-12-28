package com.example.project.controller;

import com.example.project.model.zone.ZoneDTO;
import com.example.project.service.zone.IZoneService;
import com.example.project.utils.URLUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping(URLUtils.ZONE)
@Controller
public class ZoneController {
    private final IZoneService zoneService;

    @Autowired
    public ZoneController(IZoneService zoneService) {
        this.zoneService = zoneService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ZoneDTO addZone(@RequestBody ZoneDTO zoneDTO) {
        return zoneService.save(zoneDTO.convertToZoneEntity());
    }
}
