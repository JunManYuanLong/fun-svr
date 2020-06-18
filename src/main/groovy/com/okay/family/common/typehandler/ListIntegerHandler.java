package com.okay.family.common.typehandler;

import com.okay.family.common.bean.pubdata.PubDataDetailBean;
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
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.okay.family.fun.frame.SourceCode.changeStringToInt;

@MappedTypes(PubDataDetailBean.class)
@MappedJdbcTypes(JdbcType.VARCHAR)
public class ListIntegerHandler extends BaseTypeHandler<List<Integer>> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<Integer> parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, Join.join(parameter, Constant.COMMA));
    }

    @Override
    public List<Integer> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String str = rs.getString(columnName);
        if (null != str) {
            String[] split = str.split(Constant.PART);
            return Arrays.asList(split).stream().map(x -> changeStringToInt(x)).collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public List<Integer> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String str = rs.getString(columnIndex);
        if (null != str) {
            String[] split = str.split(Constant.PART);
            return Arrays.asList(split).stream().map(x -> changeStringToInt(x)).collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public List<Integer> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String str = cs.getString(columnIndex);
        if (null != str) {
            String[] split = str.split(Constant.PART);
            return Arrays.asList(split).stream().map(x -> changeStringToInt(x)).collect(Collectors.toList());
        }
        return null;
    }


}
