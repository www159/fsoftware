package com.example.nationaltax.controller;

import com.example.nationaltax.bean.Privilege;
import com.example.nationaltax.bean.Privilege;
import com.example.nationaltax.service.PrivilegeService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/privilege")
public class PrivilegeController {
    @Autowired
    PrivilegeService privilegeService;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "User ID", example = "1", dataType = "long", paramType = "path")
    })
    @GetMapping("/{id}/privileges")
    public List<Privilege> getUserPrivilegesByUserId(@PathVariable("id") Long id) {
        return privilegeService.getUserPrivilegesByUserId(id);
    }

    @PutMapping("/update/{id}")
    public String updatePrivilege(@PathVariable Long id, @RequestBody Privilege privilege) {
        privilege.setPrivilegeId(id);
        privilegeService.updateById(privilege);
        return "Privilege updated successfully";
    }

    @PostMapping("/addPrivilege")
    public String addPrivilege(@RequestBody Privilege privilege) {
        privilegeService.save(privilege);
        return "Privilege added successfully";
    }

    @DeleteMapping("/delete/{id}")
    public String deletePrivilege(@PathVariable Long id) {
        privilegeService.removeById(id);
        return "Privilege deleted successfully";
    }
}
