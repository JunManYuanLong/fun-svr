package com.okay.family.common.typehandler;

import com.alibaba.fastjson.JSONArray;
import com.okay.family.fun.frame.SourceCode;
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

/**
 * 从数据库里面读取的map运行结果转化成json格式
 */
@MappedTypes(JSONArray.class)
@MappedJdbcTypes(JdbcType.VARCHAR)
public class ResultArrayHandler extends BaseTypeHandler<JSONArray> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, JSONArray parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, String.valueOf(parameter.toJSONString()));
    }

    @Override
    public JSONArray getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String sqlJson = rs.getString(columnName);
        if (null != sqlJson) {
            JSONArray result = new JSONArray();
            List<String> asList = Arrays.asList(sqlJson.split(";"));
            asList.stream().forEach(x -> {
                String[] split = x.split("=", 2);
                result.add(SourceCode.getJson("text=" + split[0], "amount=" + split[1]));
            });
            return result;
        }
        return null;
    }

    @Override
    public JSONArray getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String sqlJson = rs.getString(columnIndex);
        if (null != sqlJson) {
            JSONArray result = new JSONArray();
            List<String> asList = Arrays.asList(sqlJson.split(";"));
            asList.stream().forEach(x -> {
                String[] split = x.split("=", 2);
                result.add(SourceCode.getJson("text=" + split[0], "amount=" + split[1]));
            });
            return result;
        }
        return null;
    }

    @Override
    public JSONArray getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String sqlJson = cs.getString(columnIndex);
        if (null != sqlJson) {
            JSONArray result = new JSONArray();
            List<String> asList = Arrays.asList(sqlJson.split(";"));
            asList.stream().forEach(x -> {
                String[] split = x.split("=", 2);
                result.add(SourceCode.getJson("text=" + split[0], "amount=" + split[1]));
            });
            return result;
        }
        return null;

    }


}
