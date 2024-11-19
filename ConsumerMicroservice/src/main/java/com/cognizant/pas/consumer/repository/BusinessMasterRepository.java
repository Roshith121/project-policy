package com.cognizant.pas.consumer.repository;

import org.hibernate.annotations.DynamicUpdate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.cognizant.pas.consumer.model.BusinessMaster;

@Repository
@DynamicUpdate
public interface BusinessMasterRepository extends JpaRepository<BusinessMaster, Long> {

	BusinessMaster findByBusinesscategoryAndBusinesstype(String businesscategory, String businesstype);

}
