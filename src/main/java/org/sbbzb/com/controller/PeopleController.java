package org.sbbzb.com.controller;

import com.github.pagehelper.Page;
import org.sbbzb.com.model.PeopleModel;
import org.sbbzb.com.service.PeopleService;
import org.sbbzb.com.util.redirect.RedirectUtil;
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
    public ModelAndView add(@ModelAttribute("form") PeopleModel model) {
        RedirectUtil redirectUtil = new RedirectUtil();
        if (model.getUuid() == null || model.getName().isEmpty())
            return new ModelAndView("/people/add")
                    .addObject("error", "姓名不能为空");
        Page<PeopleModel> page = service.findByName(1, 15, model.getName());
        if (page.size() > 0)
            return new ModelAndView("/people/add")
                    .addObject("error", "姓名重复");
        int i = service.save(model);
        if (i > 0)
            return new ModelAndView(redirectUtil.getRedirect() + "/people/init")
                    .addObject("pageNow", 1);
        else
            return new ModelAndView("/people/add")
                    .addObject("error", "保存失败");
    }
}
