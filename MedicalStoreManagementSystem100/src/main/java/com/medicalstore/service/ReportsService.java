package com.medicalstore.service;

import com.medicalstore.entity.Reports;
import com.medicalstore.exception.ReportNotFoundException;
import com.medicalstore.repository.ReportsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportsService {
    @Autowired
    private ReportsRepository reportsRepository;

    public List<Reports> getAllReports() {
        return reportsRepository.findAll();
    }

    public Reports getReportById(Long id) {
        return reportsRepository.findById(id)
                                 .orElseThrow(() -> new ReportNotFoundException("Report not found with ID: " + id));
    }

    public Reports saveReport(Reports report) {
        return reportsRepository.save(report);
    }

    public void deleteReportById(Long id) {
        if (!reportsRepository.existsById(id)) {
            throw new ReportNotFoundException("Report not found with ID: " + id);
        }
        reportsRepository.deleteById(id);
    }
}
