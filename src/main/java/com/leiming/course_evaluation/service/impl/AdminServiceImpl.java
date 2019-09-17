package com.leiming.course_evaluation.service.impl;

import com.leiming.course_evaluation.dto.Admin;
import com.leiming.course_evaluation.repository.AdminRepository;
import com.leiming.course_evaluation.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;


    @Override
    public Admin login() {
        return null;
    }

    @Override
    public Admin findOneByPassword(String oldPassword) {
        return adminRepository.findOneByPassword(oldPassword);
    }

    @Override
    public void save(Admin admin) {
        adminRepository.save(admin);
    }
}
