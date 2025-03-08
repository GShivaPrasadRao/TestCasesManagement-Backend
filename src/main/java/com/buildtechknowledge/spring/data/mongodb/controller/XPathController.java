package com.buildtechknowledge.spring.data.mongodb.controller;


import com.buildtechknowledge.spring.data.mongodb.model.XPath;
import com.buildtechknowledge.spring.data.mongodb.service.XPathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/xpaths")
public class XPathController {

    @Autowired
    private XPathService xpathService;

    @GetMapping
    public List<XPath> getAllXpaths() {
        return xpathService.getAllXpaths();
    }

    @GetMapping("/{id}")
    public ResponseEntity<XPath> getXPathById(@PathVariable String id) {
        return xpathService.getXPathById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public XPath createXPath(@RequestBody XPath xpath) {
        return xpathService.createXPath(xpath);
    }

    @PutMapping("/{id}")
    public XPath updateXPath(@PathVariable String id, @RequestBody XPath xpath) {
        return xpathService.updateXPath(id, xpath);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteXPath(@PathVariable String id) {
        xpathService.deleteXPath(id);
        return ResponseEntity.noContent().build();
    }
}
