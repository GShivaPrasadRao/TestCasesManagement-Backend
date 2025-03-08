package com.buildtechknowledge.spring.data.mongodb.service;


import com.buildtechknowledge.spring.data.mongodb.model.XPath;
import com.buildtechknowledge.spring.data.mongodb.repository.XPathRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class XPathService {

    @Autowired
    private XPathRepository xpathRepository;

    public List<XPath> getAllXpaths() {
        return xpathRepository.findAll();
    }

    public Optional<XPath> getXPathById(String id) {
        return xpathRepository.findById(id);
    }

    public XPath createXPath(XPath xpath) {
        return xpathRepository.save(xpath);
    }

    public XPath updateXPath(String id, XPath updatedXPath) {
        return xpathRepository.findById(id)
                .map(xpath -> {
                    xpath.setProjectName(updatedXPath.getProjectName());
                    xpath.setProjectPage(updatedXPath.getProjectPage());
                    xpath.setKeyName(updatedXPath.getKeyName());
                    xpath.setValue(updatedXPath.getValue());
                    xpath.setEnvironment(updatedXPath.getEnvironment());
                    return xpathRepository.save(xpath);
                }).orElseGet(() -> {
                    updatedXPath.setId(id);
                    return xpathRepository.save(updatedXPath);
                });
    }

    public void deleteXPath(String id) {
        xpathRepository.deleteById(id);
    }
}
