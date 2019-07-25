package com.xiatianlong.signatrue.service;

import com.xiatianlong.signatrue.dao.PdfRepository;
import com.xiatianlong.signatrue.entity.PdfEntity;
import com.xiatianlong.signatrue.exception.ApplicationException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * pdfServiceImpl.
 * Created by xiatl on 2019-07-25 09:11
 */
@Service
public class PdfServiceImpl implements PdfService {

    @Autowired
    private PdfRepository pdfRepository;

    /**
     * 你保存pdf
     * @param pdfEntity 骑牛
     * @return entity
     */
    @Override
    @Transactional
    public PdfEntity savePdf(PdfEntity pdfEntity) throws ApplicationException {
        if (pdfEntity == null || StringUtils.isEmpty(pdfEntity.getIdNo()) || StringUtils.isEmpty(pdfEntity.getMobile())){
            throw new ApplicationException("请完整填写身份证、手机号");
        }
        PdfEntity temp = pdfRepository.findByIdNo(pdfEntity.getIdNo());
        if (temp != null){
            throw new ApplicationException("您已填提交过合同，请勿重新提交");
        }
        pdfEntity.setCreatedDate(new Date());
        return pdfRepository.saveAndFlush(pdfEntity);
    }
}
