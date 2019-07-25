package com.xiatianlong.signatrue.service;

import com.xiatianlong.signatrue.entity.PdfEntity;
import com.xiatianlong.signatrue.exception.ApplicationException;

/**
 * PdfService.
 * Created by xiatl on 2019-07-25 09:10
 */
public interface PdfService {


    /**
     * 你保存pdf
     * @param pdfEntity 骑牛
     * @return entity
     */
    PdfEntity savePdf(PdfEntity pdfEntity) throws ApplicationException;
}
