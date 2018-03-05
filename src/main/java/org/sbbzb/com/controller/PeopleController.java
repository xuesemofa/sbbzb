package org.sbbzb.com.controller;

import com.github.pagehelper.Page;
import org.sbbzb.com.model.PeopleModel;
import org.sbbzb.com.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@RestController
@RequestMapping("/people")
public class PeopleController {

    @Value("${page.pageSize}")
    private int pageSize;

    @Autowired
    private PeopleService service;

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public ModelAndView init(@RequestParam("pageNow") int pageNow) {
        Page<PeopleModel> page = service.find(pageNow, pageSize);
        return new ModelAndView("/people/index")
                .addObject("page", page);
    }

    @RequestMapping(value = "/toAdd", method = RequestMethod.GET)
    public ModelAndView toAdd() {
        return new ModelAndView("/people/add");
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public boolean add(@ModelAttribute("form") PeopleModel model) {
        if (model.getName() == null || model.getName().isEmpty())
            return false;
        Page<PeopleModel> page = service.findByName(1, 15, model.getName());
        if (page.size() > 0)
            return false;
        int i = service.save(model);
        if (i > 0)
            return true;
        else
            return false;
    }
}
