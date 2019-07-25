package com.xiatianlong.signatrue.dao;

import com.xiatianlong.signatrue.entity.PdfEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * PdfRepository.
 * Created by xiatl on 2019-07-25 09:03
 */
public interface PdfRepository extends JpaRepository<PdfEntity, String> {


    PdfEntity findByIdNo(String idNo);
}
