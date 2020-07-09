package com.okay.family.common.typehandler;

import com.alibaba.fastjson.JSON;
import com.okay.family.common.basedata.OkayConstant;
import com.okay.family.common.bean.pubdata.PubDataDetailBean;
import com.okay.family.fun.config.Constant;
import com.okay.family.fun.utils.Join;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@MappedTypes(List.class)
@MappedJdbcTypes(JdbcType.VARCHAR)
public class ListPubDetailHandler extends BaseTypeHandler<List<PubDataDetailBean>> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<PubDataDetailBean> parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, Join.join(parameter, OkayConstant.MYSQL_SEPARATE));
    }

    @Override
    public List<PubDataDetailBean> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String str = rs.getString(columnName);
        if (StringUtils.isNoneBlank(str)) {
            List<String> beans = Arrays.asList(str.split(OkayConstant.MYSQL_SEPARATE));
            return beans.stream().map(x -> JSON.parseObject(x, PubDataDetailBean.class)).collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public List<PubDataDetailBean> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String str = rs.getString(columnIndex);
        if (StringUtils.isNoneBlank(str)) {
            List<String> beans = Arrays.asList(str.split(Constant.PART));
            return beans.stream().map(x -> JSON.parseObject(x, PubDataDetailBean.class)).collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public List<PubDataDetailBean> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String str = cs.getString(columnIndex);
        if (StringUtils.isNoneBlank(str)) {
            List<String> beans = Arrays.asList(str.split(Constant.PART));
            return beans.stream().map(x -> JSON.parseObject(x, PubDataDetailBean.class)).collect(Collectors.toList());
        }
        return null;
    }


}
