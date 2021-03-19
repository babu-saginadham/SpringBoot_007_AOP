package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.jdbc.model.StudentAudit;

@Repository
public interface StudentAuditRepository extends JpaRepository<StudentAudit, Long>{

}
