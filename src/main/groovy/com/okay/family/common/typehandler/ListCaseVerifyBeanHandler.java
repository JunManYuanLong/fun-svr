package com.okay.family.common.typehandler;

import com.alibaba.fastjson.JSON;
import com.okay.family.common.basedata.OkayConstant;
import com.okay.family.common.bean.testcase.CaseVerifyBean;
import com.okay.family.fun.config.Constant;
import com.okay.family.fun.utils.Join;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 处理用例详情的读写
 */
@MappedTypes(List.class)
@MappedJdbcTypes(JdbcType.VARCHAR)
public class ListCaseVerifyBeanHandler extends BaseTypeHandler<List<CaseVerifyBean>> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<CaseVerifyBean> parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, Join.join(parameter, OkayConstant.MYSQL_SEPARATE));
    }

    @Override
    public List<CaseVerifyBean> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String str = rs.getString(columnName);
        if (null != str) {
            List<String> beans = Arrays.asList(str.split(OkayConstant.MYSQL_SEPARATE));
            return beans.stream().map(x -> JSON.parseObject(x, CaseVerifyBean.class)).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    @Override
    public List<CaseVerifyBean> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String str = rs.getString(columnIndex);
        if (null != str) {
            List<String> beans = Arrays.asList(str.split(Constant.PART));
            return beans.stream().map(x -> JSON.parseObject(x, CaseVerifyBean.class)).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    @Override
    public List<CaseVerifyBean> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String str = cs.getString(columnIndex);
        if (null != str) {
            List<String> beans = Arrays.asList(str.split(Constant.PART));
            return beans.stream().map(x -> JSON.parseObject(x, CaseVerifyBean.class)).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }


}
