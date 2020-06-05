package com.okay.family.common.bean.testuser

import com.okay.family.fun.base.bean.AbstractBean
import org.hibernate.validator.constraints.Length

import javax.validation.constraints.Min
import javax.validation.constraints.NotNull

class DelUserBean extends AbstractBean {

    private static final long serialVersionUID = 7314229831207229048L;

    @Min(value = 1L)
    int id

    @Min(value = 1L)
    int owner

    @NotNull
    @Length(min = 5, max = 11)
    String uname

}
